package com.ycombinator.news.hackernews.utils;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.ycombinator.news.hackernews.model.data.Story;

/**
 * Created by leonardoilagan on 28/06/2016.
 */

public class StoryAPI {


    StoreAPIListener listener;
    public StoryAPI(){

    }

    public void setListener(StoreAPIListener listener) {
        this.listener = listener;
    }

    public void fetchStoryList(){
        // Get a reference to top stories
    Firebase    ref = new Firebase(Constants.API_TOP_STORIES);

        //limit data upto 1-
        Query query =ref.limitToFirst(10);
        query.startAt(null,"10");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String id = dataSnapshot.getValue(String.class);
                System.out.println(id);
                listener.addStory(id);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String id = dataSnapshot.getValue(String.class);
                listener.removeStory(id);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                String id = dataSnapshot.getValue(String.class);
                listener.moveStoryIndex(id);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


    public void fetchStoryInfo(String id){
        Firebase ref = new Firebase(Constants.API_ITEM_INFO+"/"+id);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Story story = snapshot.getValue(Story.class);
                listener.updateStoryDetails(story);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }


    public interface StoreAPIListener {
         void updateStoryDetails(Story story);
         void addStory(String storyID);
         void removeStory(String storyID);
         void moveStoryIndex(String storyID);
    }

}
