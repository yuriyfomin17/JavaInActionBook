package chapt3.letter;

import java.util.function.Function;

public class Letter {

    public static String addHeader(String text){
        return "From Yuriy and Nastya:" + text;
    }

    public static String addFooter(String text){
        return text + " Kind Regards";
    }
    public static String checkSpelling(String text){
        return text.replaceAll("labda", "lambda");
    }

    public static Function<String, String> addHeader(){
        return Letter::addHeader;
    }

    public static Function<String, String> headerFooterSpellingPipeline(){
        return addHeader().andThen(Letter::addFooter).andThen(Letter::checkSpelling);
    }
}
