package com.codecool.stockApp;

import com.codecool.stockApp.service.RemoteURLReader;
import com.codecool.stockApp.service.StockAPIService;
import com.codecool.stockApp.util.Trader;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// TODO
class TraderTest {


    @Test // Bid was lower than price, buy should return false.
    void testBidLowerThanPrice() throws IOException {
        StockAPIService stockAPIService = Mockito.mock(StockAPIService.class);
        Trader trader = new Trader(stockAPIService);
        Mockito.when(stockAPIService.getPrice("aaa")).thenReturn((double) 30L);
        assertFalse(trader.buy("aaa", 13L));
    }

    @Test // bid was equal or higher than price, buy() should return true.
    void testBidHigherThanPrice() throws IOException {
        StockAPIService stockAPIService = Mockito.mock(StockAPIService.class);
        Trader trader = new Trader(stockAPIService);
        Mockito.when(stockAPIService.getPrice("aaa")).thenReturn((double) 30L);
        assertTrue(trader.buy("aaa", 33L));
    }
}