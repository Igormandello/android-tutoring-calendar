package br.unicamp.cotuca.tutoring_calendar_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Home extends AppCompatActivity implements View.OnClickListener{

    private CardView cardTutors, cardWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cardTutors =  findViewById(R.id.cardTutors);
        cardWeek = findViewById(R.id.cardWeek);

        cardTutors.setOnClickListener(this);
        cardWeek.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i = null;

        switch (view.getId()) {
            case R.id.cardTutors:
                i = new Intent(this, Tutors.class); break;

            case R.id.cardWeek:
                i = new Intent(this, Week.class); break;


            default: break;
        }

        if (i != null)
            startActivity(i);
    }
}
