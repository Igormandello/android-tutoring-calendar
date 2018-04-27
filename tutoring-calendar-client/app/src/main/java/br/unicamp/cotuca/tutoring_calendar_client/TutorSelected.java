package br.unicamp.cotuca.tutoring_calendar_client;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import br.unicamp.cotuca.tutoring_calendar_client.adapters.ScheduleAdapter;
import models.Schedule;
import models.Tutor;
import utils.Utils;

public class TutorSelected extends AppCompatActivity {

    private ListView lvShedules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_selected);

        int ra = getIntent().getExtras().getInt("ra");

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Utils.API_URL + "/tutorSchedule/" + ra;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("INFO", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("ERROR", error.getMessage());
            }
        });
        queue.add(stringRequest);

        lvShedules = findViewById(R.id.lvSchedules);

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
