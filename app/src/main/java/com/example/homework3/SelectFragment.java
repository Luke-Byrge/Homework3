package com.example.homework3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class SelectFragment extends Fragment {

    Spinner background;
    Spinner seff1;
    Spinner seff2;
    Spinner seff3;
    SeekBar seek1;
    SeekBar seek2;
    SeekBar seek3;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View selectView = inflater.inflate(R.layout.fragment_first, container, false);
        background = (Spinner) selectView.findViewById(R.id.backgroundSongSpinner);
        seff1 = (Spinner) selectView.findViewById(R.id.soundEffect1);
        seff2 = (Spinner) selectView.findViewById(R.id.soundEffect2);
        seff3 = (Spinner) selectView.findViewById(R.id.soundEffect3);

        seek1 = (SeekBar) selectView.findViewById(R.id.soundEffectSeekBar1);
        seek2 = (SeekBar) selectView.findViewById(R.id.soundEffectSeekBar2);
        seek3 = (SeekBar) selectView.findViewById(R.id.soundEffectSeekBar3);

        return selectView;
    }

    public int getSeek1(){
        return seek1.getProgress();
    }
    public int getSeek2(){
        return seek2.getProgress();
    }
    public int getSeek3(){
        return seek3.getProgress();
    }

    public int getBackground(){
        switch (background.getSelectedItem().toString()){
            case "Go Tech Go!":
                return R.raw.gotechgo;
            case "Clapping":
                return R.raw.clapping;
            case "Cheering":
                return R.raw.cheering;
            case "GoHokies":
                return R.raw.lestgohokies;
        }
        return R.raw.gotechgo;
    }

    public int getBackgroundImage(){
        switch (background.getSelectedItem().toString()){
            case "Go Tech Go!":
                return R.drawable.gotech;
            case "Clapping":
                return R.drawable.clapping;
            case "Cheering":
                return R.drawable.cheering;
            case "GoHokies":
                return R.drawable.gohokies;
        }
        return R.raw.gotechgo;
    }

    public int getSeff1(){
        switch (seff1.getSelectedItem().toString()){
            case "Go Tech Go!":
                return R.raw.gotechgo;
            case "Clapping":
                return R.raw.clapping;
            case "Cheering":
                return R.raw.cheering;
            case "GoHokies":
                return R.raw.lestgohokies;
        }
        return R.drawable.gotech;
    }

    public int getSeffImage1(){
        switch (seff1.getSelectedItem().toString()){
            case "Go Tech Go!":
                return R.drawable.gotech;
            case "Clapping":
                return R.drawable.clapping;
            case "Cheering":
                return R.drawable.cheering;
            case "GoHokies":
                return R.drawable.gohokies;
        }
        return R.drawable.gotech;
    }

    public int getSeff2(){
        switch (seff2.getSelectedItem().toString()){
            case "Go Tech Go!":
                return R.raw.gotechgo;
            case "Clapping":
                return R.raw.clapping;
            case "Cheering":
                return R.raw.cheering;
            case "GoHokies":
                return R.raw.lestgohokies;
        }
        return R.raw.gotechgo;
    }

    public int getSeffImage2(){
        switch (seff2.getSelectedItem().toString()){
            case "Go Tech Go!":
                return R.drawable.gotech;
            case "Clapping":
                return R.drawable.clapping;
            case "Cheering":
                return R.drawable.cheering;
            case "GoHokies":
                return R.drawable.gohokies;
        }
        return R.drawable.gotech;
    }

    public int getSeff3(){
        switch (seff3.getSelectedItem().toString()){
            case "Go Tech Go!":
                return R.raw.gotechgo;
            case "Clapping":
                return R.raw.clapping;
            case "Cheering":
                return R.raw.cheering;
            case "GoHokies":
                return R.raw.lestgohokies;
        }
        return R.raw.gotechgo;
    }

    public int getSeffImage3(){
        switch (seff3.getSelectedItem().toString()){
            case "Go Tech Go!":
                return R.drawable.gotech;
            case "Clapping":
                return R.drawable.clapping;
            case "Cheering":
                return R.drawable.cheering;
            case "GoHokies":
                return R.drawable.gohokies;
        }
        return R.drawable.gotech;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


       /*view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SelectFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });*/
    }
}