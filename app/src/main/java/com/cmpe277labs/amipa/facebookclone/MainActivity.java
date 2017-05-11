package com.cmpe277labs.amipa.facebookclone;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import layout.RequestsFragment;
import layout.SearchFragment;
import layout.SettingsFragment;
import layout.UploadFragment;

import static com.cmpe277labs.amipa.facebookclone.R.id.container;
import static com.cmpe277labs.amipa.facebookclone.R.id.fragment_settings;

public class MainActivity extends AppCompatActivity implements SettingsFragment.OnFragmentInteractionListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    public String mn;
    public BottomNavigationView bmv;
    private View viewLayout;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        bmv = (BottomNavigationView)findViewById(R.id.bottomnavigation);
        bmv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Log.d("++++Menu Item","MENU ITEM"+menuItem.getItemId());
                Log.d("++++Menu Item","Settings "+R.id.action_settings);
                Log.d("++++Menu Item","Upload "+R.id.action_upload);
                Log.d("++++Menu Item","Search "+R.id.action_search);
                Fragment bfam=null;
                FragmentManager fragmentManager=getSupportFragmentManager();
                switch (menuItem.getItemId()){
                    case R.id.action_settings:
                    {
                        Log.d("Inside case",".................case");
                       bfam=new SettingsFragment();
                       break;
                    }
                    case R.id.action_upload:
                        bfam=new UploadFragment();
                        break;
                    case R.id.action_search:
                        bfam=new RequestsFragment();
                        break;
                    }
                Log.d("Inside case",".................case comitted");
                fragmentManager.beginTransaction().replace(R.id.main_content,bfam).commit();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Log.d("onCreateOptionsMenu","onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.d("onOptionsItemSelected","onOptionsItemSelected");

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(String uri) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        public static String ARG_SECTION_NUMBER = "section_number";

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
            Log.d("PlaceholderFragment","PlaceholderFragment");

            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Log.d("++++ARG ROOT VIEW",""+ARG_SECTION_NUMBER);

            if(getArguments().getInt(ARG_SECTION_NUMBER)==1){
                View rootView = inflater.inflate(R.layout.fragment_main, container, false);
                container.removeAllViews();
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==2){
                container.removeAllViews();
                Log.d("onCreateView","onCreateView");

                View rootView = inflater.inflate(R.layout.fragment_search, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==3){
                container.removeAllViews();
                View rootView = inflater.inflate(R.layout.fragment_posts, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==4){
                container.removeAllViews();
                View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
                return rootView;
            }
            return null;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.d("getitem","get item");

            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Log.d("getPageTitle","getPageTitle");

            switch (position) {
                case 0:
                    return "Home";
                case 1:
                    return "Search";
                case 2:
                    return "Timeline";
                case 3:
                    return "Profile";
            }
            return null;
        }
    }
}
