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
import java.util.Objects;

import br.unicamp.cotuca.tutoring_calendar_client.R;
import models.Schedule;

public class ScheduleAdapter extends ArrayAdapter<Schedule> {

    private final Context context;
    private final ArrayList<Schedule> schedules;

    public ScheduleAdapter(Context context, ArrayList<Schedule> schedules) {
        super(Objects.requireNonNull(context), R.layout.schedule_view, schedules);
        this.context = context;
        this.schedules = schedules;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.schedule_view, parent, false);

        TextView txtWeekDay   = (TextView) rowView.findViewById(R.id.txtWeekDay);
        TextView txtSchedule = (TextView) rowView.findViewById(R.id.txtSchedule);

        txtWeekDay.setText(schedules.get(position).getWeekDay());
        txtSchedule.setText(schedules.get(position).getSchedule());

        return rowView;
    }

}
