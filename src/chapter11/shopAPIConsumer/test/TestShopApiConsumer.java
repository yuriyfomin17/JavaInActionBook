package chapter11.shopAPIConsumer.test;

import chapter11.shopAPIConsumer.ShopAPIConsumer;
import org.junit.jupiter.api.Test;

public class TestShopApiConsumer {
    private static final String PRODUCT_NAME = "IPHONE SUPER DUPER COOL";


    @Test
    public void testGetShopQuoteList() {
        ShopAPIConsumer.getShopQuoteList(16, PRODUCT_NAME);
    }

    @Test
    public void testGetShopQuoteCompletableFutures() {
        ShopAPIConsumer.getShopQuoteListUsingCompletableFutures(16, PRODUCT_NAME);
    }
    @Test
    public void testGetShopQuotesPricesInEurUsingCompletableFutures(){
        ShopAPIConsumer.getShopQuotesPricesInEurUsingCompletableFutures(100, PRODUCT_NAME);
    }

    @Test
    public void testGetDiscountQuoteFromShopQuoteList() {
        ShopAPIConsumer.getDiscountQutoesFromShopQuotes(100, PRODUCT_NAME);
    }

    @Test
    public void testGetDiscountQutoeCompletableFutures() {
        ShopAPIConsumer.getDiscountQuotesFromShopQuotesUsingCompletableFutures(100, PRODUCT_NAME);
    }
    @Test
    public void testGetDiscountQuoteCompletableFuturesAny(){
        ShopAPIConsumer.getAnyDiscountQuotesFromShopQuotesUsingCompletableFutures(100, PRODUCT_NAME);
    }
    @Test
    public void testGetDiscountQuoteCopletableFuturesAll(){
        ShopAPIConsumer.getAllDiscountQuotesFromShopQuotesUsingCompletableFutures(100, PRODUCT_NAME);
    }

}
