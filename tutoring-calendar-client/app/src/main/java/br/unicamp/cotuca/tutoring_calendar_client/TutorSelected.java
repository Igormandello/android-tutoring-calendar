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

import br.unicamp.cotuca.tutoring_calendar_client.adapters.ScheduleWeekDayAdapter;
import models.ScheduleWeekDay;
import utils.Utils;

public class TutorSelected extends AppCompatActivity {

    private ListView lvSchedules;
    private TextView txtTutorName;

    private ArrayList<ScheduleWeekDay> schedules = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_selected);

        lvSchedules = findViewById(R.id.lvSchedules);
        txtTutorName = findViewById(R.id.txtTutorName);

        // Tutor selected information: ra and name
        int ra = getIntent().getExtras().getInt("ra");
        final String tutorName = getIntent().getExtras().getString("tutorName");

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(Utils.API_URL + "/tutorSchedules/" + ra,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        String schedule = "";
                        for(int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String location = jsonObject.getString("place");

                                // set String weekDay based on the index of the attribute 'weekday' returned
                                int weekDayIndex = jsonObject.getInt("weekday");
                                String[] weekDaysString = getResources().getStringArray(R.array.week_days);

                                String weekDay = weekDaysString[weekDayIndex - 1];

                                String[] time = jsonObject.getString("initial_hour").split(":");

                                Time initialHour = new Time(Integer.parseInt(time[0]), Integer.parseInt(time[1]), 0);
                                long duration = 1000 * 60 * Integer.parseInt(jsonObject.getString("duration"));
                                Time finalHour = new Time(initialHour.getTime() + duration);

                                if (i + 1 != jsonArray.length() && weekDayIndex == jsonArray.getJSONObject(i + 1).getInt("weekday")) {
                                    schedule += location + ": " + time[0] + ":" + time[1] + " - " + finalHour.getHours() + ":" + finalHour.getMinutes() + "\n";
                                }
                                else {
                                    schedules.add(new ScheduleWeekDay(
                                            weekDay, schedule + location + ": " + time[0] + ":" + time[1] + " - " + finalHour.getHours() + ":" + finalHour.getMinutes()));
                                    schedule = "";
                                }
                            }
                            catch(JSONException e) {
                                Log.e("volley", e.toString());
                            }
                        }

                        ArrayAdapter adapter = new ScheduleWeekDayAdapter(TutorSelected.this, schedules);
                        lvSchedules.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("volley", volleyError.toString());
                    }
                });
        queue.add(request);

        txtTutorName.setText(tutorName);

        if (schedules.size() == 0) {
            lvSchedules.setEmptyView(findViewById(R.id.empty_list_item));
        }
    }

}
