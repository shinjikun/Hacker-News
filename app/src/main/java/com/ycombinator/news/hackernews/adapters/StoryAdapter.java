package com.ycombinator.news.hackernews.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ycombinator.news.hackernews.R;
import com.ycombinator.news.hackernews.activity.StoryDetailActivity;
import com.ycombinator.news.hackernews.model.data.Story;
import com.ycombinator.news.hackernews.utils.TimeAgo;

import java.util.ArrayList;

/**
 * Created by leonardoilagan on 27/06/2016.
 */

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {

    private   ArrayList<Story> arryList;
    Context context;
    TimeAgo timeAgo;
    public StoryAdapter(ArrayList<Story> arryList, Context context){
        this.arryList = arryList;
        this.context = context;
        timeAgo = new TimeAgo(context.getResources());

    }

    @Override
    public StoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View item =  LayoutInflater.from(parent.getContext()).inflate(R.layout.view_story_item,parent,false);
        return new StoryViewHolder(item);
    }

    @Override
    public void onBindViewHolder(StoryViewHolder holder, int position) {
         Story story = arryList.get(position);
         holder.txtTitle.setText(story.getTitle());
         holder.txtpoint.setText(String.valueOf(story.getScore()));
         String comments = context.getResources().getQuantityString(R.plurals.comments,
                story.getDescendants() < 2 ? 1 : 2,story.getDescendants());
         holder.txtComments.setText(comments);
         holder.txtAuthor.setText("By "+story.getBy());
            System.out.println(story.getTime());
         String timeformatted =  timeAgo.time(story.getTime());
        holder.txtTime.setText(timeformatted);
    }

    @Override
    public int getItemCount() {
        return this.arryList.size();
    }

     class StoryViewHolder extends RecyclerView.ViewHolder{
         TextView txtpoint,txtTitle,txtAuthor,txtComments,txtTime;
         Button btnLink;
         StoryViewHolder(View itemView) {
            super(itemView);
             txtpoint = (TextView)itemView.findViewById(R.id.txtStoryPoints);
             txtTitle = (TextView)itemView.findViewById(R.id.txtStoryTitle);
             txtAuthor = (TextView)itemView.findViewById(R.id.txtStoryAuthor);
             txtComments = (TextView)itemView.findViewById(R.id.txtStoryComments);
             txtTime = (TextView)itemView.findViewById(R.id.txtStoryTime);
             btnLink = (Button) itemView.findViewById(R.id.btnlaunch);
             //when the button open link was click open the url link using a intent
             btnLink.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Story story  = arryList.get(getAdapterPosition());
                     String url =  story.getUrl();
                     Intent i = new Intent(Intent.ACTION_VIEW);
                     i.setData(Uri.parse(url));
                     context.startActivity(i);
                 }
             });
             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Story story  = arryList.get(getAdapterPosition());
                     Intent intent = new Intent(context, StoryDetailActivity.class);
                     intent.putExtra("data",story);
                     context.startActivity(intent);
                 }
             });

        }
    }
}
