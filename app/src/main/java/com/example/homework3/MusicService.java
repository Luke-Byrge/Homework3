package com.example.homework3;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MusicService extends Service {

    MusicPlayer musicPlayer;
    private final IBinder iBinder= new MyBinder();

    public static final String COMPLETE_INTENT = "complete intent";
    public static final String MUSICNAME = "music name";

    @Override
    public void onCreate() {
        super.onCreate();
        musicPlayer = new MusicPlayer(this);
    }

    public void startMusic(int idxBackground, int idxEff1, int idxEff2, int idxEff3, int seek1, int seek2, int seek3, int img1, int img2, int img3, int img4){

        musicPlayer.playMusic(idxBackground, idxEff1, idxEff2, idxEff3, seek1, seek2, seek3, img1, img2, img3, img4);
    }

    public void pauseMusic(){

        musicPlayer.pauseMusic();
    }

    public void resumeMusic(){

        musicPlayer.resumeMusic();
    }

    public int getPlayingStatus(){

        return musicPlayer.getMusicStatus();
    }


    public void onUpdateMusicName(String musicname, int img) {
        Intent intent = new Intent(COMPLETE_INTENT);
        intent.putExtra(MUSICNAME, musicname);
        intent.putExtra("image", img);
        sendBroadcast(intent);
    }

    public void onUpdateMusicImage(String musicname){

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return iBinder;
    }


    public class MyBinder extends Binder {

        MusicService getService(){
            return MusicService.this;
        }
    }
}
