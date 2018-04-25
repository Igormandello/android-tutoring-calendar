package models;

/**
 * Created by bart on 25/04/2018.
 */

public class Schedule {

    private String weekDay;
    private String schedule;

    public Schedule(String weekDay, String schedule) {
        setWeekDay(weekDay);
        setSchedule(schedule);
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
