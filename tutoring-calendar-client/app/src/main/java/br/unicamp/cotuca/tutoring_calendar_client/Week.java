package br.unicamp.cotuca.tutoring_calendar_client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;

public class Week extends AppCompatActivity {

    private ListView lvWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        lvWeek = (ListView) findViewById(R.id.lvWeek);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                                                                R.array.week_days,
                                                                R.layout.week_view);
        lvWeek.setAdapter(adapter);


    }
}
