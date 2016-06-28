
package com.ycombinator.news.hackernews.model.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Story implements Serializable {
    private String by;
    private long descendants;
    private long id;
    private List<Long> kids = new ArrayList<Long>();
    private long score;
    private long time;
    private String title;
    private String type;
    private String url;
    private String text;

    public Story(){

    }

    /**
     * return story item authors.
     * @return
     *     author's name
     */
    public String getBy() {
        return by;
    }

    /**
     * set item's author
     * @param by
     *     The by
     */
    public void setBy(String by) {
        this.by = by;
    }

    /**
     *  returns the number of comments
     * @return
     *     The descendants
     */
    public long getDescendants() {
        return descendants;
    }

    /**
     * set the number of comments
     * @param descendants
     *     The descendants
     */
    public void setDescendants(long descendants) {
        this.descendants = descendants;
    }

    /**
     *  return the story item id
     * @return
     *     The id of story item
     */
    public long getId() {
        return id;
    }

    /**
     * set story item id
     * @param id
     *     The id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The kids
     */
    public List<Long> getKids() {
        return kids;
    }

    /**
     * 
     * @param kids
     *     The kids
     */
    public void setKids(List<Long> kids) {
        this.kids = kids;
    }

    /**
     * 
     * @return
     *     The score
     */
    public long getScore() {
        return score;
    }

    /**
     * 
     * @param score
     *     The score
     */
    public void setScore(long score) {
        this.score = score;
    }

    /**
     * 
     * @return
     *     The time
     */
    public long getTime() {
        return time;
    }

    /**
     * 
     * @param time
     *     The time
     */
    public void setTime(long time) {
        this.time = time;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }


    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
