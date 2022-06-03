package chapter3;

import java.util.function.Function;

public class Letter {
    public static String addHeader(String text){
        return "From Raoul, Mario and Alan:" + text;
    }
    public static String addFooter(String text){
        return text + " Kind regards";
    }
    public static String checkSpelling(String text){
        return text.replaceAll("labda", "lambda");
    }

    public static void main(String[] args) {
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> checkSpelling = Letter::checkSpelling;
        Function<String, String> addFooter = Letter::addFooter;
        Function<String, String> stringTransformation = addHeader.andThen(checkSpelling).andThen(addFooter);
    }
}
