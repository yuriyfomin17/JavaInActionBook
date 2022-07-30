package chapter10.CarPersonInsurance;

import java.util.Optional;

public class Insurance {
    private final String name;

    public Insurance(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car){
        if (person.isPresent() && car.isPresent()){
            return Optional.of(findCheapestInsurance(person.get(), car.get()));
        }
        return Optional.empty();
    }

    public static Insurance findCheapestInsurance(Person person, Car car){
        System.out.println("Comapring " + person + " and " + car);
        return new Insurance("Cheapest Insurance");
    }
}
