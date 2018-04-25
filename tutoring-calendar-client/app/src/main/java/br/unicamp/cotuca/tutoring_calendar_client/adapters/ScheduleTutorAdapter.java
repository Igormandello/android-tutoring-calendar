package br.unicamp.cotuca.tutoring_calendar_client.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import br.unicamp.cotuca.tutoring_calendar_client.R;
import models.ScheduleTutor;

/**
 * Created by bart on 25/04/2018.
 */

public class ScheduleTutorAdapter extends ArrayAdapter<ScheduleTutor> {

    private final Context context;
    private final ArrayList<ScheduleTutor> scheduleTutors;

    public ScheduleTutorAdapter(@NonNull Context context, ArrayList<ScheduleTutor> scheduleTutors) {
        super(context, R.layout.schedule_tutor_view, scheduleTutors);
        this.context = context;
        this.scheduleTutors = scheduleTutors;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.schedule_tutor_view, parent, false);

        TextView txtTutorName = (TextView) rowView.findViewById(R.id.txtTutorName);
        TextView txtTutorSchedule = (TextView) rowView.findViewById(R.id.txtTutorSchedule);

        txtTutorName.setText(scheduleTutors.get(position).getTutorName());
        txtTutorSchedule.setText(scheduleTutors.get(position).getSchedule());

        return rowView;
    }
}
