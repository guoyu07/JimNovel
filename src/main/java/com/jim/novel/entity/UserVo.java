package com.jim.novel.entity;

import com.jim.novel.model.User;

/**
 * Created by run on 17/3/22.
 */
public class UserVo extends User{

    private String name;
    private String avaterUrl;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvaterUrl() {
        return avaterUrl;
    }

    public void setAvaterUrl(String avaterUrl) {
        this.avaterUrl = avaterUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
