package chapter5;

import java.util.*;
import java.util.stream.Collectors;

public class Trader {
    private final String name;
    private final String city;
    private static final String MILAN = "Milan";
    private static final String CAMBRIDGE = "Cambridge";

    public Trader(String n, String c) {
        this.name = n;
        this.city = c;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    public String toString() {
        return "Trader:" + this.name + " in " + this.city;
    }

    public boolean isTraderFromCambridge(){
        return this.city.equalsIgnoreCase(CAMBRIDGE);
    }
    public boolean isTraderFromMilan(){
        return this.city.equalsIgnoreCase(MILAN);
    }

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");


        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        List<Transaction> transactionListIn2011Sorted = transactions
                .stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        transactionListIn2011Sorted.forEach(System.out::println);

        System.out.println("Unique traders cities\n");
        List<String> uniqueTradersCities = transactions
                .stream()
                .map(Transaction::getCity)
                .distinct()
                .collect(Collectors.toList());
        uniqueTradersCities.forEach(System.out::println);

        System.out.println("\nTraders from Cambridge\n");
        List<Trader> tradersFromCambridge = transactions
                .stream()
                .map(Transaction::getTrader)
                .filter(Trader::isTraderFromCambridge)
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        tradersFromCambridge.forEach(System.out::println);

        // Not very effective since all string are repeatedly concatenated which creates new string object at each iteration
        System.out.println("Traders names sorted alphabetically");
        List<String> tradersNames = transactions
                .stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .sorted()
                .collect(Collectors.toList());
        tradersNames.forEach(System.out::println);

        System.out.println(transactions.stream().map(Transaction::getTrader).map(Trader::getName).distinct().sorted().collect(Collectors.joining(",")));

        System.out.println("Is any trader based in Milan:" + transactions.stream().anyMatch(transaction -> transaction.getTrader().isTraderFromMilan()));


        System.out.println("Transactions values from Cambridge");
        transactions
                .stream()
                .filter(Transaction::isTransactionFromCambridge)
                .map(Transaction::getValue)
                .forEach(System.out::println);

        Optional<Integer> highestTransaction = transactions.stream().map(Transaction::getValue).reduce(Math::max);
        System.out.println("Max: "+highestTransaction.get());

        Optional<Transaction> lowestTransaction = transactions.stream().min(Comparator.comparing(Transaction::getValue));
        System.out.println("Min: " + lowestTransaction.get().getValue());


    }
}
