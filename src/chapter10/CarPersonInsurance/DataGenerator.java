package chapter10.CarPersonInsurance;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataGenerator {
    private static final Random RANDOM = new Random();
    private static final int PERSON_AGE_BOUND = 50;

    public static Insurance createRandomInsurance() {
        return new Insurance(RandomStringUtils.randomAlphabetic(6));
    }

    public static Car createRandomCar() {
        return new Car(createRandomInsurance());
    }

    public static Person createRandomPerson() {
        return new Person(createRandomCar(), RANDOM.nextInt(PERSON_AGE_BOUND));
    }

    public static List<Person> getRandomListOfPeople(int size) {
        return Stream.generate(DataGenerator::createRandomPerson).limit(size).collect(Collectors.toList());
    }
}
