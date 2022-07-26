package chapt5.transactionTrader.transactionFilter;

import chapt5.transactionTrader.trader.Trader;
import chapt5.transactionTrader.transaction.Transaction;
import chapt5.transactionTrader.util.City;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransactionTraderFilter implements TransactionFilterImpl {

    @Override
    public List<Transaction> findAllTransactionsByYear(List<Transaction> transactions, int year) {
        return transactions.stream()
                .filter(transaction -> transaction.transactionInThisYear(year))
                .sorted(Comparator.comparing(Transaction::value))
                .collect(Collectors.toList());
    }

    @Override
    public List<City> findAllUniqueCities(List<Trader> traders) {
        return traders.stream()
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> findsAllTransactionsFromThisCity(List<Transaction> transactions, City city) {
        return transactions.stream()
                .filter(transaction -> transaction.isTransactionFromThisCity(city))
                .sorted(Comparator.comparing(Transaction::getTraderName))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getNamesOfAllTraders(List<Trader> traders) {
        return traders.stream()
                .map(Trader::getName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public boolean anyTraderFromThisCity(List<Trader> traders, City city) {
        return traders.stream().anyMatch(trader -> trader.isTraderFromThisCity(city));
    }


    @Override
    public Optional<Transaction> findMaxTransaction(List<Transaction> transactions) {
        return transactions.stream().max(Comparator.comparing(Transaction::value));
    }

    @Override
    public Optional<Transaction> findMinTransaction(List<Transaction> transactions) {
        return transactions.stream().min(Comparator.comparing(Transaction::value));
    }

    @Override
    public List<City> getUniqueTradersCities(List<Trader> traders) {
        return traders.stream()
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Trader> findAllTradersFromSpecificCity(List<Trader> traders, City city) {
        return traders.stream()
                .filter(trader -> trader.isTraderFromThisCity(city))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Trader> sortsTradersAlphabetically(List<Trader> traders) {
        return traders.stream()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
    }
}
