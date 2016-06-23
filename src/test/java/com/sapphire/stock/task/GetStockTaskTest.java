package com.sapphire.stock.task;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.junit.Test;

import com.sapphire.BaseTest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

/**
 * GetStockTask Tester.
 * 
 * @author EthanPark
 * @since <pre>
 * 23, 2016
 * </pre>
 * @version 1.0
 */
public class GetStockTaskTest {

   /**
    * 
    * Method: executeInternal(JobExecutionContext context)
    * 
    */
   @Test
   public void testExecuteInternal() throws Exception {
      ClientRequest request = new ClientRequest("http://hq.sinajs.cn/list=sz300168");

//      request.accept("application/json");


      ClientResponse<String> response = request.get(String.class);

      BufferedReader br  = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(response.getEntity().getBytes())));

      String output;
      System.out.println("Output from Server .... \n");
      while ((output = br.readLine()) != null) {
         System.out.println(output);
      }

      return;
   }


}
