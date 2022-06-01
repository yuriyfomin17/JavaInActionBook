package chapter2.apple;

import java.util.List;

public class ApplePrinter {
    public static void main(String[] args) {

    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter appleFancyFormatter){
        for (Apple apple: inventory ){
            String output = appleFancyFormatter.accept(apple);
            System.out.println(output);
        }
    }

}
