package com.derek.helloanthy.sexyphotoviewer.view.website;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.derek.helloanthy.sexyphotoviewer.R;
import com.derek.helloanthy.sexyphotoviewer.model.CategoryModel;
import com.derek.helloanthy.sexyphotoviewer.model.ImagePageModel;
import com.derek.helloanthy.sexyphotoviewer.model.WebsiteModel;

import java.util.List;

//import android.support.v4.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WebsiteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WebsiteFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class WebsiteFragment extends Fragment implements ActionBar.TabListener{
    // TODO: Rename parameter arguments, choose names that matchiWebsiteFragment
    private static String TAG = "WebsiteFragment";
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static List<WebsiteModel> wml;
    private WebsiteModel wm = null;
    private int position = 0;
    private ViewPager viewPager;
    private static final String POSITION = "position";

    private WebsiteTagAdapter websiteTagAdapter;

//    private static final String ARG_PARAM2 = "param2";
//    private String mParam1;
//    private String mParam2;

/*
    public WebsiteModel getWm() {
        return wm;
    }

    public void setWm(WebsiteModel wm) {
//        TextView tv = (TextView) getView().findViewById(R.id.viewPagerTextViewPlaceHolder);
//        tv.setText(this.uri);
        this.wm = wm;
    }*/

    private OnFragmentInteractionListener mListener;
    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param params Parameters.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment WebsiteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WebsiteFragment newInstance(String... params) {
        WebsiteFragment fragment = new WebsiteFragment();
        Bundle args = new Bundle();

//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public WebsiteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getView().findViewById(R.id.pager) == null){
            Log.e(TAG, "pager view null");
            //throw new Exception("pager view null");
            return;
        }
        if (getView().findViewById(R.id.viewPagerTextViewPlaceHolder) != null){
            TextView tv = (TextView)getView().findViewById(R.id.viewPagerTextViewPlaceHolder);
            tv.setText("Started, position: " + position);
        }
        websiteTagAdapter = new WebsiteTagAdapter(getFragmentManager());

        // Set up the action bar.
        final ActionBar actionBar = getActivity().getActionBar();
        // Specify that we will be displaying tabs in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        viewPager = (ViewPager) getView().findViewById(R.id.pager);
        viewPager.setAdapter(websiteTagAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When swiping between different app sections, select the corresponding tab.
                // We can also use ActionBar.Tab#select() to do this if we have a reference to the
                // Tab.
                Log.v(TAG, "Actionbar item selected: " + position);
                actionBar.setSelectedNavigationItem(position);
            }
        });
/*        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < wm.getCategories().size(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter.
            // Also specify this Activity object, which implements the TabListener interface, as the
            // listener for when this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mAppSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }*/
        actionBar.removeAllTabs();
        for (String title : wm.getCategories()){
            actionBar.addTab(
                    actionBar.newTab()
                    .setText(title)
                    .setTabListener(this));
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(POSITION);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        if(WebsiteFragment.wml == null) WebsiteFragment.wml = mListener.getWebsiteModel();
        if (position != 0){
            wm = WebsiteFragment.wml.get(position-1);
        }
        List<ImagePageModel> list = ((CategoryModel)wm.getCategoryMap().values().toArray()[0]).getImagePageModelList();
        // set up the adapter
        mAdapter = new ArrayAdapter<ImagePageModel>(getActivity(),
                android.R.layout.simple_list_item_1,wm.getCategoryMap().values().toArray(new CategoryModel[0])[0].getImagePageModelList());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //TextView tv = (TextView) getView().findViewById(R.id.viewPagerTextViewPlaceHolder);

        Log.v(TAG, "onCreateView, position: " + position);
        return inflater.inflate(R.layout.fragment_website_content, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            //mListener.onFragmentInteraction(uri.toString());
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            Log.e(TAG, e.getMessage());
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
//        public void onFragmentInteraction(Uri uri);
//        public void onFragmentInteraction(String uri);
        public List<WebsiteModel> getWebsiteModel();
//        public void setWebsiteModel(WebsiteModel wm);
    }

    public class WebsiteTagAdapter extends FragmentStatePagerAdapter {
        public WebsiteTagAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return null;
        }

        @Override
        public int getCount() {
            return 0;
        }
    }

}
