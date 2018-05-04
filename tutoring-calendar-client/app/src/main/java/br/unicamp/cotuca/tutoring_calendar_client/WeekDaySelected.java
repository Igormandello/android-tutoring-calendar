package br.unicamp.cotuca.tutoring_calendar_client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;

import br.unicamp.cotuca.tutoring_calendar_client.adapters.ScheduleAdapter;
import br.unicamp.cotuca.tutoring_calendar_client.adapters.ScheduleTutorAdapter;
import models.Schedule;
import models.ScheduleTutor;
import models.Tutor;
import utils.Utils;

public class WeekDaySelected extends AppCompatActivity {

    private ListView lvWeekDaySchedule;
    private TextView txtWeekDaySelected;
    private ArrayList<ScheduleTutor> scheduleTutors = new ArrayList<ScheduleTutor>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_day_selected);

        int weekday = getIntent().getExtras().getInt("weekDaySelected");

        lvWeekDaySchedule = findViewById(R.id.lvWeekDaySchedule);
        txtWeekDaySelected = findViewById(R.id.txtWeekDaySelected);

        final ArrayList<Tutor> tutors = new ArrayList<>();

        final RequestQueue queue = Volley.newRequestQueue(this);

        final JsonArrayRequest request2 = new JsonArrayRequest(Utils.API_URL + "/tutorSchedules/" + (weekday + 1),
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

                                int tutorRa = jsonObject.getInt("tutor_ra");
                                String tutorName = "";
                                for (Tutor t : tutors)
                                    if (t.getRa() == tutorRa) {
                                        tutorName = t.getName();
                                        break;
                                    }

                                scheduleTutors.add(new ScheduleTutor(
                                        tutorName,
                                        location + ": " + time[0] + ":" + time[1] + " - " + finalHour.getHours() + ":" + finalHour.getMinutes()
                                ));
                            }
                            catch(JSONException e) {
                                Log.e("volley", e.toString());
                            }
                        }

                        ArrayAdapter adapter = new ScheduleTutorAdapter(WeekDaySelected.this, scheduleTutors);
                        lvWeekDaySchedule.setAdapter(adapter);

                        if (scheduleTutors.size() == 0) {
                            lvWeekDaySchedule.setEmptyView(findViewById(R.id.empty_list_item)); // change xml
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("volley", volleyError.toString());
                    }
                });

        JsonArrayRequest request = new JsonArrayRequest(Utils.API_URL + "/tutors",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        for(int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                int ra = jsonObject.getInt("ra");
                                String name = jsonObject.getString("name");

                                tutors.add(new Tutor(ra, name, "", null));
                            }
                            catch(JSONException e) {
                                Log.e("volley", e.toString());
                            }
                        }

                        queue.add(request2);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("volley", volleyError.toString());
                    }
                });
        queue.add(request);

        String[] weekDaysString = getResources().getStringArray(R.array.week_days);
        String weekDayString = weekDaysString[weekday];

        txtWeekDaySelected.setText(weekDayString);
    }
}
