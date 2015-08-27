package com.shisholik.pages.interceptors;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxrs.impl.MetadataMap;
import org.apache.cxf.jaxrs.interceptor.JAXRSOutInterceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import javax.ws.rs.core.MultivaluedMap;
import java.io.OutputStream;

public class ETagInterceptor extends AbstractPhaseInterceptor<Message> {
    public ETagInterceptor() {
        super(Phase.MARSHAL);
        //addAfter(JAXRSOutInterceptor.class.getName());
    }


    @Override
    public void handleMessage(Message message)  {
        /*MultivaluedMap<String, Object> headers = (MetadataMap<String, Object>) message.get(Message.PROTOCOL_HEADERS);

        //String s = ((OutputStream)message.getContent(OutputStream.class));

        if (headers == null) {
            headers = new MetadataMap<String, Object>();
        }

        //generate E-tag here
        String etag = "afawf";
        //
        String cc = "600";

        headers.add("E-Tag", etag);
        headers.add("Cache-Control", cc);
        message.put(Message.PROTOCOL_HEADERS, headers);*/
    }
}
