package com.derek.helloanthy.sexyphotoviewer.view;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
    private List<MyTask> myTasks;
    private List<WebsiteModel> websiteModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context = this;
        websiteLinks = this.getResources().getStringArray(R.array.website);
        //Toast.makeText(this, websites[0], Toast.LENGTH_SHORT).show();
        myTasks = new ArrayList<MyTask>();
        websiteModels = new ArrayList<WebsiteModel>();
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
        return super.onOptionsItemSelected(item);
    }
    private void requestData(String... uri){
        MyTask newTask = new MyTask();
        newTask.execute(websiteLinks);
    }
    class MyTask extends AsyncTask<String, Integer, List<WebsiteModel>>{
        @Override
        protected List<WebsiteModel> doInBackground(String... params) {
            int size = params.length;
            for (int i = 0; i < params.length; i++){
                WebsiteModel wm;// = new WebsiteModel();
//                wm.setUrl(params[i]);
//                wm.setWebsiteContent(HttpManager.getData(params[i]));
//                Document doc = Jsoup.parse(wm.getWebsiteContent());

                wm = WebsiteParser.parser(params[i]);
                websiteModels.add(wm);
                //Log.v(TAG, "Retrived website content for " + i + ": " + params[i]);
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
            Log.v(TAG, websiteLinks.length - myTasks.size() + 1 + "/" + websiteLinks.length + " wepages loaded");
            if (myTasks.size() == 0){
                Log.v(TAG, "all web pages loaded");
            }
        }

    }
}
