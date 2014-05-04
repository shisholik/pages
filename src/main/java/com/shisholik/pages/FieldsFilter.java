package com.shisholik.pages;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.shisholik.pages.filters.FilterManager;
import org.apache.cxf.phase.PhaseInterceptorChain;


public class FieldsFilter
        extends SimpleBeanPropertyFilter {

    @Override
    protected boolean include(BeanPropertyWriter beanPropertyWriter) {
        return true;
    }

    @Override
    protected boolean include(PropertyWriter propertyWriter) {
        return true;
    }

    @Override
    public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer) throws Exception {
        FilterManager defaultFilterManager = (FilterManager) PhaseInterceptorChain.getCurrentMessage().getExchange().get("fields");

        if (defaultFilterManager.contains(writer.getName())) {
            defaultFilterManager.push(writer.getName());
            writer.serializeAsField(pojo, jgen, provider);
            defaultFilterManager.pop();
        }
    }

}