package com.example.masuo.parceiroladiesrespectbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.masuo.parceiroladiesrespectbook.PlayerList.PlayerListFragment;
import com.example.masuo.parceiroladiesrespectbook.StaffList.StaffListFragment;

public class TabActivity extends AppCompatActivity
        implements
        PlayerListFragment.OnFragmentInteractionListener,
        StaffListFragment.OnFragmentInteractionListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private static final String LOG = "TabActivity";
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private String season;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        // シーズンを受け取る
        Intent intent = getIntent();
        this.season = intent.getStringExtra("season");
        String league = intent.getStringExtra("league");
        String slogan = intent.getStringExtra("slogan");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle(season + " " + getString(R.string.app_team));
        toolbar.setSubtitle("「" + slogan + "」");

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
//        navigationView.setNavigationItemSelectedListener(this);
//

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), this);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tab, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private Context context;

        public SectionsPagerAdapter(FragmentManager fm, Context context) {
            super(fm);

            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
//            return PlaceholderFragment.newInstance(position + 1);
            if (position == 0) {
                return PlayerListFragment.newInstance(context, season, "");
            } else {
                return StaffListFragment.newInstance(context, season, "");

            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "選手";
                case 1:
                    return "スタッフ";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }


    @Override
    public void onPlayerListFragmentInteraction(String year, String id, String league, String slogan) {
        Log.i(LOG, "onPlayerListFragmentInteraction " + year + " " + id);

        Intent intent = new Intent(this, PlayerInfoActivity.class);
        intent.putExtra("year", year);
        intent.putExtra("id", id);
        intent.putExtra("league", league);
        intent.putExtra("slogan", slogan);
        startActivity(intent);

//        PlayerInfoBaseFragment fragment = PlayerInfoBaseFragment.newInstance(year, id);
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.setCustomAnimations(R.anim.fade_in, 0, R.anim.fade_in, 0);
//        transaction.replace(R.id.fragment_container, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
    }

    @Override
    public void onStaffListFragmentInteraction(String year, String id, String league, String slogan) {
        Log.i(LOG, "onStaffListFragmentInteraction " + year + " " + id);

        Intent intent = new Intent(this, StaffInfoActivity.class);
        intent.putExtra("year", year);
        intent.putExtra("id", id);
        intent.putExtra("league", league);
        intent.putExtra("slogan", slogan);
        startActivity(intent);

        // どちらか

//        PlayerInfoBaseFragment fragment = PlayerInfoBaseFragment.newInstance(year, id);
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.setCustomAnimations(R.anim.fade_in, 0, R.anim.fade_in, 0);
//        transaction.replace(R.id.fragment_container, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
    }


//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        FragmentTransaction transaction;
//        FragmentManager manager;
//
//        switch (id) {
//            case R.id.menu_home:
//                //処理
//
//                finish();
//
////                SeasonListFragment fragment = new SeasonListFragment();
////                transaction = getSupportFragmentManager().beginTransaction();
////
////                manager = getSupportFragmentManager();
////                manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
////
////                transaction.setCustomAnimations(R.anim.fade_in, 0, R.anim.fade_in, 0);
////                transaction.add(R.id.fragment_container, fragment);
////                transaction.commit();
//                break;
////            case R.id.menu_mypage:
////                Toast.makeText(MainActivity.this, "マイページ", Toast.LENGTH_SHORT).show();
////                break;
////            case R.id.menu_youtube:
////                Toast.makeText(MainActivity.this, "Youtube", Toast.LENGTH_SHORT).show();
////                break;
//
//            case R.id.menu_app_description:
////                getSupportActionBar().hide();
//                AboutThisAppFragment aboutThisAppFragment = new AboutThisAppFragment();
//                transaction = getSupportFragmentManager().beginTransaction();
//                transaction.setCustomAnimations(R.anim.fade_in, 0, R.anim.fade_in, 0);
//                transaction.replace(R.id.fragment_container, aboutThisAppFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//                break;
////            case R.id.menu_setting:
////                Toast.makeText(MainActivity.this, "設定", Toast.LENGTH_SHORT).show();
////                break;
//            default:
//                Toast.makeText(this, "?", Toast.LENGTH_SHORT).show();
//                break;
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }

//    @Override
//    public void onAboutThisAppFragmentInteraction(Uri uri) {
//
//    }

}
