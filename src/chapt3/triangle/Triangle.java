package chapt3.triangle;

import java.util.function.DoubleFunction;

public class Triangle {

    public static double calculateAreaTrapezium(DoubleFunction<Double> areaCalculator, Double a, Double b){
        return 0.5 * (areaCalculator.apply(a) + areaCalculator.apply(b)) * Math.abs(a - b);
    }
    public static void main(String[] args) {
        DoubleFunction<Double> function = x -> x + 10;
        System.out.println(calculateAreaTrapezium(function, 3.0, 7.0));
    }
}
