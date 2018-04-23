package br.unicamp.cotuca.tutoring_calendar_client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import models.Tutor;

public class Tutors extends AppCompatActivity {

    private ListView lvTutors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutors);

        lvTutors = (ListView) findViewById(R.id.lvTutors);
        ArrayAdapter adapter = new TutorAdapter(this, testTutors());
        lvTutors.setAdapter(adapter);

    }

    private ArrayList<Tutor> testTutors() {
        ArrayList<Tutor> tutorsTest = new ArrayList<Tutor>();

        tutorsTest.add(new Tutor(16001, "José Maria", "Description here...", ""));
        tutorsTest.add(new Tutor(16002, "José Maria", "Description here...", ""));
        tutorsTest.add(new Tutor(16003, "José Maria", "Description here...", ""));
        tutorsTest.add(new Tutor(16004, "José Maria", "Description here...", ""));
        tutorsTest.add(new Tutor(16005, "José Maria", "Description here...", ""));

        return tutorsTest;
    }
}
