package com.webmyne.myclassroom;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;
import java.util.Date;


public class PlayVideoActivity extends ActionBarActivity {

    private String videoPath = "";
    private VideoView videoView;
    private int pos = 0;
    private ProgressBar pb;
    private TextView downloadRateView, loadRateView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        videoView = (VideoView)findViewById(R.id.video_view);
        pb = (ProgressBar) findViewById(R.id.probar);
        downloadRateView = (TextView) findViewById(R.id.download_rate);
        loadRateView = (TextView) findViewById(R.id.load_rate);
        
        playVideo(pos);
        
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.e("Video Completed",""+pos);
             
                if(pos < HomeActivity.currentPrograms.size()-1){
                   pos = pos+1;
                   playVideo(pos);
                }else{

                    new CountDownTimer(1000, 1000) {

                        @Override
                        public void onFinish() {
                            Toast.makeText(PlayVideoActivity.this, "Completed", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onTick(long millisUntilFinished) {

                        }
                    }.start();
                    

                }
            }
        });

    }
    public void playVideo(final int position){

        pb.setVisibility(View.VISIBLE);
        videoView.setVideoPath(HomeActivity.currentPrograms.get(position).url); 
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                pb.setVisibility(View.GONE);
                videoView.start();
                long start_time = DateUtilities.parse(HomeActivity.currentPrograms.get(position).start_time).getTime();
                Date currentDate = new Date();
                long current_time = currentDate.getTime();
                double diff = DateUtilities.difference(start_time,current_time);
                videoView.seekTo((int)diff);
            }
        });
     
        
    }


}


