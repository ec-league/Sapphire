package com.sapphire.stock.cache;

import java.util.List;

import com.sapphire.common.utils.EmailUtil;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.BaseTest;
import com.sapphire.stock.domain.Stock;
import com.sapphire.stock.service.StockService;
import com.sapphire.stock.service.StockStatisticsService;

/**
 * StockCache Tester.
 * 
 * @author EthanPark
 * @since <pre>
 * ���� 28, 2016
 * </pre>
 * @version 1.0
 */
public class StockCacheTest extends BaseTest {

   @Autowired
   private StockCache stockCache;

   @Autowired
   private StockService stockService;

   @Autowired
   private StockStatisticsService stockStatisticsService;

   //   @Test
   public void outputIncreaseTop100ThisMonth() throws EmailException {
      List<Stock> stocks = stockCache.getStockStatics().getIncreaseTop100();

      EmailUtil.EmailBuilder builder =
            new EmailUtil.EmailBuilder().setTitle("最近一个月增幅排名前百的股票信息:排除新股")
                  .setEmail("515466823@qq.com")
                  .setContent(generateEmail(stocks));

      EmailUtil.sendEmail(builder);

      EmailUtil.sendEmail(builder.setEmail("136689664@qq.com"));
   }

   private String generateEmail(List<Stock> stocks) {

      StringBuilder sb = new StringBuilder();

      sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
            + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
            + "<head>\n"
            + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n"
            + "<title>Demystifying Email Design</title>\n"
            + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n"
            + "</head>\n" + "<body style=\"margin: 0; padding: 0;\">");
      sb.append("<table align=\"center\" border=\"1\"><thead><tr><td>股票代码</td><td>股票名字</td><td>历史最高(元)</td><td>金叉平均时长</td><td>增长幅度</td></tr></thead><tbody>");

      for (Stock s : stocks) {
         sb.append("<tr>");
         sb.append(String.format("<td>%s</td>", s.getCode()));
         sb.append(String.format("<td>%s</td>", s.getName()));
         sb.append(String.format("<td>%.2f</td>", s.getHighestPrice()));
         sb.append(String.format("<td>%d</td>", s.getStockDetail().getHistoryInfo().getAverageGoldDays()));
         sb.append(String.format("<td>%.2f</td>", s.getIncreaseTotal() * 100));
         sb.append("</tr>");
      }

      sb.append("</tbody></table>");
      sb.append("<body></html>");

      return sb.toString();
   }
}
