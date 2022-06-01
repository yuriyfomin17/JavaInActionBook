package chapter2.apple;

public class Apple {
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
}
