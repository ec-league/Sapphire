package com.sapphire.stock.job;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.sapphire.common.utils.TimeUtil;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.BaseTest;
import com.sapphire.stock.domain.Stock;
import com.sapphire.stock.domain.StockItem;
import com.sapphire.stock.repository.StockItemRepository;
import com.sapphire.stock.service.StockService;

/**
 * GetStockTask Tester.
 * 
 * @author EthanPark
 * @since <pre>
 * 23, 2016
 * </pre>
 * @version 1.0
 */
public class GetStockTaskTest extends BaseTest {
   private static String URL_FORMAT = "http://hq.sinajs.cn/list=%s%s";
   @Autowired
   private StockService stockService;

   @Autowired
   private StockItemRepository stockItemRepository;

   /**
    * 
    * Method: executeInternal(JobExecutionContext context)
    * 
    */
   //   @Test
   public void testExecuteInternal() throws Exception {
      List<String> codes = stockService.getAllCodes();

      System.out.println(TimeUtil.now());

      for (String code : codes) {
         String url = getUrl(code);

         ClientRequest request = new ClientRequest(url);

         ClientResponse<String> response;
         try {
            response = request.get(String.class);
            BufferedReader br =
                  new BufferedReader(new InputStreamReader(
                        new ByteArrayInputStream(response.getEntity()
                              .getBytes())));
            String output = br.readLine();

            StockItem item = toDomain(output);
            item.setCode(code);

            StockItem last = stockItemRepository.getLatestStockItem(code);

            if (last.getLogDate().equals(item.getLogDate()))
               return;

            if (last.isStop()) {
               last.updateItem(item, 12, 26);
               stockItemRepository.save(last);
            } else {
               last.setLast(false);

               List<StockItem> items = new ArrayList<>();
               items.add(last);
               items.add(item);

               Stock stock = new Stock(items);

               stock.calculateMacd(12, 26, false);

               if (item.isStop()) {
                  item.setMacdDea(last.getMacdDea());
                  item.setMacdDiff(last.getMacdDiff());
                  item.setMacd(last.getMacd());
                  item.setEma12(last.getEma12());
                  item.setEma26(last.getEma26());
               }

               stockItemRepository.save(items);
            }
         } catch (Exception e) {
            System.out.printf("Code :\"%s\" Not Updated%n", code);
            e.printStackTrace();
            continue;
         }
      }

      System.out.println(TimeUtil.now());
   }

   private StockItem toDomain(String output) {
      StockItem item = new StockItem();

      String data =
            output.substring(output.indexOf('"') + 1, output.indexOf(';') - 1);

      String[] datas = data.split(",");

      item.setName(datas[0]);
      item.setStartPrice(Double.parseDouble(datas[1]));
      item.setEndPrice(Double.parseDouble(datas[3]));
      item.setHighestPrice(Double.parseDouble(datas[4]));
      item.setLowestPrice(Double.parseDouble(datas[5]));

      item.setTrading(Double.parseDouble(datas[8]));
      item.setTradingValue(Double.parseDouble(datas[9]));

      item.setStop(Double.compare(0d, item.getStartPrice()) == 0);
      item.setLast(true);
      item.setLogDate(TimeUtil.fromStockWebString(datas[30]));

      item.setEma12(item.getEndPrice());
      item.setEma26(item.getEndPrice());
      if (!item.isStop())
         item.setIncreaseRate((item.getEndPrice()
               / Double.parseDouble(datas[2]) - 1) * 100);
      else {
         item.setStartPrice(item.getEndPrice());
         item.setHighestPrice(item.getEndPrice());
         item.setLowestPrice(item.getEndPrice());
      }
      return item;
   }

   private String getUrl(String code) {
      if (code.indexOf("60") == 0)
         return String.format(URL_FORMAT, "sh", code);
      else
         return String.format(URL_FORMAT, "sz", code);
   }
}
