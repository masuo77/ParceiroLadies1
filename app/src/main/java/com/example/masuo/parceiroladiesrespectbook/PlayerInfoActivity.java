package com.example.masuo.parceiroladiesrespectbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.masuo.parceiroladiesrespectbook.PlayerInfo.PlayerInfoBaseFragment;

public class PlayerInfoActivity extends AppCompatActivity {
    private static final String LOG = "PlayerInfoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();
        String year = intent.getStringExtra("year");
        String id = intent.getStringExtra("id");
        String league = intent.getStringExtra("league");
        String slogan = intent.getStringExtra("slogan");

        Log.i(LOG, "PlayerInfoActivity " + year + " " + id);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle(year + " " + getString(R.string.app_team));
        toolbar.setSubtitle("「" + slogan + "」");

        PlayerInfoBaseFragment fragment = PlayerInfoBaseFragment.newInstance(year, id);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.setCustomAnimations(R.anim.fade_in, 0, R.anim.fade_in, 0);
        transaction.add(R.id.fragment_container, fragment);
        transaction.commit();

//
//        PlayerInfoBaseFragment fragment = PlayerInfoBaseFragment.newInstance(year, id);
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.setCustomAnimations(R.anim.fade_in, 0, R.anim.fade_in, 0);
//        transaction.replace(R.id.fragment_container, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();

    }
}
