package chapt5.traders.testTrading;
import chapt5.traders.trader.Trader;
import chapt5.traders.transaction.Transaction;
import chapt5.traders.transactionFilter.TransactionTraderFilter;
import chapt5.traders.util.City;
import chapt5.traders.util.DataGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class TestTrading {

    private static final TransactionTraderFilter TRANSACTION_TRADER_FILTER = new TransactionTraderFilter();

    @Test
    public void testFindAllTransactionsByYear(){
        List<Transaction> transactions = DataGenerator.generateListOfTransactions(100);

        System.out.println("Find all transactions in the year 2011 and sort them by value (small to high).");

        List<Transaction> filteredTranasactions = TRANSACTION_TRADER_FILTER.findAllTransactionsByYear(transactions, 2011);
        filteredTranasactions.forEach(System.out::println);
    }

    @Test
    public void testFindAllUniqueCities(){
        List<Trader> traders = DataGenerator.generateListOfTraders(20);
        System.out.println("What are all the unique cities where the traders work");

        List<City> uniqueCities = TRANSACTION_TRADER_FILTER.findAllUniqueCities(traders);
        uniqueCities.forEach(System.out::println);
    }

    @Test
    public void testFindsAllTransactionsFromThisCity(){
        List<Transaction> transactions = DataGenerator.generateListOfTransactions(1000);
        System.out.println("Find all traders from Cambridge and sort them by name");

        List<Transaction> filteredTransactions = TRANSACTION_TRADER_FILTER.findsAllTransactionsFromThisCity(transactions, City.CAMBRIDGE);
        filteredTransactions.forEach(System.out::println);
    }

    @Test
    public void testGetNamesOfAllTraders(){
        List<Trader> traders = DataGenerator.generateListOfTraders(1000);
        System.out.println("Return a string of all traders’ names sorted alphabetically.");

        List<String> sortedNames = TRANSACTION_TRADER_FILTER.getNamesOfAllTraders(traders);
        sortedNames.forEach(System.out::println);
    }

    @Test
    public void testAnyTraderFromThisCity(){
        List<Trader> traders = DataGenerator.generateListOfTraders(10);
        traders.forEach(System.out::println);

        System.out.println("Are any traders based in Milan:" + TRANSACTION_TRADER_FILTER.anyTraderFromThisCity(traders, City.MILAN));
    }

    @Test
    public void testFindMaxTransaction(){
        List<Transaction> transactions = DataGenerator.generateListOfTransactions(20);
        transactions.forEach(System.out::println);
        System.out.println("What’s the highest value of all the transactions?");

        Optional<Transaction> maxTransaction = TRANSACTION_TRADER_FILTER.findMaxTransaction(transactions);

        maxTransaction.ifPresent(System.out::println);

    }

    @Test
    public void testFindMinTransaction(){
        List<Transaction> transactions = DataGenerator.generateListOfTransactions(20);
        transactions.forEach(System.out::println);

        System.out.println("Find the transaction with the smallest value.");
        Optional<Transaction> minTransaction = TRANSACTION_TRADER_FILTER.findMinTransaction(transactions);

        minTransaction.ifPresent(System.out::println);

    }
}
