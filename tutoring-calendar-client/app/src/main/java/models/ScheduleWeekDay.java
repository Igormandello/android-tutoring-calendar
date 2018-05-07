package models;

/**
 * Created by bart on 25/04/2018.
 */

/**
 * ScheduleWeekDay: used to show the schedule of one day of the TUTOR SELECTED
 */
public class ScheduleWeekDay {

    private String weekDay; /* example: Monday, Tuesday... */
    private String schedule;

    public ScheduleWeekDay(String weekDay, String schedule) {
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
