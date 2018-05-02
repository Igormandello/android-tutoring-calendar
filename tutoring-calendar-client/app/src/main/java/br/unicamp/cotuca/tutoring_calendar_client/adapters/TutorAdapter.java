package br.unicamp.cotuca.tutoring_calendar_client.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.unicamp.cotuca.tutoring_calendar_client.R;
import models.Tutor;

/**
 * Created by bart on 22/04/18.
 */

public class TutorAdapter extends ArrayAdapter<Tutor> {

    private final Context context;
    private final ArrayList<Tutor> tutors;

    public TutorAdapter(@NonNull Context context, ArrayList<Tutor> tutors) {
        super(context, R.layout.tutor_view, tutors);
        this.context = context;
        this.tutors = tutors;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.tutor_view, parent, false);

        TextView txtTutorName = (TextView) rowView.findViewById(R.id.txtTutorName);
        TextView txtTutorDescription = (TextView) rowView.findViewById(R.id.txtTutorDescription);
        ImageView txtTutorImg = (ImageView) rowView.findViewById(R.id.imgTutor);

        txtTutorName.setText(tutors.get(position).getName());
        txtTutorDescription.setText((tutors.get(position).getDescription() == null ? "Sem descrição." : tutors.get(position).getDescription()));
        txtTutorImg.setImageBitmap(tutors.get(position).getImage());

        return rowView;
    }
}
