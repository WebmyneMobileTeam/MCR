package com.webmyne.myclassroom;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;

import java.io.IOException;



import butterknife.ButterKnife;
import butterknife.InjectView;

public class VideoPlayerActivity extends ActionBarActivity
        implements SurfaceHolder.Callback,
        MediaPlayer.OnPreparedListener,
        MediaPlayer.OnErrorListener,
        VideoControllerView.MediaPlayerControl {
    
    public static String VIDEO_URL_KEY = "video_url_key";
    private static String TAG = "VideoPlayerActivity";

    @InjectView(R.id.videoSurface)
    SurfaceView videoSurface;

    @InjectView(R.id.videoSurfaceContainer)
    FrameLayout videoSurfaceContainer;

    private VideoControllerView controller;
    private MediaPlayer player;
    private String videoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        ButterKnife.inject(this);
        SurfaceHolder videoHolder = videoSurface.getHolder();
        videoHolder.addCallback(this);
        videoHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        player = new MediaPlayer();
        controller = new VideoControllerView(this);
        
        try {

            Uri uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.sample);
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(this, uri);
            player.setOnPreparedListener(this);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, e.getStackTrace().toString());
        } catch (SecurityException e) {
            Log.e(TAG, e.getStackTrace().toString());
        } catch (IllegalStateException e) {
            Log.e(TAG, e.getStackTrace().toString());
        } catch (IOException e) {
            Log.e(TAG, e.getStackTrace().toString());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        getSupportActionBar().show();
        controller.show();
        return false;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        player.setDisplay(holder);
        player.prepareAsync();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        getSupportActionBar().hide();

        controller.setMediaPlayer(this);
        controller.setAnchorView(videoSurfaceContainer);
        player.start();

        player.seekTo(5000);

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.e(TAG, "(" + what + ", " + extra + ")");
        return false;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public int getCurrentPosition() {
        return player.getCurrentPosition();
    }

    @Override
    public int getDuration() {
        return player.getDuration();
    }

    @Override
    public boolean isPlaying() {
        return player.isPlaying();
    }

    @Override
    public void pause() {
        player.pause();
    }

    @Override
    public void seekTo(int i) {
        player.seekTo(i);
    }

    @Override
    public void start() {
        
        player.start();
      
    }

    @Override
    public boolean isFullScreen() {
        return true;
    }

    @Override
    public void toggleFullScreen() {
        
    }

}