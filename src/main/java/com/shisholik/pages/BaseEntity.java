package com.shisholik.pages;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {
    private long id;
    private Date createdAt;
    private Date lastModified;

    public Date getLastModified() {
        if (lastModified == null) {
            return new Date(0);
        }
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    protected void onCreate() {
        createdAt = new Date();
    }
}
