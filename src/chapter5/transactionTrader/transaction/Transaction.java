package chapter5.transactionTrader.transaction;

import chapter5.transactionTrader.trader.Trader;
import chapter5.transactionTrader.util.City;

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
