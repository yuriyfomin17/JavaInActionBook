package chapter12;

import java.time.LocalTime;

public class TimeClass {

    private final LocalTime time;

    public TimeClass(int hour, int minutes, int seconds) {
        time = LocalTime.of(hour, minutes, seconds);
    }
    public TimeClass(String timeString){
        // 13:45:20
        time = LocalTime.parse(timeString);
    }

    public int getHour() {
        return time.getHour();
    }

    public int getMinutes() {
        return time.getMinute();
    }

    public int getSecond() {
        return time.getSecond();
    }
}
