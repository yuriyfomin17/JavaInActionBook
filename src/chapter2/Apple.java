package chapter2;

import chapter2.util.*;

import java.util.List;
import java.util.function.Predicate;

public class Apple implements ApplePredicate {
    private int weight = 0;
    private Color color = Color.getRandomColor();

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        String charateristic = this.getWeight() > 150 ? "heavy": "light";
        return "Apple{" +
                charateristic +
                ", weight=" + weight +
                ", color=" + color +
                '}';
    }

    @Override
    public boolean isAppleHeavy() {
        return this.getWeight() > 150;
    }

    @Override
    public boolean isAppleGreen() {
        return this.getColor().equals(Color.GREEN);
    }

    @Override
    public boolean isAppleRed() {
        return this.getColor().equals(Color.RED);
    }

    @Override
    public boolean isAppleRedAndHeavy() {
        return isAppleRed() && isAppleHeavy();
    }

    public static List<Apple> filterApples(List<Apple> appleList, Predicate<Apple> applePredicate){
        return appleList.stream().filter(applePredicate).toList();
    }
}
