package com.shisholik.pages.interceptors;

import com.shisholik.pages.api.IBaseResource;
import org.apache.commons.codec.binary.Base64;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.apache.cxf.jaxrs.ext.ResponseHandler;
import org.apache.cxf.jaxrs.model.OperationResourceInfo;
import org.apache.cxf.message.Message;
import org.joda.time.DateTimeUtils;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class GameObjectResponseHandler implements ResponseHandler {
    @Context
    MessageContext context;

    private long milliseconds = DateTimeUtils.currentTimeMillis();

    @Override
    public Response handleResponse(Message message, OperationResourceInfo ori, Response response) {
        // replace an output stream
        if (response.getEntity() instanceof IBaseResource) {
            IBaseResource entity = (IBaseResource) response.getEntity();

            String etag = "";
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                byte[] md5s = md5.digest(entity.getLastModificationDate().toString().getBytes());

                etag = new String(Base64.encodeBase64(md5s));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            EntityTag entityTag = new EntityTag(etag);
            CacheControl cc = new CacheControl();
            cc.setMaxAge(1);

            ResponseBuilder builder = context.getRequest().evaluatePreconditions(new Date(milliseconds), entityTag);

            if (builder == null) {
                builder = Response.ok(response.getEntity());
            } else {
                builder = Response.notModified();
            }

            return builder.cacheControl(cc).tag(entityTag).lastModified(new Date(milliseconds)).build();
        }

        return response;
    }
}
