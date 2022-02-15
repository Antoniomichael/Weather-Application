package amg.technicalevaluation.kracekennedyemployeeapplication.model;

import java.time.LocalDate;
import java.util.Objects;

public class DaysModel {
    private final LocalDate day;
    private boolean isSunny;
    private final String weather;

    public DaysModel(LocalDate currentDate,String weather ){
        this.day = currentDate;
        this.weather = weather;
        isSunny();

    }

    public LocalDate getDay() {
        return day;
    }

    public String getWeather() {
        return weather;
    }

    public boolean isSunny() {
        isSunny = !Objects.equals(weather, "Rain");
        return isSunny;
    }

    @Override
    public String toString() {
        return "DaysModel{" +
                "day=" + day +
                ", isSunny=" + isSunny +
                ", weather='" + weather + '\'' +
                '}';
    }
}
