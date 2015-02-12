package com.webmyne.myclassroom;

import android.app.ProgressDialog;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.IOException;
import java.util.Date;


public class PlayVideoActivity extends ActionBarActivity{

    private String videoPath = "";
    private VideoView videoView;
    private int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        videoView = (VideoView)findViewById(R.id.video_view);
        
        playVideo(pos);
        
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.e("Video Completed",""+pos);
                
                if(pos < HomeActivity.currentPrograms.size()){
                   pos = pos+1;
                   playVideo(pos);
                }
            }
        });

    }
    
    public void playVideo(final int position){


        videoView.setVideoPath(HomeActivity.currentPrograms.get(position).url);
        
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
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


