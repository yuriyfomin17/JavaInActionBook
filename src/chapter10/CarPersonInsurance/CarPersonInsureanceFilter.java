package chapter10.CarPersonInsurance;

import java.util.Optional;

import static chapter10.CarPersonInsurance.Insurance.findCheapestInsurance;

public class CarPersonInsureanceFilter {

    /**
     * <h3>nullSafeFindCheapestInsuranceOptimized</h3>
     * when using flatMap in case {@link Optional} of person contains null then
     * flatMap will not be executed
     */

    public static Optional<Insurance> nullSafeFindCheapestInsuranceOptimized(Optional<Person> person, Optional<Car> car){
        return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
    }


    public static String getCarInsuranceName(Optional<Person> person, int minAge){
        return person.filter(p -> p.getAge() >= minAge)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }
}
