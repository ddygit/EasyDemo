package com.example.administrator.easyshopdemo.model;

/**
 * Created by Administrator on 2016/11/23.
 */

public class PersonItem {
    private String title;
    private String content;

    public PersonItem(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
