package com.example.homework3;

import android.media.MediaPlayer;
import android.os.AsyncTask;

public class MusicPlayer implements MediaPlayer.OnCompletionListener {

    MediaPlayer player;
    MediaPlayer eff1;
    MediaPlayer eff2;
    MediaPlayer eff3;

    TimerAsyncTask asynctask;
    TimerAsyncTask asynctask2;
    TimerAsyncTask asynctask3;

    int currentPosition = 0;
    int musicIndex = 0;
    private int musicStatus = 0;//0: before playing, 1 playing, 2 paused
    private MusicService musicService;

    static final int[] MUSICPATH = new int[]{
            R.raw.mario,
            R.raw.tetris
    };

    static final String[] MUSICNAME = new String[]{
            "Super Mario Brothers",
            "Tetris"
    };

    public MusicPlayer(MusicService service) {

        this.musicService = service;
    }


    public int getMusicStatus() {

        return musicStatus;
    }

    public String getMusicName() {

        return MUSICNAME[musicIndex];
    }

    public void playMusic(int idxBackground, int idxEff1, int idxEff2, int idxEff3, int seek1, int seek2, int seek3, int img1, int img2, int img3, int img4) {
        if (player!= null){
            player.release();
            player= null;
        }
        player= MediaPlayer.create(this.musicService, idxBackground);
        int songTime = player.getDuration();
        player.start();

        //asynctask = new TimerAsyncTask();
        //asynctask.setDelay((seek1/100)*songTime);
        //asynctask.execute();


        player.setOnCompletionListener(this);
        musicService.onUpdateMusicName(getMusicName(), img1);
        musicStatus = 1;
    }

    public void pauseMusic() {
        if(player!= null && player.isPlaying()){
            player.pause();
            currentPosition= player.getCurrentPosition();
            musicStatus= 2;
        }
    }

    public void resumeMusic() {
        if(player!= null){
            player.seekTo(currentPosition);
            player.start();
            musicStatus=1;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        //musicIndex = (musicIndex +1) % MUSICNAME.length;
        player.release();
        player= null;

    }

    private class TimerAsyncTask extends AsyncTask<Integer, Integer, Void> {
        int delay;
        int id = R.raw.gotechgo;
        MediaPlayer player;

        public void setDelay(int d){
            delay = d;
        }
        public void setId(int i){
            id = i;
        }

        public void setPlayer(MediaPlayer p){
            player = p;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);


        }

        @Override
        protected Void doInBackground(Integer... times) {

            publishProgress(id); // publish progress fomr onProgressUpdate method to be triggered.
            try {
                Thread.sleep(delay);
            }
            catch (Exception e) {
                System.out.println(e);
            }

            player= MediaPlayer.create(musicService, id);

            return null;
        }
    }
}