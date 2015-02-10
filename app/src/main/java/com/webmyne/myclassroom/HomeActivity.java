package com.webmyne.myclassroom;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class HomeActivity extends ActionBarActivity {
    
    private ArrayList<TVProgram> programs;
    public static ArrayList<TVProgram> currentPrograms;
    private ListView lvPrograms;
    public static final String TIME_SERVER = "time-a.nist.gov";
    public static Date currentTime;
    public static String MYURL1 = "http://ws-srv-net.in.webmyne.com/Applications/VideoLibrary/1.mp4";
    public static String MYURL2 = "http://ws-srv-net.in.webmyne.com/Applications/VideoLibrary/2.mp4";
    public static String MYURL3 = "http://ws-srv-net.in.webmyne.com/Applications/VideoLibrary/3.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        lvPrograms = (ListView)findViewById(R.id.listPrograms);
      
    }

    @Override
    protected void onResume() {
        super.onResume();

        fillPrograms();

    }

    private void fillPrograms() {

        TVProgram program1 = new TVProgram();
        program1.start_time = "09-02-2015 11:00 AM";
        program1.end_time = "09-02-2015 11:03 AM";
        program1.name = "Pirates of the caribbean";
        program1.url = MYURL1;

        TVProgram program2 = new TVProgram();
        program2.start_time = "09-02-2015 11:03 AM";
        program2.end_time = "09-02-2015 11:06 AM";
        program2.name = "Avengers";
        program2.url = MYURL2;

        TVProgram program3 = new TVProgram();
        program3.start_time = "09-02-2015 11:06 AM";
        program3.end_time = "09-02-2015 11:09 AM";
        program3.name = "Knowing";
        program3.url = MYURL3;


        
        programs = new ArrayList<>();
           
        programs.add(program1);
        programs.add(program2);
        programs.add(program3);


        setupList();
    }

    private void setupList() {
      
        currentTime = new Date();
        currentPrograms = new ArrayList<>();
        currentPrograms.addAll(programs);
        try {
            
            for (TVProgram program : programs) {
                Date startTime = new SimpleDateFormat("dd-MM-yyyy hh:mm a").parse(program.start_time);
                Date endTime = new SimpleDateFormat("dd-MM-yyyy hh:mm a").parse(program.end_time);

                if(endTime.before(currentTime)){
                    currentPrograms.remove(program);
                }

            }
        }catch(ParseException e){
            e.printStackTrace();
        }
         
        lvPrograms.setAdapter(new MyPerformanceArrayAdapter(HomeActivity.this,currentPrograms,currentTime));
        lvPrograms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent i = new Intent(HomeActivity.this,MyPlayerActivity.class);
                    startActivity(i);
                }
            }
        });
    }



}
