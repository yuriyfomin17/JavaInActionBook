package chapter5.utilityClass.testUtilityClass;
import chapter5.utilityClass.primeNumberCustomCollector.PrimeNumbersCollector;

import chapter5.utilityClass.DataGenerator;
import chapter5.utilityClass.UtilityClass;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

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

    @Test
    public void testGetPythagoreanTriple(){
        List<List<Integer>> list = UtilityClass.getPythagoreanTriples();
        list.forEach(System.out::println);
    }

    @Test
    public void testCountUniqueWords(){
        System.out.println("Unique words:" + UtilityClass.countNumberOfUniqueWords("src/chapt5/utilityClass/assets/data.txt"));
    }

    @Test
    public void getUniqueWords(){
        System.out.println(UtilityClass.listUniqueNumberOfWords("src/chapt5/utilityClass/assets/data.txt"));
    }
    @Test
    public void testGetFibonacciSequence(){
        List<Integer> list = UtilityClass.getFibonacciSequence(5);
        list.forEach(System.out::println);
    }

    @Test
    public void testPartitioningByPrimeNonPrime(){
        Map<Boolean, List<Integer>> primeNonPrimeList = UtilityClass.getPrimeNumbersList(100);
        primeNonPrimeList.forEach((isPrime, list) -> {
            System.out.println(isPrime ? "Prime" : "Non prime");
            System.out.println(list);
        } );
    }

    @Test
    public void testPartitioningByPrimeNonPrimeCustomCollector(){
        Map<Boolean, List<Integer>> primeNonPrimeList = UtilityClass.getPrimeNumbersListWithCustomCollector(100);
        primeNonPrimeList.forEach((isPrime, list) -> {
            System.out.println(isPrime ? "Prime" : "Non prime");
            System.out.println(list);
        } );
    }

    @Test
    public void testPartitionPrimeNonPrimeNumbersWithoutStream(){
        Map<Boolean, List<Integer>> primeNonPrimeList = UtilityClass.getPrimeNumbersWithoutStream(100);
        primeNonPrimeList.forEach((isPrime, list) -> {
            System.out.println(isPrime ? "Prime" : "Non prime");
            System.out.println(list);
        });
    }

    /**
     * <h3>testPerfomanceOfPrimeNumberFinders</h3>
     * tests perfomance of prime number finders with and without {@link PrimeNumbersCollector}
     */

    @Test
    public void testPerfomanceOfPrimeNumberFinders(){
        UtilityClass.testPerfomanceofWithAndWithoutCusomtCollector(UtilityClass::getPrimeNumbersList, "getPrimeNumbersList");
        UtilityClass.testPerfomanceofWithAndWithoutCusomtCollector(UtilityClass::getPrimeNumbersListWithCustomCollector,"getPrimeNumbersListWithCustomCollector");
        UtilityClass.testPerfomanceofWithAndWithoutCusomtCollector(UtilityClass::getPrimeNumbersWithoutStream, "getPrimeNumbersWithoutStream");
    }
}
