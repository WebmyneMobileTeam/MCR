package com.webmyne.myclassroom;

import android.app.ProgressDialog;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;


public class PlayVideoActivity extends ActionBarActivity {

    private String videoPath ="url";
    private static ProgressDialog progressDialog;
    String videourl;
    private VideoView videoView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        videoView = (VideoView) findViewById(R.id.videoView);
        progressDialog = ProgressDialog.show(PlayVideoActivity.this, "", "Buffering video...", true);
        progressDialog.setCancelable(true);


        PlayVideo();
    }

    private void PlayVideo()
    {
        try
        {
            getWindow().setFormat(PixelFormat.TRANSLUCENT);
            MediaController mediaController = new MediaController(PlayVideoActivity.this);
            mediaController.setAnchorView(videoView);

            Uri video = Uri.parse(videoPath);
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(video);
            videoView.requestFocus();
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
            {

                public void onPrepared(MediaPlayer mp)
                {
                    progressDialog.dismiss();
                    videoView.start();
                }
            });


        }
        catch(Exception e)
        {
            progressDialog.dismiss();
            System.out.println("Video Play Error :"+e.toString());
            finish();
        }

    }
}


