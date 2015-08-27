package com.shisholik.pages.api;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.shisholik.pages.BaseEntity;
import org.apache.cxf.jaxrs.model.wadl.XMLName;

import javax.ws.rs.GET;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@JsonFilter("myFilter")
@XmlRootElement
@XMLName
public class BaseResource<T extends BaseEntity> implements IBaseResource {
    private T baseEntity;

    public BaseResource() {
    }

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

    @GET
    @JsonIgnore
    public BaseResource returnThis() {
        return this;
    }

    @JsonIgnore
    @Override
    public Date getLastModificationDate() {
        return baseEntity.getLastModified();
    }
}
