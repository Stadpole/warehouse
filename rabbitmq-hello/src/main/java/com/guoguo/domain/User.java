package com.guoguo.domain;

import java.io.Serializable;

/**
 * Created by mengying on 2018/1/14.
 */
public class User implements Serializable{
    private String username;
    private String passwoed;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswoed() {
        return passwoed;
    }

    public void setPasswoed(String passwoed) {
        this.passwoed = passwoed;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", passwoed='" + passwoed + '\'' +
                '}';
    }
}
