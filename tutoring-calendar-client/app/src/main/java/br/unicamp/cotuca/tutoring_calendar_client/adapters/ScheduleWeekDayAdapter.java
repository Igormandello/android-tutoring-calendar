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
import models.ScheduleWeekDay;

public class ScheduleWeekDayAdapter extends ArrayAdapter<ScheduleWeekDay> {

    private final Context context;
    private final ArrayList<ScheduleWeekDay> schedules;

    public ScheduleWeekDayAdapter(Context context, ArrayList<ScheduleWeekDay> schedules) {
        super(Objects.requireNonNull(context), R.layout.schedule_view, schedules);
        this.context = context;
        this.schedules = schedules;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.schedule_view, parent, false);

        TextView txtWeekDay  = rowView.findViewById(R.id.txtWeekDay);
        TextView txtSchedule = rowView.findViewById(R.id.txtSchedule);

        txtWeekDay.setText(schedules.get(position).getWeekDay());
        txtSchedule.setText(schedules.get(position).getSchedule());

        return rowView;
    }

}
