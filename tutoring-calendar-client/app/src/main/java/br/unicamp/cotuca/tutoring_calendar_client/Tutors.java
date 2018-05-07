package br.unicamp.cotuca.tutoring_calendar_client;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
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
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import javax.net.ssl.HttpsURLConnection;

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

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(Utils.API_URL + "/tutors",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        for(int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                byte[] data = Base64.decode(jsonObject.getString("image").split(",")[1], Base64.DEFAULT);
                                Bitmap img = BitmapFactory.decodeByteArray(data, 0, data.length);

                                tutors.add(new Tutor(
                                           jsonObject.getInt("ra"),
                                           jsonObject.getString("name"),
                                           jsonObject.getString("description"),
                                           img));
                            }
                            catch(JSONException e) {
                                Log.e("volley", e.toString());
                            }
                        }

                        ArrayAdapter adapter = new TutorAdapter(Tutors.this, tutors);
                        lvTutors.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("volley", volleyError.toString());
                    }
                });
        queue.add(request);

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

        if (tutors.size() == 0) {
            lvTutors.setEmptyView(findViewById(R.id.empty_list_item));
        }
    }
}
