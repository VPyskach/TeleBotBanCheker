package com.job.telebotv2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Url {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String url;
    private boolean status;

    public Url() {
    }

    public Url(String url, boolean status) {
        this.url = url;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
