package chapter11.shopAPI.test;

import chapter11.shopAPI.DiscountQuote;
import chapter11.shopAPI.ShopQuote;
import chapter11.util.UtillClass;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestShopAPIDiscountQuote {
    private static final String PRODUCT_NAME = "IPHONE SUPER DUPER COOL";
    private static final int SHOP_QUOTES_SIZE = 5;
    @Test
    public void testGenerateRandomShopQuotes(){
        List<ShopQuote> shopQuoteList = UtillClass.generateRandomShopeQuotes(SHOP_QUOTES_SIZE, PRODUCT_NAME);
        shopQuoteList.forEach(System.out::println);
    }


    @Test
    public void testGenerateRandomDiscountQuotes(){
        List<ShopQuote> shopQuotesList = UtillClass.generateRandomShopeQuotes(20, PRODUCT_NAME);
        List<DiscountQuote> discountQuoteList = UtillClass.generateRandomDiscountQuotes(shopQuotesList);
        discountQuoteList.forEach(System.out::println);
    }


}
