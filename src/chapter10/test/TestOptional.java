package chapter10.test;

import chapter10.CarPersonInsurance.CarPersonInsureanceFilter;
import chapter10.CarPersonInsurance.DataGenerator;
import chapter10.CarPersonInsurance.Person;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class TestOptional {
    private final CarPersonInsureanceFilter FILTER = new CarPersonInsureanceFilter();

    @Test
    public void testFilter(){
        Person person = DataGenerator.createRandomPerson();
        String filteredInsuranceName = CarPersonInsureanceFilter.getCarInsuranceName(Optional.ofNullable(person), 1);

        System.out.println("Filtered String:" + filteredInsuranceName);
    }
}
