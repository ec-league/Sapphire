package com.sapphire.stock.domain;

import com.sapphire.common.dto.Dto;

/**
 * Author: EthanPark <br/>
 * Date: 2017/1/5<br/>
 * Email: byp5303628@hotmail.com
 */
public class StockDetail implements Dto {
   private HistoryInfo historyInfo;

   private CurrentInfo currentInfo;

   public HistoryInfo getHistoryInfo() {
      return historyInfo;
   }

   public void setHistoryInfo(HistoryInfo historyInfo) {
      this.historyInfo = historyInfo;
   }

   public CurrentInfo getCurrentInfo() {
      return currentInfo;
   }

   public void setCurrentInfo(CurrentInfo currentInfo) {
      this.currentInfo = currentInfo;
   }
}
