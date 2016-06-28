package com.ycombinator.news.hackernews.activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.ycombinator.news.hackernews.R;
import com.ycombinator.news.hackernews.model.data.Story;
import com.ycombinator.news.hackernews.utils.TimeAgo;

public class StoryDetailActivity extends AppCompatActivity {
    ProgressDialog pg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);


        //pressed back button
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //toolbar setup
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);


        getSupportActionBar().setTitle("Hacker News Details");

        //pull the data passed by the intent
        Story story =  (Story)getIntent().getSerializableExtra("data");
        //hide button
        findViewById(R.id.btnlaunch).setVisibility(View.GONE);
        TextView txtpoint,txtTitle,txtAuthor,txtComments,txtTime;
        txtpoint = (TextView)findViewById(R.id.txtStoryPoints);
        txtTitle = (TextView)findViewById(R.id.txtStoryTitle);
        txtAuthor = (TextView)findViewById(R.id.txtStoryAuthor);
        txtComments = (TextView)findViewById(R.id.txtStoryComments);
        txtTime = (TextView)findViewById(R.id.txtStoryTime);
        txtTitle.setText(story.getTitle());
        txtpoint.setText(String.valueOf(story.getScore()));
        String comments = getResources().getQuantityString(R.plurals.comments,
                story.getDescendants() < 2 ? 1 : 2,story.getDescendants());
        txtComments.setText(comments);
        txtAuthor.setText("By "+story.getBy());
        TimeAgo timeAgo = new TimeAgo(getResources());
        String timeformatted =  timeAgo.time(story.getTime());
        txtTime.setText(timeformatted);
         pg = ProgressDialog.show(this, "",
                "Loading. Please wait...", false,true);
        WebView webView = (WebView) findViewById(R.id.webView);
        final String currentUrl = "https://news.ycombinator.com/item?id="+story.getId();
        webView.loadUrl("https://news.ycombinator.com/item?id="+story.getId());
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.equals(currentUrl)){
                    view.loadUrl(url);
                }
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if(!pg.isShowing()){
                    pg.show();
                }

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(pg!=null){
                    pg.dismiss();
                }
            }
        });


    }

}
