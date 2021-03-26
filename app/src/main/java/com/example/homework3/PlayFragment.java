package com.example.homework3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class PlayFragment extends Fragment {
    View playView;
    Button playpause;
    Button restart;
    ImageView musicImage;
    TextView musicTitle;
    SelectFragment selFrag;

    MainActivity mainActivity;
    Boolean running;


    //TimerAsyncTask asynctask;
    //TimerAsyncTask asynctask2;
    //TimerAsyncTask asynctask3;


    /*MusicService musicService;
    MusicReceiver musicCompletionReceiver;
    Intent startMusicServiceIntent;
    boolean isBound = false;
    boolean isInitialized = false;*/

    /*public AsyncTask.Status getStatus(){
        return asynctask.getStatus();
    }
    public AsyncTask.Status getStatus2(){
        return asynctask2.getStatus();
    }
    public AsyncTask.Status getStatus3(){
        return asynctask3.getStatus();
    }

    public void cancelTime(){
        asynctask.cancel(true);
        asynctask = null;
        asynctask2.cancel(true);
        asynctask2 = null;
        asynctask3.cancel(true);
        asynctask3 = null;
    }*/

    public void setMainActivity(MainActivity main){
        mainActivity = main;
    }
    public void setSelFrag(SelectFragment s){
        selFrag = s;
    }
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        playView = inflater.inflate(R.layout.fragment_second, container, false);

        playpause = (Button) playView.findViewById(R.id.play);
        restart = (Button) playView.findViewById(R.id.restartButton);
        musicImage = (ImageView) playView.findViewById(R.id.imageView);
        musicTitle = (TextView) playView.findViewById(R.id.musicTitle);


        //Need bundle to send picture image and title
        return playView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playpause.getText().equals("Play")){
                    playpause.setText("Pause");
                    running = true;

                    mainActivity.refreshActivity();
                    mainActivity.startMusic(selFrag.getBackground(), selFrag.getSeff1(), selFrag.getSeff2(), selFrag.getSeff3(), selFrag.getSeek1(), selFrag.getSeek2(), selFrag.getSeek3(), selFrag.getBackgroundImage(), selFrag.getSeffImage1(), selFrag.getSeffImage2(), selFrag.getSeffImage3());

                }
                else{
                    playpause.setText("Play");
                    running = false;

                }
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        /*view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(PlayFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });*/
    }

    public int getImage(){
        return (int)musicImage.getTag();
    }


}