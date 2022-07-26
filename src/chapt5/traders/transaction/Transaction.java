package chapt5.traders.transaction;

import chapt5.traders.trader.Trader;
import chapt5.traders.util.City;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public record Transaction(Trader trader, int year, int value) {

    @Override
    public String toString() {
        return "Transaction{" +
                "trader=" + trader +
                ", year=" + year +
                ", value=" + value +
                '}';
    }

    @Override
    public int year() {
        return year;
    }

    @Override
    public int value() {
        return value;
    }

    public boolean transactionInThisYear(int year) {
        return this.year() == year;
    }
    public boolean isTransactionFromThisCity(City city){
        return this.trader.getCity().equals(city);
    }

    public String getTraderName(){
        return this.trader.getName();
    }

}
