package com.sapphire.common.dal.stock.domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.BaseTest;
import com.sapphire.biz.stock.algorithm.StockAlgorithm;
import com.sapphire.biz.stock.service.StockService;
import com.sapphire.common.dal.stock.repository.StockItemRepository;
import com.sapphire.common.utils.TimeUtil;

/**
 * Stock Tester.
 * 
 * @author <Authors name>
 * @since
 * 
 *        <pre>
 *        </pre>
 * 
 * @version 1.0
 */
public class StockTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockTest.class);

    @Autowired
    private StockItemRepository stockItemRepository;

    @Autowired
    private StockService        stockService;

    @Autowired
    private StockAlgorithm      stockAlgorithm;

    //   @Test
    public void testStat() {
        List<String> codes = stockItemRepository.getCodes();
        List<CodeIncrease> list = new ArrayList<>(codes.size());

        for (String code : codes) {
            Stock stock = stockService.getStockByCodeAndTime(code,
                TimeUtil.fromStockString("05/26/2016"), TimeUtil.now());

            if (stock == null)
                continue;

            list.add(new CodeIncrease(code, stock.getIncreaseFromStart()));
        }

        Collections.sort(list, (o1, o2) -> Double.compare(o1.getIncrease(), o2.getIncrease()));

        for (int i = 0; i < 100; i++) {
            System.out.println(String.format("Code: \"%s\", Increase: %.2f", list.get(i).getCode(),
                list.get(i).getIncrease()));
        }

        Collections.reverse(list);
        System.out.println("#############################");
        for (int i = 0; i < 100; i++) {
            System.out.println(String.format("Code: \"%s\", Increase: %.2f", list.get(i).getCode(),
                list.get(i).getIncrease()));
        }
    }

    private static class CodeIncrease {
        private String code;
        private double increase;

        public CodeIncrease(String code, double increase) {
            this.code = code;
            this.increase = increase;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public double getIncrease() {
            return increase;
        }

        public void setIncrease(double increase) {
            this.increase = increase;
        }
    }

    @Test
    public void construct() throws IOException, ParseException {
        String code = "600000";
        Timestamp from = new Timestamp(
            new SimpleDateFormat("MM/dd/yyyy").parse("07/20/2015").getTime());
        Timestamp to = new Timestamp(
            new SimpleDateFormat("MM/dd/yyyy").parse("09/10/2015").getTime());

        List<StockItem> stockItems = stockItemRepository.getStockByCodeAndTime(code, from, to);

        Assert.assertNotNull(stockItems);
    }

    @Test
    public void construct1() throws Exception {
        Assert.assertNotNull(stockItemRepository);

        System.out.println(TimeUtil.now());
        String path = "";

        String osname = System.getProperty("os.name");

        if (osname.contains("windows")) {
            path = "C:\\Users\\Ethan\\Desktop\\script\\export";
        } else if (osname.contains("Mac")) {
            path = "/Users/yunpeng.byp/Desktop/export";
            if (!stockItemRepository.getCodes().isEmpty())
                return;
        } else {
            return;
        }

        File dir = new File(path);

        File[] files = dir.listFiles();

        if (files == null)
            return;

        for (File f : files) {
            handleOneStock(f);
        }
    }

    private void handleOneStock(File f) throws Exception {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(new FileInputStream(f), "GBK"));

        List<StockItem> stockItems = new ArrayList<>(500);
        String temp = br.readLine();
        String code = temp.split(" ")[0];
        String name = temp.split(" ")[1];
        LOGGER.info(String.format("%s : %s processing.%n", code, name));
        temp = br.readLine();
        while (temp != null) {
            temp = br.readLine();
            if (!temp.matches("[0-9].*"))
                break;

            StockItem item = new StockItem(temp);
            item.setCode(code);
            item.setName(name);

            stockItems.add(item);
        }

        if (stockItems.isEmpty())
            return;
        Stock stock = new Stock(stockItems);
        stockAlgorithm.calculateMacd(stock, true);

        StockItem last = stockItems.get(stockItems.size() - 1);

        last.setLast(true);

        stockItemRepository.save(stockItems);
    }
}
