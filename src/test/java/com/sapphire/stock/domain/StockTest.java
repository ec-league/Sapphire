package com.sapphire.stock.domain;

import com.sapphire.stock.model.Stock;
import com.sapphire.stock.repository.StockItemRepository;
import org.junit.Assert;
import org.junit.Test;

import com.sapphire.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Stock Tester.
 * 
 * @author <Authors name>
 * @since <pre>
 * ���� 30, 2016
 * </pre>
 * @version 1.0
 */
public class StockTest extends BaseTest{

   @Autowired
   private StockItemRepository stockItemRepository;

   @Test
   public void construct() throws IOException {

//      Assert.assertNotNull(stockItemRepository);
//      File dir = new File("C:\\Users\\Ethan\\Desktop\\script\\export");
//
//      for(File f : dir.listFiles()){
//         BufferedReader br = new BufferedReader(new FileReader(f));
//
//         List<StockItem> stockItems = new ArrayList<StockItem>(500);
//         String temp = br.readLine();
//         String code = temp.split(" ")[0];
//         String name = temp.split(" ")[1];
//         temp = br.readLine();
//         while (temp != null) {
//            temp = br.readLine();
//            if (!temp.matches("[0-9].*"))
//               break;
//
//            StockItem item = new StockItem(temp);
//            item.setCode(code);
//            item.setName(name);
//
//            stockItems.add(item);
//         }
//         for (int i = 1; i < stockItems.size(); i++) {
//            stockItems.get(i).setEma12(stockItems.get(i - 1).getEma12() * 11 / 13 + stockItems.get(i).getEma12() * 2 / 13);
//            stockItems.get(i).setEma26(stockItems.get(i - 1).getEma26() * 25 / 27 + stockItems.get(i).getEma26() * 2 / 27);
//            stockItems.get(i).setMacdDiff(stockItems.get(i).getEma12() - stockItems.get(i).getEma26());
//            stockItems.get(i).setMacdDea(stockItems.get(i-1).getMacdDea() * 0.8 + stockItems.get(i).getMacdDiff() * 0.2);
//            stockItems.get(i).setMacd(stockItems.get(i).getMacdDiff() - stockItems.get(i).getMacdDea());
//         }
//
//         stockItems.forEach(stockItemRepository::save);
//      }
   }

   /**
    * 
    * Method: getCode()
    * 
    */
   @Test
   public void testGetCode() throws Exception {
      //TODO: Test goes here... 
   }

   /**
    * 
    * Method: getStartDate()
    * 
    */
   @Test
   public void testGetStartDate() throws Exception {
      //TODO: Test goes here... 
   }

   /**
    * 
    * Method: setStartDate(Timestamp startDate)
    * 
    */
   @Test
   public void testSetStartDate() throws Exception {
      //TODO: Test goes here... 
   }

   /**
    * 
    * Method: getEndDate()
    * 
    */
   @Test
   public void testGetEndDate() throws Exception {
      //TODO: Test goes here... 
   }

   /**
    * 
    * Method: setEndDate(Timestamp endDate)
    * 
    */
   @Test
   public void testSetEndDate() throws Exception {
      //TODO: Test goes here... 
   }

   /**
    * 
    * Method: isOverZero()
    * 
    */
   @Test
   public void testIsOverZero() throws Exception {
      //TODO: Test goes here... 
   }

   /**
    * 
    * Method: setIsOverZero(boolean isOverZero)
    * 
    */
   @Test
   public void testSetIsOverZero() throws Exception {
      //TODO: Test goes here... 
   }

   /**
    * 
    * Method: getIncreaseRate()
    * 
    */
   @Test
   public void testGetIncreaseRate() throws Exception {
      //TODO: Test goes here... 
   }

   /**
    * 
    * Method: setIncreaseRate(double increaseRate)
    * 
    */
   @Test
   public void testSetIncreaseRate() throws Exception {
      //TODO: Test goes here... 
   }

   /**
    * 
    * Method: getConsistDays()
    * 
    */
   @Test
   public void testGetConsistDays() throws Exception {
      //TODO: Test goes here... 
   }

   /**
    * 
    * Method: setConsistDays(int consistDays)
    * 
    */
   @Test
   public void testSetConsistDays() throws Exception {
      //TODO: Test goes here... 
   }


   /**
    * 
    * Method: calculateMacd(List<StockItem> stockItems)
    * 
    */
   @Test
   public void testCalculateMacd() throws Exception {
      //TODO: Test goes here... 
      /* 
      try { 
         Method method = Stock.getClass().getMethod("calculateMacd", List<StockItem>.class); 
         method.setAccessible(true); 
         method.invoke(<Object>, <Parameters>); 
      } catch(NoSuchMethodException e) { 
      } catch(IllegalAccessException e) { 
      } catch(InvocationTargetException e) { 
      } 
      */
   }

}
