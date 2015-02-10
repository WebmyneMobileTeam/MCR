package com.webmyne.myclassroom;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MyPerformanceArrayAdapter extends BaseAdapter {
    private final Activity context;
    private final ArrayList<TVProgram> programs;
    private final Date currentTime;

    static class ViewHolder {
        public TextView text;
        public ImageView image;
        public TextView time;
    }

    public MyPerformanceArrayAdapter(Activity context, ArrayList<TVProgram> programs,Date currentTime) {
        this.context = context;
        this.programs = programs;
        this.currentTime = currentTime;
    }

    @Override
    public int getCount() {
        return programs.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        // reuse views
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.item_program, null);
            // configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) rowView.findViewById(R.id.txtProgramName);
            viewHolder.time = (TextView) rowView.findViewById(R.id.txtProgramTime);
            viewHolder.image = (ImageView) rowView .findViewById(R.id.imgPlay);
            rowView.setTag(viewHolder);
        }

        // fill data
        ViewHolder holder = (ViewHolder) rowView.getTag();
        String s = programs.get(position).name;
        holder.text.setText(s);
        String timeString = programs.get(position).start_time +" : "+programs.get(position).end_time;
        holder.time.setText(timeString);
        
        try{
            Date startTime = new SimpleDateFormat("dd-MM-yyyy hh:mm a").parse(programs.get(position).start_time);
            Date endTime = new SimpleDateFormat("dd-MM-yyyy hh:mm a").parse(programs.get(position).end_time);
            
            if(currentTime.after(startTime) && currentTime.before(endTime)){
                holder.image.setVisibility(View.VISIBLE);
            }else{
                holder.image.setVisibility(View.GONE);
            }

        }catch(ParseException e){
            e.printStackTrace();
        }
      
        
        

        return rowView;
    }
}
