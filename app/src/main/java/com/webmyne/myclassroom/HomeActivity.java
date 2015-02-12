package com.webmyne.myclassroom;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;


public class HomeActivity extends ActionBarActivity {
    
    private ArrayList<TVProgram> programs;
    public static ArrayList<TVProgram> currentPrograms;
    private ListView lvPrograms;

     private Date currentTime;
    public static String MYURL1 = "http://ws-srv-net.in.webmyne.com/Applications/VideoLibrary/one.3gp";
    public static String MYURL2 = "http://ws-srv-net.in.webmyne.com/Applications/VideoLibrary/two.3gp";
    public static String MYURL3 = "http://ws-srv-net.in.webmyne.com/Applications/VideoLibrary/three.3gp";
    

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
        program1.start_time = "12-02-2015 11:00 AM";
        program1.end_time = "12-02-2015 11:03 AM";
        program1.name = "Pirates of the caribbean";
        program1.url = MYURL1;

        TVProgram program2 = new TVProgram();
        program2.start_time = "12-02-2015 11:03 AM";
        program2.end_time = "12-02-2015 11:06 AM";
        program2.name = "Avengers";
        program2.url = MYURL2;

        TVProgram program3 = new TVProgram();
        program3.start_time = "12-02-2015 11:06 AM";
        program3.end_time = "12-02-2015 11:09 AM";
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
                Date startTime = DateUtilities.parse(program.start_time);
                Date endTime = DateUtilities.parse(program.end_time);
                
                if(endTime.before(currentTime)){
                    currentPrograms.remove(program);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        MyPerformanceArrayAdapter adapter = new MyPerformanceArrayAdapter(HomeActivity.this,currentPrograms,currentTime);
        lvPrograms.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        lvPrograms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent i = new Intent(HomeActivity.this,PlayVideoActivity.class);
                    startActivity(i);

                }
            }
        });
    }



}
