package amg.technicalevaluation.kracekennedyemployeeapplication.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class DaysModel {
    private LocalDate day;
    private boolean isSunny;
    private String weather;

    public DaysModel(LocalDate currentDate,String weather ){
        this.day = currentDate;
        this.weather = weather;
        isSunny();

    }

    public LocalDate getDay() {
        return day;
    }

    public boolean isSunny() {
        if(Objects.equals(weather, "Rain"))
        {
            isSunny = false;
        }else{
            isSunny = true;
        }
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
