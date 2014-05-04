package com.shisholik.pages;

import com.shisholik.pages.filters.FilterManager;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.BeanPropertyWriter;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;

public class FieldsFilter
        extends SimpleBeanPropertyFilter {

    @Override
    public void serializeAsField(Object bean, JsonGenerator jgen,
                                 SerializerProvider provider, BeanPropertyWriter writer)
            throws Exception {

        FilterManager defaultFilterManager = (FilterManager) PhaseInterceptorChain.getCurrentMessage().getExchange().get("fields");

        if (defaultFilterManager.contains(writer.getName())) {
            defaultFilterManager.push(writer.getName());
            writer.serializeAsField(bean, jgen, provider);
            defaultFilterManager.pop();
        }
    }
}