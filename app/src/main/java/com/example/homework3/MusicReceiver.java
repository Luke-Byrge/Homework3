package com.example.homework3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MusicReceiver extends BroadcastReceiver {

    MainActivity mainActivity;

    public MusicReceiver(){
        //empty constructor
    }

    public MusicReceiver(MainActivity mainActivity) {
        this.mainActivity= mainActivity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String musicName= intent.getStringExtra(MusicService.MUSICNAME);
        mainActivity.updateName(musicName);
    }
}