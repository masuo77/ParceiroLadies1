package com.example.masuo.parceiroladiesrespectbook;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static android.R.style.Theme_Holo_Dialog;
import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class MainActivity extends AppCompatActivity
        implements
        TestFragment.OnFragmentInteractionListener,
        SeasonListFragment.OnFragmentInteractionListener,
        PlayerListFragment.OnFragmentInteractionListener
{

    private static final String LOG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Log.i(LOG, "Start2");

//        setTitle("Parceiro Ladies Respect Book");
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("Parceiro Ladies Respect Book");
        ab.setSubtitle(Html.fromHtml("<small>長野ACパルセイロレディース リスペクトブック</small>"));

        TestFragment fragment = new TestFragment();
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
        PlayerListFragment fragment = PlayerListFragment.newInstance(year, "");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onPlayerListFragmentInteraction(int year, int id) {
        Log.i(LOG, "onPlayerListFragmentInteraction");
        PlayerInfoBaseFragment fragment = PlayerInfoBaseFragment.newInstance(year, id);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

//        PlayerInfoFragment fragment = new PlayerInfoFragment();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragment_container, fragment.newInstance(year, id));
//        transaction.addToBackStack(null);
//        transaction.commit();

    }


}
