package com.example.administrator.easyshopdemo.model;

/**
 * Created by Administrator on 2016/11/19.
 */

public class Easy {

    /**
     * username : username
     * password : password
     */

    private String username;
    private String password;

    public Easy(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Easy{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
