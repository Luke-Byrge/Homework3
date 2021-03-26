package com.example.homework3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView music;
    Button playScreen;
    Button selectScreen;
    FragmentManager fm;
    PlayFragment playFrag;
    SelectFragment selFrag;

    MusicService musicService;
    MusicReceiver musicReceiver;
    Intent startMusicServiceIntent;
    boolean isBound = false;
    boolean isInitialized = false;
    boolean isSelectScreen;

    public static final String INITIALIZE_STATUS = "intialization status";
    public static final String MUSIC_PLAYING = "music playing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playScreen= (Button) findViewById(R.id.PlayMusicScreenButton);
        selectScreen= (Button) findViewById(R.id.SelectMusicScreenButton);


        fm = getSupportFragmentManager();
        selFrag = (SelectFragment) fm.findFragmentById(R.id.selectFrag);
        playFrag = (PlayFragment) fm.findFragmentById(R.id.playFrag);
        playFrag.setSelFrag(selFrag);


        isSelectScreen = true;

        if(savedInstanceState != null){
            isInitialized = savedInstanceState.getBoolean(INITIALIZE_STATUS);
            isSelectScreen = savedInstanceState.getBoolean("IS_SELECT_SCREEN");
            music.setText(savedInstanceState.getString(MUSIC_PLAYING));
        }

        FragmentTransaction ft = fm.beginTransaction();
        if(isSelectScreen){
            ft.show(selFrag);
            ft.hide(playFrag);
        }
        else{
            ft.hide(selFrag);
            ft.show(playFrag);
        }

        ft.commit();

        playScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction trans = fm.beginTransaction();
                trans.show(playFrag);
                isSelectScreen = false;
                trans.hide(selFrag);
                trans.commit();
            }
        });

        selectScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction trans = fm.beginTransaction();
                trans.hide(playFrag);
                trans.show(selFrag);
                isSelectScreen = true;
                trans.commit();
            }
        });

        startMusicServiceIntent= new Intent(this, MusicService.class);

        if(!isInitialized){
            startService(startMusicServiceIntent);
            isInitialized= true;
        }

        musicReceiver = new MusicReceiver(this);
        playFrag.setMainActivity(this);

    }


    public void startMusic(int idxBackground, int idxEff1, int idxEff2, int idxEff3, int seek1, int seek2, int seek3, int img1, int img2, int img3, int img4){
        musicService.startMusic(idxBackground, idxEff1, idxEff2, idxEff3, seek1, seek2, seek3, img1, img2, img3, img4);
    }

    public void refreshActivity(){
        playFrag.setMainActivity(this);
    }

    /*@Override
    public void onClick(View view) {

        if (isBound) {
            switch (musicService.getPlayingStatus()){
                case 0:
                    musicService.startMusic();
                    play.setText("Pause");
                    break;
                case 1:
                    musicService.pauseMusic();
                    play.setText("Resume");
                    break;
                case 2:
                    musicService.resumeMusic();
                    play.setText("Pause");
                    break;
            }
        }
    }*/


    public void updateName(String musicName) {

        music.setText(musicName);
    }

    @Override
    protected void onResume() {
        super.onResume();
        playFrag.setMainActivity(this);
        if(isInitialized && !isBound){
            bindService(startMusicServiceIntent, musicServiceConnection, Context.BIND_AUTO_CREATE);
        }

        registerReceiver(musicReceiver, new IntentFilter(MusicService.COMPLETE_INTENT));
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(isBound){
            unbindService(musicServiceConnection);
            isBound= false;
        }

        unregisterReceiver(musicReceiver);
    }

    @Override
    protected void onDestroy() {
        //checking if asynctask is still runnning
        //if(playFrag.asynctask!=null && (playFrag.getStatus()== AsyncTask.Status.RUNNING || playFrag.getStatus2()== AsyncTask.Status.RUNNING || playFrag.getStatus3()== AsyncTask.Status.RUNNING)){
            //cancel the task before destroying activity
         //   playFrag.cancelTime();
        //}
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(INITIALIZE_STATUS, isInitialized);
        outState.putBoolean("IS_SELECT_SCREEN", isSelectScreen);

        super.onSaveInstanceState(outState);
    }


    private ServiceConnection musicServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MusicService.MyBinder binder = (MusicService.MyBinder) iBinder;
            musicService = binder.getService();
            isBound = true;

            /*switch (musicService.getPlayingStatus()) {
                case 0:
                    play.setText("Start"); //FIX THIS TO BE THE CORRECT STUFF ACTUALLY LOL
                    break;
                case 1:
                    play.setText("Pause");
                    break;
                case 2:
                    play.setText("Resume");
                    break;
            }*/
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            musicService = null;
            isBound = false;
        }
    };
}