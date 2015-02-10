package com.webmyne.myclassroom;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class Launcher extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        new CountDownTimer(2500, 1000) {

            @Override
            public void onFinish() {

                Intent i = new Intent(Launcher.this,LoginActivity.class);
                startActivity(i);
                finish();


            }

            @Override
            public void onTick(long millisUntilFinished) {

            }
        }.start();
        
    }



}
