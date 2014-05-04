package com.shisholik.pages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.shisholik.pages.api.AuthService;
import com.shisholik.pages.api.BaseResource;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        sf.setResourceClasses(AuthService.class, BaseResource.class);
        sf.setResourceProvider(AuthService.class, new SingletonResourceProvider(new AuthService()));
        sf.setAddress("http://localhost:8080/");
        //sf.setServiceClass(AuthService.class);

        //sf.getServiceFactory().setInvoker(new BeanInvoker(new AuthService()));
        JacksonJaxbJsonProvider jacksonJaxbJsonProvider = new JacksonJaxbJsonProvider();
        ObjectMapper objectMapper = new ObjectMapper();

        FilterProvider filters = new SimpleFilterProvider().addFilter("myFilter",
                new FieldsFilter());

        objectMapper.setFilters(filters);
        jacksonJaxbJsonProvider.setMapper(objectMapper);

        //SimpleBeanPropertyFilter.filterOutAllExcept()
        sf.setProviders(Arrays.asList(jacksonJaxbJsonProvider));
        sf.setInInterceptors(Arrays.asList(new FieldsInterceptor()));

        sf.create();
    }
}
