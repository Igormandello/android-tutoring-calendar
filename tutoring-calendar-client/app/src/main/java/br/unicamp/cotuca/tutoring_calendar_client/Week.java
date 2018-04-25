package br.unicamp.cotuca.tutoring_calendar_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;

public class Week extends AppCompatActivity implements View.OnClickListener {

    private ListView lvWeek;
    private TextView txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        lvWeek = (ListView) findViewById(R.id.lvWeek);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                                                                R.array.week_days,
                                                                R.layout.week_view);
        lvWeek.setAdapter(adapter);

        txtDescription = (TextView) findViewById(R.id.txtDescription);
        txtDescription.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, WeekDaySelected.class);
        startActivity(i);
    }
}
