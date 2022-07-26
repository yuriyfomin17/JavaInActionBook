package chapt5.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataGenerator {

    public static String generateRandomString() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    public static List<String> generateRandomStringList(int size) {
        return Stream.generate(DataGenerator::generateRandomString).limit(size).collect(Collectors.toList());
    }

    public static List<Integer> generateNumbersList(int size) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= size; i++) list.add(i);
        return list;
    }

}
