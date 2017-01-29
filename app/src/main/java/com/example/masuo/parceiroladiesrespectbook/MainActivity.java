package com.example.masuo.parceiroladiesrespectbook;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;

public class MainActivity extends AppCompatActivity
        implements
        TestFragment.OnFragmentInteractionListener,
        SeasonListFragment.OnFragmentInteractionListener,
        PlayerListFragment.OnFragmentInteractionListener {
    private static final String LOG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Log.i(LOG, "Start2");


        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("Parceiro Ladies 1");
        ab.setSubtitle(Html.fromHtml("<small>AC長野パルセイロレディース リスペクトブック</small>"));

//        TestFragment fragment = new TestFragment();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.fragment_container, fragment);
//        transaction.commit();

        SeasonListFragment fragment = new SeasonListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, fragment);
        transaction.commit();

    }

    @Override
    public void onTestFragmentInteraction() {
        SeasonListFragment fragment = new SeasonListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    @Override
    public void onSeasonListFragmentInteraction(int year) {
        PlayerListFragment fragment = PlayerListFragment.newInstance(this, year, "");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onPlayerListFragmentInteraction(int year, int id) {
        Log.i(LOG, "onPlayerListFragmentInteraction " + year + " " + id);
        PlayerInfoBaseFragment fragment = PlayerInfoBaseFragment.newInstance(year, id);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
