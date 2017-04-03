package com.example.masuo.parceiroladiesrespectbook;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.masuo.parceiroladiesrespectbook.SeasonList.SeasonListFragment;

public class MainActivity extends AppCompatActivity
        implements
        NavigationView.OnNavigationItemSelectedListener,
        SeasonListFragment.OnFragmentInteractionListener,
        AboutThisAppFragment.OnFragmentInteractionListener {

    private static final String LOG = "MainActivity";
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        SeasonListFragment fragment = new SeasonListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, fragment);
        transaction.commit();
    }

    @Override
    public void onSeasonListFragmentInteraction(String year, String league, String slogan) {
        Log.i(LOG, "onSeasonListFragmentInteraction " + year);

        Intent intent = new Intent(getApplication(), TabActivity.class);
        intent.putExtra("season", year);
        intent.putExtra("league", league);
        intent.putExtra("slogan", slogan);
        startActivity(intent);
    }

//    @Override
//    public void onPlayerListFragmentInteraction(String year, String id) {
//        Log.i(LOG, "onPlayerListFragmentInteraction " + year + " " + id);
//        PlayerInfoBaseFragment fragment = PlayerInfoBaseFragment.newInstance(year, id);
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.setCustomAnimations(R.anim.fade_in, 0, R.anim.fade_in, 0);
//        transaction.replace(R.id.fragment_container, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }

    @Override
    public void onAboutThisAppFragmentInteraction(Uri uri) {
    }

    @Override
    public void onBackPressed() {
        Log.d(LOG, "onBackPressed");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        FragmentTransaction transaction;
        FragmentManager manager;

        switch (id) {
//            case R.id.menu_home:
            //処理
//                SeasonListFragment fragment = new SeasonListFragment();
//                transaction = getSupportFragmentManager().beginTransaction();
//
//                manager = getSupportFragmentManager();
//                manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//
//                transaction.setCustomAnimations(R.anim.fade_in, 0, R.anim.fade_in, 0);
//                transaction.add(R.id.fragment_container, fragment);
//                transaction.commit();
//            break;
//            case R.id.menu_mypage:
//                Toast.makeText(MainActivity.this, "マイページ", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.menu_youtube:
//                Toast.makeText(MainActivity.this, "Youtube", Toast.LENGTH_SHORT).show();
//                break;

            case R.id.menu_app_description:
//                getSupportActionBar().hide();
                AboutThisAppFragment aboutThisAppFragment = new AboutThisAppFragment();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.fade_in, 0, R.anim.fade_in, 0);
                transaction.replace(R.id.fragment_container, aboutThisAppFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
//            case R.id.menu_setting:
//                Toast.makeText(MainActivity.this, "設定", Toast.LENGTH_SHORT).show();
//                break;
            default:
                Toast.makeText(MainActivity.this, "?", Toast.LENGTH_SHORT).show();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
////        getSupportActionBar().show();
////        Toast.makeText(MainActivity.this, "onCreateOptionsMenu", Toast.LENGTH_SHORT).show();
//
//        return super.onCreateOptionsMenu(menu);
//    }
}
