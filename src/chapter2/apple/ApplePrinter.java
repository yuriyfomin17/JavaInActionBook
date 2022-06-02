package chapter2.apple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

public class ApplePrinter {
    public static void main(String[] args) {
        ArrayList<Apple> arrayList = new ArrayList<>();
        arrayList.add(new Apple());
        arrayList.add(new Apple());
        arrayList.add(new Apple());
        // chaining comparator
        arrayList.sort(comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));
        System.out.println(arrayList);

        // chaining predicate
        Predicate<Apple> redApple = (apple) -> apple.getColor().equals("red");
        System.out.println(redApple.test(new Apple()));
        Predicate<Apple> notRedApple = redApple.negate();
        Predicate<Apple> notRedAppleHeavierThen150  = notRedApple.and(Apple::isHeavierThen150);

        // chaining function interface
        Function<Integer, Integer> f = x -> x + 2;
        Function<Integer, Integer> h = x -> x * 3;
        Function<Integer, Integer> z = f.andThen(h); // h(f(x))
        Function<Integer, Integer> j = f.compose(h); // f(h(x))
        System.out.println(z.apply(2));
        System.out.println(j.apply(2));
    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter appleFancyFormatter){
        for (Apple apple: inventory ){
            String output = appleFancyFormatter.accept(apple);
            System.out.println(output);
        }
    }


}
