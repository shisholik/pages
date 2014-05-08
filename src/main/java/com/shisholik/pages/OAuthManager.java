package com.shisholik.pages;

import org.apache.cxf.rs.security.oauth2.common.AccessTokenRegistration;
import org.apache.cxf.rs.security.oauth2.common.Client;
import org.apache.cxf.rs.security.oauth2.common.OAuthPermission;
import org.apache.cxf.rs.security.oauth2.common.ServerAccessToken;
import org.apache.cxf.rs.security.oauth2.common.UserSubject;
import org.apache.cxf.rs.security.oauth2.grants.code.AuthorizationCodeDataProvider;
import org.apache.cxf.rs.security.oauth2.grants.code.AuthorizationCodeRegistration;
import org.apache.cxf.rs.security.oauth2.grants.code.ServerAuthorizationCodeGrant;
import org.apache.cxf.rs.security.oauth2.provider.OAuthServiceException;

import java.util.List;

public class OAuthManager implements AuthorizationCodeDataProvider {
    @Override
    public ServerAuthorizationCodeGrant createCodeGrant(AuthorizationCodeRegistration authorizationCodeRegistration) throws OAuthServiceException {
        return null;
    }

    @Override
    public ServerAuthorizationCodeGrant removeCodeGrant(String s) throws OAuthServiceException {
        return null;
    }

    @Override
    public Client getClient(String s) throws OAuthServiceException {
        return null;
    }

    @Override
    public ServerAccessToken createAccessToken(AccessTokenRegistration accessTokenRegistration) throws OAuthServiceException {
        return null;
    }

    @Override
    public ServerAccessToken getAccessToken(String s) throws OAuthServiceException {
        return null;
    }

    @Override
    public ServerAccessToken getPreauthorizedToken(Client client, List<String> strings, UserSubject userSubject, String s) throws OAuthServiceException {
        return null;
    }

    @Override
    public ServerAccessToken refreshAccessToken(Client client, String s, List<String> strings) throws OAuthServiceException {
        return null;
    }

    @Override
    public void removeAccessToken(ServerAccessToken serverAccessToken) throws OAuthServiceException {

    }

    @Override
    public List<OAuthPermission> convertScopeToPermissions(Client client, List<String> strings) {
        return null;
    }
}
