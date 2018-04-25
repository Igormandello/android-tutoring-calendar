package br.unicamp.cotuca.tutoring_calendar_client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.unicamp.cotuca.tutoring_calendar_client.adapters.ScheduleAdapter;
import br.unicamp.cotuca.tutoring_calendar_client.adapters.ScheduleTutorAdapter;
import models.Schedule;
import models.ScheduleTutor;

public class WeekDaySelected extends AppCompatActivity {

    private ListView lvWeekDaySchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_day_selected);

        lvWeekDaySchedule = (ListView) findViewById(R.id.lvWeekDaySchedule);
        ArrayAdapter adapter = new ScheduleTutorAdapter(this, testScheduleTutors());
        lvWeekDaySchedule.setAdapter(adapter);
    }

    private ArrayList<ScheduleTutor> testScheduleTutors() {
        ArrayList<ScheduleTutor> scheduleTutors = new ArrayList<ScheduleTutor>();

        scheduleTutors.add(new ScheduleTutor("Monitor fulano de tal", "Lapa: 7:30 - 9:10"));
        scheduleTutors.add(new ScheduleTutor("Monitor fulano de tal", "Lapa: 7:30 - 9:10"));
        scheduleTutors.add(new ScheduleTutor("Monitor fulano de tal", "Lapa: 7:30 - 9:10"));
        scheduleTutors.add(new ScheduleTutor("Monitor fulano de tal", "Lapa: 7:30 - 9:10"));
        scheduleTutors.add(new ScheduleTutor("Monitor fulano de tal", "Lapa: 7:30 - 9:10"));

        return scheduleTutors;
    }
}
