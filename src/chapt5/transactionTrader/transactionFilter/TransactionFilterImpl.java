package chapt5.transactionTrader.transactionFilter;

import chapt5.transactionTrader.trader.Trader;
import chapt5.transactionTrader.transaction.Transaction;
import chapt5.transactionTrader.util.City;

import java.util.List;
import java.util.Optional;

public interface TransactionFilterImpl {
    /**
     * <h3>findAllTransactionsByYear</h3>
     * finds all transcation in specific year and sort them by value (small to high)
     */

    List<Transaction> findAllTransactionsByYear(List<Transaction> transactions, int year);

    /**
     * <h3>findAllUniqueCities<h3/>
     * find unique cities where traders work
     */
    List<City> findAllUniqueCities(List<Trader> traders);

    /**
     * <h3>findsAllTransactionsFromThisCity</h3>
     * finds all transactions from specific city and sort them by name
     */

    List<Transaction> findsAllTransactionsFromThisCity(List<Transaction> transactions, City city);

    /**
     * <h3>getNamesOfAllTraders</h3>
     * retruns names of all traders and sorts them alphabetically
     */
    List<String> getNamesOfAllTraders(List<Trader> traders);

    /**
     * <h3>findMaxTransaction</h3>
     * finds maximum transaction by value
     */

    Optional<Transaction> findMaxTransaction(List<Transaction> transactions);

    /**
     * <h3>findMinTransaction<h3/>
     * finds minimum transaction by value
     */

    Optional<Transaction> findMinTransaction(List<Transaction> transactions);

    /**
     * <h3>getUniqueTradersCities</h3>
     * find unique cities where traders work
     */
    List<City> getUniqueTradersCities(List<Trader> traders);

    /**
     * <h3>findAllTradersFromSpecificCity</h3>
     * find all traders from specific cities and sorts them by name
     */
    List<Trader> findAllTradersFromSpecificCity(List<Trader> traders, City city);

    /**
     * <h3>sortsTradersAlphabetically</h3>
     * sorts traders alpahabetically
     */
    List<Trader> sortsTradersAlphabetically(List<Trader> traders);

    /**
     * <h3>anyTraderFromThisCity</h3>
     * finds wheter there is any trader from this city
     */

    boolean anyTraderFromThisCity(List<Trader> traders, City city);

}
