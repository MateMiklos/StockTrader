package com.codecool.stockApp;

import com.codecool.stockApp.service.RemoteURLReader;
import com.codecool.stockApp.service.StockAPIService;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// TODO
class StockAPIServiceTest {
    private String url = String.format("https://financialmodelingprep.com/api/v3/stock/real-time-price/%s", "aaa");

    @Test // everything works
    void testGetPriceNormalValues() throws IOException {
        RemoteURLReader remoteURLReader = mock(RemoteURLReader.class);
        StockAPIService stockAPIService = new StockAPIService(remoteURLReader);
        when(remoteURLReader.readFromUrl(url)).thenReturn("{\"price\":23}");
        assertEquals(23L, stockAPIService.getPrice("aaa"));
    }

    @Test // readFromURL threw an exception
    void testGetPriceServerDown() throws IOException {
        RemoteURLReader remoteURLReader = mock(RemoteURLReader.class);
        StockAPIService stockAPIService = new StockAPIService(remoteURLReader);
        when(remoteURLReader.readFromUrl(url)).thenThrow(IOException.class);
        assertThrows(IOException.class, () -> {stockAPIService.getPrice("aaa");});
    }

    @Test // readFromURL returned wrong JSON
    void testGetPriceMalformedResponse() throws IOException {
        RemoteURLReader remoteURLReader = mock(RemoteURLReader.class);
        StockAPIService stockAPIService = new StockAPIService(remoteURLReader);
        when(remoteURLReader.readFromUrl(url)).thenReturn("{\"ball\": \"231\"}");
        assertThrows(JSONException.class, () -> {stockAPIService.getPrice("aaa");});
    }

}