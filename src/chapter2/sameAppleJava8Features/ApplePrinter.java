package chapter2.sameAppleJava8Features;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class ApplePrinter<T> {
    public static void main(String[] args) {
        List<Apple> appleList = new ArrayList<>();

        appleList = filter(appleList, (apple) -> "red".equals(apple.getColor()));

    }
    public static <T> List<T>  filter(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for (T e : list){
            if(p.test(e)){
                result.add(e);
            }
        }
        return result;
    }

}
