package br.unicamp.cotuca.tutoring_calendar_client;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;

import br.unicamp.cotuca.tutoring_calendar_client.adapters.ScheduleAdapter;
import br.unicamp.cotuca.tutoring_calendar_client.adapters.ScheduleTutorAdapter;
import models.Schedule;
import models.ScheduleTutor;
import models.Tutor;
import utils.Utils;

public class TutorSelected extends AppCompatActivity {

    private ListView lvShedules;
    private TextView txtTutorName;

    private ArrayList<ScheduleTutor> schedules = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_selected);

        lvShedules = findViewById(R.id.lvSchedules);
        txtTutorName = findViewById(R.id.txtTutorName);

        int ra = getIntent().getExtras().getInt("ra");
        final String tutorName = getIntent().getExtras().getString("tutorName");

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(Utils.API_URL + "/tutorSchedules/" + ra,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        for(int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String[] time = jsonObject.getString("initial_hour").split(":");

                                Time initialHour = new Time(Integer.parseInt(time[0]), Integer.parseInt(time[1]), 0);
                                long duration = 1000 * 60 * Integer.parseInt(jsonObject.getString("duration"));
                                Time finalHour = new Time(initialHour.getTime() + duration);

                                String location = jsonObject.getString("place");
                                schedules.add(new ScheduleTutor(
                                    tutorName,
                                    location + ": " + time[0] + ":" + time[1] + " - " + finalHour.getHours() + ":" + finalHour.getMinutes()
                                ));
                            }
                            catch(JSONException e) {
                                Log.e("volley", e.toString());
                            }
                        }

                        ArrayAdapter adapter = new ScheduleTutorAdapter(TutorSelected.this, schedules);
                        lvShedules.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("volley", volleyError.toString());
                    }
                });
        queue.add(request);

//        txtTutorName.setText(tutorName);
//
//        if (schedules.size() == 0) {
//            lvShedules.setEmptyView(findViewById(R.id.empty_list_item));
//        }
    }

}
