package com.shisholik.pages.api;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonView;
import com.shisholik.pages.BaseEntity;


import java.util.Date;

@JsonFilter("myFilter")
public class BaseResource<T extends BaseEntity> {
    private T baseEntity;

    public BaseResource(T baseEntity) {
        this.baseEntity = baseEntity;
    }

    protected T getType() {
        return baseEntity;
    }

    @JsonView(Views.Public.class)
    public long getId() {
        return baseEntity.getId();
    }


    @JsonView(Views.Public.class)
    public Date getCreatedAt() {
        return baseEntity.getCreatedAt();
    }
}
