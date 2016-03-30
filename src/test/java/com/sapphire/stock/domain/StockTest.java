package com.sapphire.stock.domain;

import org.junit.Test;

import com.sapphire.BaseTest;

import java.io.*;

/**
 * Stock Tester.
 * 
 * @author <Authors name>
 * @since <pre>
 * 三月 30, 2016
 * </pre>
 * @version 1.0
 */
public class StockTest {

   @Test
   public void construct() throws IOException {
      File f = new File("C:\\Users\\Ethan\\Desktop\\script\\export\\SH600000.txt");

      if (!f.exists() || f.isDirectory())
         throw new FileNotFoundException();
      BufferedReader br = new BufferedReader(new FileReader(f));

      String temp = br.readLine();
      String code = temp.split(" ")[0];
      temp = br.readLine();
      while (temp != null) {
         temp = br.readLine();
         if (temp.contains("数据来源:通达信"))
            break;

      }
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
