package chapt5.transactionTrader.trader;

import chapt5.transactionTrader.util.City;

public class Trader {
    private final String name;
    private final City city;

    public Trader(String n, City c) {
        this.name = n;
        this.city = c;
    }

    public String getName() {
        return this.name;
    }

    public City getCity() {
        return this.city;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "name='" + name + '\'' +
                ", city=" + city +
                '}';
    }

    public boolean isTraderFromThisCity(City city) {
        return this.city.equals(city);
    }
}
