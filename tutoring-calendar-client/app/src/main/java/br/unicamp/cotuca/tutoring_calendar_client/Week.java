package br.unicamp.cotuca.tutoring_calendar_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;

public class Week extends AppCompatActivity  {

    private ListView lvWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        lvWeek = findViewById(R.id.lvWeek);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                                                                R.array.week_days,
                                                                R.layout.week_view);
        lvWeek.setAdapter(adapter);

        lvWeek.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Week.this, WeekDaySelected.class);

                intent.putExtra("weekDaySelected", i);

                startActivity(intent);
            }
        });

    }
}
