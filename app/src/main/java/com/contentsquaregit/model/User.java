package com.contentsquaregit.model;

import java.io.Serializable;

/**
 * Created by thiba_000 on 03/11/2016.
 */

public class User implements Serializable {

    private String login;
    private String id;
    private String avatar_url;

    public User(){}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
