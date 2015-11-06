package com.sapphire.util;


import com.sapphire.constant.JsonDtoFlag;

/**
 * Created by Administrator <br/>
 * Date: 2015/11/4.<br/>
 * Email: byp5303628@hotmail.com
 */
public class JsonDto implements Dto {
   private int returnCode;
   private String returnMessage;

   public int getReturnCode() {
      return returnCode;
   }

   public void setReturnCode(int returnCode) {
      this.returnCode = returnCode;
   }

   public String getReturnMessage() {
      return returnMessage;
   }

   public void setReturnMessage(String returnMessage) {
      this.returnMessage = returnMessage;
   }

   public JsonDto withReturnMessage(String returnMessage) {
      this.returnMessage = returnMessage;
      return this;
   }

   public JsonDto formSuccessDto() {
      this.returnCode = JsonDtoFlag.SUCCESS_CODE;
      this.returnMessage = JsonDtoFlag.SUCCESS_MESSAGE;
      return this;
   }

   public JsonDto formFailureDto() {
      this.returnCode = JsonDtoFlag.FAILURE_CODE;
      this.returnMessage = JsonDtoFlag.FAILURE_MESSAGE;
      return this;
   }
}
