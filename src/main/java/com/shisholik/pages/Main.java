package com.shisholik.pages;

import com.shisholik.pages.api.AuthService;
import com.shisholik.pages.user.UserEntity;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.service.invoker.BeanInvoker;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        sf.setResourceClasses(AuthService.class, UserEntity.class);
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
