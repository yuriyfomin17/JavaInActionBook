package chapter2.apple;

import java.util.function.Supplier;

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

    public Supplier<Boolean> isRedFunc(){
        return () -> this.color.equals("red");
    }
     public boolean isHeavierThen150(){
        return this.weight > 150;
    }
}
