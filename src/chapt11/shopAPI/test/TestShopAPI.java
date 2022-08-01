package chapt11.shopAPI.test;

import chapt11.shopAPI.Shop;
import chapt11.util.UtillClass;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestShopAPI {
    private static final String PRODUCT_NAME = "IPHONE SUPER DUPER COOL";
    @Test
    public void generateRandomShopList(){
        List<Shop> shopList = UtillClass.generateRandomShopsList(20);
        shopList.forEach(System.out::println);
    }

    @Test
    public void getPriceForProduct(){
        List<Shop> shopList = UtillClass.generateRandomShopsList(5);
        shopList.forEach(System.out::println);

        List<String> prices = UtillClass.getPriceFromRandomShopsList(shopList, PRODUCT_NAME);
        prices.forEach(System.out::println);
    }


}
