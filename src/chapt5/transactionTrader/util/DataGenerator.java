package chapt5.transactionTrader.util;

import chapt5.transactionTrader.trader.Trader;
import chapt5.transactionTrader.transaction.Transaction;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataGenerator {

    public static Trader createRandomTrader() {
        return new Trader(RandomStringUtils.randomAlphabetic(4), City.getRandomCity());
    }

    public static Transaction createRandomTransaction() {
        return new Transaction(createRandomTrader(), getRandomNumber(2010, 2022), getRandomNumber(300, 1500));
    }


    public static int getRandomNumber(int max, int min) {
        return (int) (Math.random() * (max - min) + min);
    }


    public static List<Trader> generateListOfTraders(int size){
        return Stream.generate(DataGenerator::createRandomTrader).limit(size).collect(Collectors.toList());
    }
    public static List<Transaction> generateListOfTransactionsFromExisitingTraders(List<Trader> traders){
        return traders
                .stream()
                .map(trader -> new Transaction(trader, getRandomNumber(2010, 2022), getRandomNumber(300, 1500)))
                .collect(Collectors.toList());
    }
    public static List<Transaction> generateListOfTransactions(int size){
        return Stream.generate(DataGenerator::createRandomTransaction).limit(size).collect(Collectors.toList());
    }
}
