package chapt5.testUtil;

import chapt5.util.DataGenerator;
import chapt5.util.UtilityClass;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UtilityClassTest {

    @Test
    public void testGetUniqueCharactersFromStringList(){
        List<String> stringList  = DataGenerator.generateRandomStringList(20);
        stringList.forEach(System.out::println);

        List<String> uniqueCharacters = UtilityClass.getUniqueCharactersFromStringList(stringList);
        uniqueCharacters.forEach(System.out::println);
    }

    @Test
    public void testGetSquareNumbers(){
        List<Integer> list = DataGenerator.generateNumbersList(20);
        list.forEach(System.out::println);

        List<Integer> squareList = UtilityClass.getSquareNumbers(list);
        squareList.forEach(System.out::println);
    }

    @Test
    public void testGetAllPairs(){
        List<Integer> list1 = DataGenerator.generateNumbersList(3);
        List<Integer> list2 = DataGenerator.generateNumbersList(2);
        List<List<Integer>> result = UtilityClass.getAllPairs(list1, list2);

        result.forEach(System.out::println);
    }

    @Test
    public void testGettAllPairsSumDivisibleByThree(){
        List<Integer> list1 = DataGenerator.generateNumbersList(3);
        List<Integer> list2 = List.of(3, 4);
        List<List<Integer>> result = UtilityClass.getAllPairsDivisibleByThree(list1, list2);
        result.forEach(System.out::println);
    }

    @Test
    public void testGetSum(){
        List<Integer> list1 = DataGenerator.generateNumbersList(3);
        System.out.println("Sum:" + UtilityClass.getSum(list1));
    }
}
