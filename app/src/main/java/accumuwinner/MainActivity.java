package accumuwinner;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.accumuwinnerbettingtips.R;

import accumuwinner.fragments.NewsFeed;
import accumuwinner.fragments.NewsFeedSliderActivity;
import accumuwinner.listadapters.DrawerListAdapter;
import accumuwinner.notification.GcmProvider;

public class MainActivity extends FragmentActivity implements NewsFeed.OnFragmentInteractionListener {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        this.setContentView(R.layout.startscreen);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_content, new PlaceholderFragment())
                    .commit();
        }
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.drawer_list);
        mDrawerList.setAdapter(new DrawerListAdapter(this, R.id.drawer_list));
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    //TODO: Handle click events for each drawer item list
                    default:
                        if(getSupportFragmentManager().findFragmentByTag("NEWS_TAG") != null){
                           getSupportFragmentManager().beginTransaction().show(NewsFeedSliderActivity.getInstance());
                        }
                        else {
                            getSupportFragmentManager().beginTransaction()
                                .add(R.id.main_content, NewsFeedSliderActivity.getInstance(), "NEWS_TAG")
                                .commit();
                        }
                }
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.project_title, R.string.project_title) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getActionBar().setIcon(R.drawable.ic_drawer);
        getActionBar().setHomeButtonEnabled(true);

        // Perform the initialisation check for push notifications
        GcmProvider gcmProvider = new GcmProvider(this);
        if(gcmProvider.checkForGoogleServices()) {
            gcmProvider.registerGcmToken();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch(item.getItemId()) {
            //TODO: Handle click events for each drawer item list
            default:
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.main_content, NewsFeedSliderActivity.getInstance())
                        .commit();
        }

        super.onOptionsItemSelected(item);
        return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_main, container, false);
        }
    }
}