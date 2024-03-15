package com.kodelib.chatai.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kodelib.chatai.R;
import com.kodelib.chatai.fragments.ChatFragment;
import com.kodelib.chatai.fragments.AssistantsFragment;
import com.kodelib.chatai.fragments.PromptsFragment;
import com.kodelib.chatai.utils.CustomBottomNavigationView;
import com.kodelib.chatai.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private CustomBottomNavigationView bottomNavigationView;

    private ChatFragment fChat;
    private AssistantsFragment fModel;
    private PromptsFragment fPromots;
    private Fragment activeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.hideStatus(this);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        fChat = new ChatFragment();
        fModel = new AssistantsFragment();
        fPromots = new PromptsFragment();

        activeFragment = fChat;

        loadFragment(activeFragment);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.iChat:
                        setActiveFragment(fChat);
                        return true;
                    case R.id.iModel:
                        setActiveFragment(fModel);
                        return true;
                    case R.id.iPrompts:
                        setActiveFragment(fPromots);
                        return true;

                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void setActiveFragment(Fragment fragment) {
        if (activeFragment != fragment) {
            activeFragment = fragment;
            loadFragment(fragment);
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
