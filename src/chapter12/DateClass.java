package chapter12;

import java.time.LocalDate;

public class DateClass {
    private final LocalDate localDate;

    public DateClass(int year, int month, int day) {
        this.localDate = LocalDate.of(year, month, day);
    }
    public DateClass(String dateString){
        // "2014-03-18"
        this.localDate = LocalDate.parse(dateString);
    }

    public int getYear() {
        return localDate.getYear();
    }

    public int getMonth() {
        return localDate.getDayOfMonth();
    }

    public int getDay() {
        return localDate.getDayOfMonth();
    }
    public int getLengthOfMonth(){
        return localDate.lengthOfMonth();
    }
    public boolean isLeapYear(){
        return localDate.isLeapYear();
    }
}
