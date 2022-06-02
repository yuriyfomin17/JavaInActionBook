package chapter2.sameAppleJava8Features;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class Apple<T> {
    private int weight = 0;
    private String color = "green";
    public int getWeight() {
        return weight;
    }



    public String getColor() {
        return color;
    }

    @Override
    public String toString(){
        return "apple";
    }

    public static void main(String[] args) {

    }

}
