package com.ycombinator.news.hackernews.utils;

/**
 * Created by leonardoilagan on 26/06/2016.
 */

public class Constants {

 private static String URL="https://hacker-news.firebaseio.com/";
 private static String VERSION="v0";
 public static String API_URL= URL+ VERSION + "/";

 public static String API_TOP_STORIES = API_URL +"topstories";

 public static String API_ITEM_INFO =  API_URL+"item/";
}
