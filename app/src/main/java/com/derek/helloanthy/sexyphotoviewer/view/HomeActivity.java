package com.derek.helloanthy.sexyphotoviewer.view;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.derek.helloanthy.sexyphotoviewer.R;
import com.derek.helloanthy.sexyphotoviewer.model.WebsiteModel;
import com.derek.helloanthy.sexyphotoviewer.parser.WebsiteParser;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends Activity {
    private static int PAGE_STEP = 10; // 10 pages as a step
    private static String TAG = "homeActivity";
    public static Context context;

    private String[] websiteLinks;
    private List<String> websiteLinkList;
    private List<MyTask> myTasks;
    private List<WebsiteModel> websiteModels;

    private DrawerLayout websiteListDrawer;
    private ListView websiteListView;
    private ActionBarDrawerToggle websiteListDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
        setContentView(R.layout.drawerlayout);
        context = this;
        websiteLinks = this.getResources().getStringArray(R.array.website);
        websiteLinkList = new ArrayList<String>();
        websiteLinkList.add(getResources().getString(R.string.get_all_websites));
        for (String s : websiteLinks){
            websiteLinkList.add(s);
        }
        websiteListDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        websiteListView = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        //websiteListDrawer.setDrawerShadow(R.drawable.ic_launcher, GravityCompat.START);
        // Set the adapter for the list view
        websiteListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.website_list_item, websiteLinkList));
        // Set the list's click listener
        websiteListView.setOnItemClickListener(new DrawerItemClickListener());
        //mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        myTasks = new ArrayList<MyTask>();
        websiteModels = new ArrayList<WebsiteModel>();
        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        websiteListDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                websiteListDrawer,         /* DrawerLayout object */
                R.drawable.ic_launcher,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle("onDrawerClosed");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle("onDrawerOpened");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        websiteListDrawer.setDrawerListener(websiteListDrawerToggle);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnectedOrConnecting());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.get_data){
            if (isOnline()){
                requestData(websiteLinks);
            } else {
                Toast.makeText(this, "Network unavaliable", Toast.LENGTH_LONG).show();
            }
            Log.v(TAG, "get_data clicked");

        } else if (id == R.id.action_settings) {
            return true;
        }
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (websiteListDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch(item.getItemId()) {


            default:
                Toast.makeText(this, "get all! id:" + id, Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }
    }
    private void requestData(String... uri){
        MyTask newTask = new MyTask();
        newTask.execute(websiteLinks);
    }
    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }
    private void selectItem(int position) {
        // update the main content by replacing fragments
        Log.v(TAG, "Selected item at position: " + position);
        websiteListDrawer.closeDrawer(websiteListView);
    }

    class MyTask extends AsyncTask<String, Integer, List<WebsiteModel>>{
        @Override
        protected List<WebsiteModel> doInBackground(String... params) {
            int size = params.length;
            for (int i = 0; i < params.length; i++){
                WebsiteModel wm;
                wm = WebsiteParser.parser(params[i]);
                websiteModels.add(wm);
                onProgressUpdate(i);
            }
            return websiteModels;
        }

        @Override
        protected void onPreExecute() {
            myTasks.add(this);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
//            Log.v(TAG, "web page index " + values[0] + ":" + websiteLinks[values[0]] + " loaded"
//                + "\n" + websiteModels.get(values[0]).getWebsiteContent());
        }

        @Override
        protected void onPostExecute(List<WebsiteModel> websiteModels) {
            //super.onPostExecute(s);
            Log.v(TAG, websiteLinks.length - myTasks.size() + 1 + "/" + websiteLinks.length + " webpages loaded");
            if (myTasks.size() == 0){
                Log.v(TAG, "all web pages loaded");
            }
        }

    }
}
