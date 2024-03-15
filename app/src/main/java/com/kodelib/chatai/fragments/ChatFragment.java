package com.kodelib.chatai.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kodelib.chatai.R;
import com.kodelib.chatai.activity.ChatActivity;
import com.kodelib.chatai.utils.AdsUtility;
import com.kodelib.chatai.utils.Utils;

public class ChatFragment extends Fragment {

    public ChatFragment() {
    }

    private CardView startBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        startBtn = view.findViewById(R.id.startBtn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdsUtility.showInterAds(getActivity(), new AdsUtility.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        Utils.isStart = true;
                        Intent intent = new Intent(getActivity(), ChatActivity.class);
                        startActivity(intent);
                        getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    }
                });


            }
        });

        return view;
    }
}