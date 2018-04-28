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

        lvWeekDaySchedule = findViewById(R.id.lvWeekDaySchedule);
        txtWeekDaySelected = findViewById(R.id.txtWeekDaySelected);

        //------------------------------------------------
        // UPDATE THIS: CREATE ARRAY LIST OF SCHEDULETUTORS CORRECTLY
        //------------------------------------------------
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(Utils.API_URL + "/tutors",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        for(int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                scheduleTutors.add(new ScheduleTutor(
                                        jsonObject.getString("name"),
                                        jsonObject.getString("schedule")));
                            }
                            catch(JSONException e) {
                                Log.e("volley", e.toString());
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("volley", volleyError.toString());
                    }
                });

        queue.add(request);
        //------------------------------------------------
        // END//UPDATE THIS
        //------------------------------------------------

        ArrayAdapter adapter = new ScheduleTutorAdapter(this, scheduleTutors);
        lvWeekDaySchedule.setAdapter(adapter);

        if (scheduleTutors.size() == 0) {
            lvWeekDaySchedule.setEmptyView(findViewById(R.id.empty_list_item)); // change xml
        }

        int weekDaySelected = getIntent().getExtras().getInt("weekDaySelected");

        String[] weekDaysString = getResources().getStringArray(R.array.week_days);
        String weekDayString = weekDaysString[weekDaySelected];

        txtWeekDaySelected.setText(weekDayString);
    }
}
