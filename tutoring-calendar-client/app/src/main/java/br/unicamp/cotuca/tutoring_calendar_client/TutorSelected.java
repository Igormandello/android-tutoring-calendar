package br.unicamp.cotuca.tutoring_calendar_client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.unicamp.cotuca.tutoring_calendar_client.adapters.ScheduleAdapter;
import models.Schedule;
import models.Tutor;

public class TutorSelected extends AppCompatActivity {

    private ListView lvShedules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_selected);

        lvShedules = (ListView) findViewById(R.id.lvSchedules);

        ArrayAdapter adapter = new ScheduleAdapter(this, testSchedules());
        lvShedules.setAdapter(adapter);
    }

    private ArrayList<Schedule> testSchedules() {
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();

        schedules.add(new Schedule("Segunda-feira", "Lapa: 7:30 - 9:10\n Dinalva: 8:20 - 9:10"));
        schedules.add(new Schedule("Segunda-feira", "Lapa: 7:30 - 9:10\n Dinalva: 8:20 - 9:10"));
        schedules.add(new Schedule("Segunda-feira", "Lapa: 7:30 - 9:10\n Dinalva: 8:20 - 9:10"));
        schedules.add(new Schedule("Segunda-feira", "Lapa: 7:30 - 9:10\n Dinalva: 8:20 - 9:10"));
        schedules.add(new Schedule("Segunda-feira", "Lapa: 7:30 - 9:10\n Dinalva: 8:20 - 9:10"));
        schedules.add(new Schedule("Segunda-feira", "Lapa: 7:30 - 9:10\n Dinalva: 8:20 - 9:10"));
        schedules.add(new Schedule("Segunda-feira", "Lapa: 7:30 - 9:10\n Dinalva: 8:20 - 9:10"));

        return schedules;
    }
}
