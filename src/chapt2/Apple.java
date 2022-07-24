package chapt2;

import chapt2.util.*;

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
        return "Apple{" +
                "weight=" + weight +
                ", color=" + color +
                '}';
    }

    @Override
    public boolean appleHeavyPredicate(Apple apple, Predicate<Apple> applePredicate) {
        return applePredicate.test(apple);
    }
}
