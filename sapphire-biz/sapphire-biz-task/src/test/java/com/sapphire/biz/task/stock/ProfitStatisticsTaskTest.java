package com.sapphire.biz.task.stock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import com.sapphire.biz.stock.service.StockService;
import com.sapphire.common.dal.stock.domain.Stock;
import com.sapphire.common.dal.stock.domain.Trade;
import com.sapphire.common.dal.stock.repository.TradeRepository;
import com.sapphire.common.integration.dingtalk.pusher.DingTalkMessagePusher;

/** 
* ProfitStatisticsTask Tester. 
* 
* @author EthanPark 
* @since <pre>一月 13, 2018</pre> 
* @version 1.0 
*/
public class ProfitStatisticsTaskTest {

    @Test
    public void test_execute() {
        ProfitStatisticsTask task = new ProfitStatisticsTask();

        task.setPusher(new DingTalkMessagePusher());
        TradeRepository tradeRepository = Mockito.mock(TradeRepository.class);

        List<Trade> unfinishedTrades = new ArrayList<>();
        Trade trade1 = new Trade();
        trade1.setInPrice(11.2);
        trade1.setOutPrice(12.1);
        trade1.setCount(4000);
        trade1.setStockCode("000001");

        unfinishedTrades.add(trade1);

        List<Trade> finishedTrades = new ArrayList<>();
        Trade trade2 = new Trade();
        trade2.setInPrice(10);
        trade2.setOutPrice(12);
        trade2.setCount(4000);

        finishedTrades.add(trade2);

        Mockito.when(tradeRepository.getAllFinishTrades()).thenReturn(finishedTrades);
        Mockito.when(tradeRepository.getAllHoldStockCodes()).thenReturn(unfinishedTrades);

        StockService service = Mockito.mock(StockService.class);

        Stock stock = new Stock();
        stock.setEndPrice(11);
        Mockito.when(service.getStockByCode("000001")).thenReturn(stock);

        task.setTradeRepository(tradeRepository);
        task.setStockService(service);

        task.execute();
    }
}
