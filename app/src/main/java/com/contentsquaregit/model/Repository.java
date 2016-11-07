package com.contentsquaregit.model;

import java.io.Serializable;

/**
 * Created by thiba_000 on 03/11/2016.
 */

public class Repository implements Serializable {

    private String id;
    private String name;
    private String full_name;
    private User owner;
    private String description;
    private String forks_count;
    private String updated_at;
    private String stargazers_count;
    private String watchers_count;
    private String subscribers_count;
    private String html_url;

    public Repository() {
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getSubscribers_count() {
        return subscribers_count;
    }

    public void setSubscribers_count(String subscribers_count) {
        this.subscribers_count = subscribers_count;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getForks_count() {
        return forks_count;
    }

    public void setForks_count(String forks_count) {
        this.forks_count = forks_count;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(String stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public String getWatchers_count() {
        return watchers_count;
    }

    public void setWatchers_count(String watchers_count) {
        this.watchers_count = watchers_count;
    }
}
