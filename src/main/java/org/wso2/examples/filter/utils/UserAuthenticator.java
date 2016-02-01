package org.wso2.examples.filter.utils;

import java.util.Iterator;
import java.util.List;

/**
 * Created by visitha on 1/27/16.
 */
public class UserAuthenticator {

    private List<AuthParam> authParamList;

    public UserAuthenticator(List<AuthParam> authParamList) {
        this.authParamList = authParamList;

    }

public boolean authenticateUser(List<String> rolesList, String uri) {
    AuthParam authParam = uriMatch(uri);
    if (authParam != null) {
        List<RoleName> roleNames = authParam.getRoleName();
        if (roleCheck(roleNames, rolesList)) {
            return true;
        }
        return false;
    }
    return true;
}
    public AuthParam uriMatch(String uri) {
        Iterator<AuthParam> iterator = authParamList.iterator();
        while (iterator.hasNext()) {
            AuthParam authParams = iterator.next();
            String urlPattern = authParams.getUrlPattern();
            if (uri.contains(urlPattern)) {
                return authParams;
            }

        }
        return null;
    }

    public boolean roleCheck(List<RoleName> roleName, List<String> rolesList) {
        Iterator<RoleName> roleNameIterator = roleName.iterator();
        while (roleNameIterator.hasNext()) {
            String authenticatedRole = roleNameIterator.next().getRoleName();
            if (rolesList.contains(authenticatedRole)) {
                return true;
            }
        }
        return false;
    }

}
