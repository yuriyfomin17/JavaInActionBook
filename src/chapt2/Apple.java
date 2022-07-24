package chapt2;

import chapt2.util.*;

import java.util.List;

public class Apple {
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

    public static void main(String[] args) {
        List<Apple> appleArrayList = AppleDataGenerator.getListOfRandomApples(20);
        appleArrayList.forEach(System.out::println);
    }
}
