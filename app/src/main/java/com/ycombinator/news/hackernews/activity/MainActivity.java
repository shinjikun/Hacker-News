package com.ycombinator.news.hackernews.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.ycombinator.news.hackernews.R;
import com.ycombinator.news.hackernews.adapters.StoryAdapter;
import com.ycombinator.news.hackernews.model.data.Story;
import com.ycombinator.news.hackernews.utils.StoryAPI;
import com.ycombinator.news.hackernews.utils.Utils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements  SwipeRefreshLayout.OnRefreshListener,
        StoryAPI.StoreAPIListener{

    private SwipeRefreshLayout swipeContainer;
    ArrayList<Story> arryList;
    RecyclerView recyclerView;
    StoryAdapter mAdapter;
    StoryAPI storyAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //initialize variables and listeners
        initPullToRefresh();
        initList();
        storyAPI = new StoryAPI();
        storyAPI.setListener(this);
        //check network connectivity before fetching contents
        if(Utils.isNetworkAvailable(this)){
            storyAPI.fetchStoryList();
        }
        else {
            Toast.makeText(this, "No Network Connection", Toast.LENGTH_SHORT).show();
        }




    }





    public int getIndexPosition(long storyID){
       for(int  i= 0;i<arryList.size();i++){
           Story mStory = arryList.get(i);
           if(mStory.getId() == storyID){
               return i;
           }
       }
        return -1;
    }


    private  void initList(){
        arryList = new ArrayList<Story>();
        mAdapter = new StoryAdapter(arryList,this );
        recyclerView = (RecyclerView)findViewById(R.id.listStories);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


    }

    private void initPullToRefresh(){
        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        //configure the refresh color
        swipeContainer.setColorSchemeColors(
                ContextCompat.getColor(getApplicationContext(), R.color.swipeRefreshColor1),
                ContextCompat.getColor(getApplicationContext(), R.color.swipeRefreshColor2),
                ContextCompat.getColor(getApplicationContext(), R.color.swipeRefreshColor1),
                ContextCompat.getColor(getApplicationContext(), R.color.swipeRefreshColor2));

        //set up listener for pull to refresh action
        swipeContainer.setOnRefreshListener(this);

    }


    //pull refresh mechanism
    @Override
    public void onRefresh() {
        if(Utils.isNetworkAvailable(this)){
            storyAPI.fetchStoryList();
        }

        // stopping swipe refresh
        swipeContainer.setRefreshing(false);
    }


    @Override
    public void updateStoryDetails(Story story) {
        int index = getIndexPosition(story.getId());
        if(index==-1){
            arryList.add(story);
        }
        else {
            arryList.set(index,story);
        }
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void addStory(String storyID) {
        storyAPI.fetchStoryInfo(storyID);
    }

    @Override
    public void removeStory(String storyID) {
        int index = getIndexPosition(Long.parseLong(storyID));
        if(index != -1){
            arryList.remove(index);
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void moveStoryIndex(String storyID) {
        int index = getIndexPosition(Long.parseLong(storyID));
        if(index != -1){
            Story story = arryList.get(index);
            arryList.remove(index);
            arryList.add(story);
            mAdapter.notifyDataSetChanged();
        }
    }
}
