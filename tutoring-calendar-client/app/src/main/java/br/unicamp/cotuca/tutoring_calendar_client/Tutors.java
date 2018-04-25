package br.unicamp.cotuca.tutoring_calendar_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.unicamp.cotuca.tutoring_calendar_client.adapters.TutorAdapter;
import models.Tutor;

public class Tutors extends AppCompatActivity implements View.OnClickListener{

    private ListView lvTutors;
    private TextView txtTitle; // delete later

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutors);

        lvTutors = (ListView) findViewById(R.id.lvTutors);
        ArrayAdapter adapter = new TutorAdapter(this, testTutors());
        lvTutors.setAdapter(adapter);

        // just testing layout, delete later
        txtTitle = (TextView)findViewById(R.id.txtTitle);
        txtTitle.setOnClickListener(this);

    }

    private ArrayList<Tutor> testTutors() {
        ArrayList<Tutor> tutorsTest = new ArrayList<Tutor>();

        tutorsTest.add(new Tutor(16001, "José Maria", "Description here..."));
        tutorsTest.add(new Tutor(16002, "José Maria", "Description here..."));
        tutorsTest.add(new Tutor(16003, "José Maria", "Description here..."));
        tutorsTest.add(new Tutor(16004, "José Maria", "Description here..."));
        tutorsTest.add(new Tutor(16005, "José Maria", "Description here..."));

        return tutorsTest;
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, TutorSelected.class);
        startActivity(i);
    }
}
