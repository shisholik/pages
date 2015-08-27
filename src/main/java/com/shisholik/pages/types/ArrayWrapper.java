package com.shisholik.pages.types;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.shisholik.pages.annotations.IgnoreNested;
import com.shisholik.pages.api.IBaseResource;
import com.shisholik.pages.api.Views.ExtendedPublic;

import java.net.MalformedURLException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@JsonFilter("myFilter")
@IgnoreNested
public class ArrayWrapper<T> implements IBaseResource {
    private List<T> list;
    private int limit;
    private int offset;

    public ArrayWrapper(List<T> list) {
        this.list = list;
        limit = 5;
    }

    public ArrayWrapper(List<T> list, int limit, int offset) {
        this.list = list;
        this.limit = limit;
        this.offset = offset;
    }

    @JsonProperty
    public int getSize() {
        return list.size();
    }

    @JsonView(ExtendedPublic.class)
    public List<T> getItems() {
        if (offset > list.size()) {
            return Collections.emptyList();
        }
        int toIndex = offset + limit;
        toIndex = toIndex > list.size() ? list.size() : toIndex;
        return list.subList(offset, toIndex);
    }

    public String getNextLink() throws MalformedURLException {
        if (offset + limit < list.size()) {
            return "http://localhost:8080/t/users/" + "?limit=" + limit + "&offset=" + (offset + limit);
        } else {
            return null;
        }
    }

    public String getPrevLink() throws MalformedURLException {
        if (offset - limit > 0) {
            return "http://localhost:8080/t/users/" + "?limit=" + limit + "&offset=" + (offset - limit);
        } else {
            return null;
        }
    }

    @Override
    public Date getLastModificationDate() {
        return new Date();
    }
}
