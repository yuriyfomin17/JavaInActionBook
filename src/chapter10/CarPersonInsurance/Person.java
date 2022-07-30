package chapter10.CarPersonInsurance;

import java.util.Optional;

public class Person {
    private final Car car;

    private final int age;

    public Person(Car car, int age) {
        this.car = car;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public Optional<Car> getCar() {
        return Optional.ofNullable(car);
    }
}
