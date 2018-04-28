package br.unicamp.cotuca.tutoring_calendar_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.*;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.unicamp.cotuca.tutoring_calendar_client.adapters.TutorAdapter;
import models.Tutor;
import utils.Utils;

public class Tutors extends AppCompatActivity {

    private ListView lvTutors;
    private ArrayList<Tutor> tutors = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutors);

        lvTutors = findViewById(R.id.lvTutors);

        //------------------------------------------------
        // UPDATE THIS: ADD IMAGE OF TUTORS
        //------------------------------------------------
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(Utils.API_URL + "/tutors",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        for(int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                tutors.add(new Tutor(
                                           jsonObject.getInt("ra"),
                                           jsonObject.getString("name"),
                                           jsonObject.getString("description")));
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
        // END///UPDATE THIS
        //------------------------------------------------

        ArrayAdapter adapter = new TutorAdapter(this, tutors);
        lvTutors.setAdapter(adapter);

        if (tutors.size() == 0) {
            lvTutors.setEmptyView(findViewById(R.id.empty_list_item));
        }

        lvTutors.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Tutors.this, TutorSelected.class);

                int ra = tutors.get(i).getRa();
                String tutorName = tutors.get(i).getName();


                intent.putExtra("ra", ra);
                intent.putExtra("tutorName", tutorName);

                startActivity(intent);
            }
        });
    }
}