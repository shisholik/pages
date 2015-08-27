package com.shisholik.pages;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import com.shisholik.pages.api.AuthService;
import com.shisholik.pages.api.BaseResource;

import com.shisholik.pages.interceptors.ETagInterceptor;
import com.shisholik.pages.interceptors.GameObjectResponseHandler;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.jaxrs.model.wadl.WadlGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws JsonMappingException {

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
        objectMapper.setSerializationInclusion(Include.NON_EMPTY);
        objectMapper.setSerializationInclusion(Include.NON_NULL);

        AnnotationIntrospector interceptors = new JaxbAnnotationIntrospector(TypeFactory.defaultInstance());
        //objectMapper.setAnnotationIntrospector(interceptors);

        jacksonJaxbJsonProvider.setMapper(objectMapper);

       //JsonSchema jsonSchema = objectMapper.generateJsonSchema(CustomStructure.class);

        WadlGenerator wadlGenerator = new WadlGenerator();
        wadlGenerator.setLinkJsonToXmlSchema(true);
        wadlGenerator.setAddResourceAndMethodIds(true);
        wadlGenerator.setIgnoreMessageWriters(false);
        wadlGenerator.setUseJaxbContextForQnames(false);
        //wadlGenerator.setExternalLinks(Arrays.asList("http://rtb.com"));

        //SimpleBeanPropertyFilter.filterOutAllExcept()

        sf.setProviders(Arrays.asList(jacksonJaxbJsonProvider, wadlGenerator, new GameObjectResponseHandler()));


        FieldsInterceptor fieldsInterceptor = new FieldsInterceptor();
        List inInterceptors = new ArrayList();
        inInterceptors.add(fieldsInterceptor);

        sf.setInInterceptors(inInterceptors);

        List outInterceptors = new ArrayList();
        outInterceptors.add(new ETagInterceptor());
        sf.setOutInterceptors(outInterceptors);

        sf.create();
    }
}
