package com.zulfa.furnitureapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private ViewFlipper ViewF;
    private Animation fadeIn;
    private Animation fadeOut;
    ImageView gambar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ViewF = (ViewFlipper) view.findViewById(R.id.ViewFlipper);
        fadeIn = AnimationUtils.loadAnimation(getContext(),R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(getContext(),R.anim.fade_out);
        gambar = view.findViewById(R.id.gmbr);
        gambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DetailActivity.class));
            }
        });

        ViewF.setInAnimation((Animation) fadeIn);
        ViewF.setOutAnimation((Animation) fadeOut);

        ViewF.setAutoStart(true);
        ViewF.setFlipInterval(5000);
        ViewF.startFlipping();
    }
}
