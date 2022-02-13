package amg.technicalevaluation.kracekennedyemployeeapplication;

import java.util.Date;

public class DaysModel {
    private int day;
    private boolean isSunny;

    public DaysModel(int currentDate,Boolean isSunny ){
        this.day = currentDate;
        this.isSunny = isSunny;

    }

    public int getDay() {
        return day;
    }

    public boolean isSunny() {
        return isSunny;
    }

    @Override
    public String toString() {
        return "DaysModel{" +
                "day=" + day +
                ", isSunny=" + isSunny +
                '}';
    }
}
