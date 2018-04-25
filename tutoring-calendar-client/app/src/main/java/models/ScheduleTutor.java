package models;

/**
 * Created by bart on 25/04/2018.
 */

public class ScheduleTutor {

    private String tutorName;
    private String schedule;

    public ScheduleTutor(String tutorName, String schedule) {
        setTutorName(tutorName);
        setSchedule(schedule);
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }
    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
