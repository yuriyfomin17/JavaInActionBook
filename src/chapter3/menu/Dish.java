package chapter3.menu;

import java.util.Random;

public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public enum Type{MEAT, FISH, OTHER}

    public Dish(String aName, int calories, Type type){
        this.name = aName;
        this.vegetarian = type.equals(Type.OTHER);
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian(){
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType(){
        return type;
    }
    public boolean isMeatDish(){
        return this.getType().equals(Type.MEAT);
    }

    @Override
    public String toString(){
        return name;
    }

    public static Type getRandomEnumType(){
        int randomType = (int) Math.floor(new Random().nextDouble() * 4);
        for (Type type: Type.values()) {
            if (type.ordinal() == randomType) return type;
        }
        return Type.OTHER;
    }


    static Dish returnRandomDish(){
        int randomNumber = (int) Math.floor(new Random().nextDouble() * 1000);
        return new Dish(Integer.toString(randomNumber), randomNumber, getRandomEnumType());
    }
}
