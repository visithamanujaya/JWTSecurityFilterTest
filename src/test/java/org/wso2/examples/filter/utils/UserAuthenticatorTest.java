package org.wso2.examples.filter.utils;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class UserAuthenticatorTest extends TestCase {

    public void testMultipleUsersInJWTMultipleUsersInXml() throws Exception {

        RoleName roleName1 = new RoleName();
        RoleName roleName2 = new RoleName();
        List<RoleName> roleNames = new ArrayList<RoleName>();
        roleName1.setRoleName("admin");
        roleName2.setRoleName("visitha");
        roleNames.add(roleName1);
        roleNames.add(roleName2);
        AuthParam authParam = new AuthParam();
        List<AuthParam> authParamList = new ArrayList<AuthParam>();
        authParam.setRoleName(roleNames);
        authParam.setUrlPattern("/greeting");
        authParamList.add(authParam);
        List<String> authRoles = new ArrayList<String>();
        authRoles.add("admin");
        authRoles.add("visitha");
        authRoles.add("Minura");
        authRoles.add("Ravindu");
        authRoles.add("Chandupa");
        authRoles.add("WIjesinghe");
        authRoles.add("Manel");
        String uri = "first/greeting";
        UserAuthenticator userAuthenticator = new UserAuthenticator(authParamList);
        userAuthenticator.authenticateUser( authRoles, uri);
        assertEquals(true,userAuthenticator.authenticateUser( authRoles, uri) );
    }

    public void testNoUserInJWTMultipleUsersInXml() throws Exception {

        RoleName roleName1 = new RoleName();
        RoleName roleName2 = new RoleName();
        List<RoleName> roleNames = new ArrayList<RoleName>();
        roleName1.setRoleName("admin");
        roleName2.setRoleName("visitha");
        roleNames.add(roleName1);
        roleNames.add(roleName2);
        AuthParam authParam = new AuthParam();
        List<AuthParam> authParamList = new ArrayList<AuthParam>();
        authParam.setRoleName(roleNames);
        authParam.setUrlPattern("/greeting");
        authParamList.add(authParam);
        List<String> authRoles = new ArrayList<String>();
        String uri = "first/greeting";
        UserAuthenticator userAuthenticator = new UserAuthenticator(authParamList);
        userAuthenticator.authenticateUser( authRoles, uri);
        assertEquals(false,userAuthenticator.authenticateUser( authRoles, uri) );
    }

    public void testOneUserInJWTMultipleUsersInXml() throws Exception {

        RoleName roleName1 = new RoleName();
        RoleName roleName2 = new RoleName();
        List<RoleName> roleNames = new ArrayList<RoleName>();
        roleName1.setRoleName("admin");
        roleName2.setRoleName("visitha");
        roleNames.add(roleName1);
        roleNames.add(roleName2);
        AuthParam authParam = new AuthParam();
        List<AuthParam> authParamList = new ArrayList<AuthParam>();
        authParam.setRoleName(roleNames);
        authParam.setUrlPattern("/greeting");
        authParamList.add(authParam);
        List<String> authRoles = new ArrayList<String>();
        authRoles.add("admin");
        String uri = "first/greeting";
        UserAuthenticator userAuthenticator = new UserAuthenticator(authParamList);
        userAuthenticator.authenticateUser( authRoles, uri);
        assertEquals(true,userAuthenticator.authenticateUser( authRoles, uri) );
    }

    public void testMultipleUserInJWTNoUserInXml() throws Exception {

        RoleName roleName1 = new RoleName();
        RoleName roleName2 = new RoleName();
        List<RoleName> roleNames = new ArrayList<RoleName>();
        roleNames.add(roleName1);
        roleNames.add(roleName2);
        AuthParam authParam = new AuthParam();
        List<AuthParam> authParamList = new ArrayList<AuthParam>();
        authParam.setRoleName(roleNames);
        authParam.setUrlPattern("/greeting");
        authParamList.add(authParam);
        List<String> authRoles = new ArrayList<String>();
        authRoles.add("admin");
        authRoles.add("visitha");
        authRoles.add("Minura");
        String uri = "first/greeting";
        UserAuthenticator userAuthenticator = new UserAuthenticator(authParamList);
        userAuthenticator.authenticateUser( authRoles, uri);
        assertEquals(false,userAuthenticator.authenticateUser( authRoles, uri) );
    }


    public void testOneUserInJWTOneUserInXml() throws Exception {

        RoleName roleName1 = new RoleName();
        List<RoleName> roleNames = new ArrayList<RoleName>();
        roleName1.setRoleName("admin");
        roleNames.add(roleName1);
        AuthParam authParam = new AuthParam();
        List<AuthParam> authParamList = new ArrayList<AuthParam>();
        authParam.setRoleName(roleNames);
        authParam.setUrlPattern("/greeting");
        authParamList.add(authParam);
        List<String> authRoles = new ArrayList<String>();
        authRoles.add("admin");
        String uri = "first/greeting";
        UserAuthenticator userAuthenticator = new UserAuthenticator(authParamList);
        userAuthenticator.authenticateUser( authRoles, uri);
        assertEquals(true,userAuthenticator.authenticateUser( authRoles, uri) );
    }

    public void testNoUserInJWTNoUserInXml() throws Exception {
        List<RoleName> roleNames = new ArrayList<RoleName>();
        AuthParam authParam = new AuthParam();
        List<AuthParam> authParamList = new ArrayList<AuthParam>();
        authParam.setRoleName(roleNames);
        authParam.setUrlPattern("/greeting");
        authParamList.add(authParam);
        List<String> authRoles = new ArrayList<String>();
        String uri = "first/greeting";
        UserAuthenticator userAuthenticator = new UserAuthenticator(authParamList);
        userAuthenticator.authenticateUser( authRoles, uri);
        assertEquals(false,userAuthenticator.authenticateUser( authRoles, uri) );
    }
    public void testWithoutUri() throws Exception {

        RoleName roleName1 = new RoleName();
        RoleName roleName2 = new RoleName();
        List<RoleName> roleNames = new ArrayList<RoleName>();
        roleName1.setRoleName("admin");
        roleName2.setRoleName("visitha");
        roleNames.add(roleName1);
        roleNames.add(roleName2);
        AuthParam authParam = new AuthParam();
        List<AuthParam> authParamList = new ArrayList<AuthParam>();
        authParam.setRoleName(roleNames);
        authParam.setUrlPattern("/greeting");
        authParamList.add(authParam);
        List<String> authRoles = new ArrayList<String>();
        authRoles.add("admin");
        authRoles.add("visitha");
        authRoles.add("Minura");
        authRoles.add("Ravindu");
        authRoles.add("Chandupa");
        authRoles.add("WIjesinghe");
        authRoles.add("Manel");
        String uri = "";
        UserAuthenticator userAuthenticator = new UserAuthenticator(authParamList);
       // userAuthenticator.authenticateUser();
        assertEquals(true,userAuthenticator.authenticateUser( authRoles, uri) );
    }
    public void testWithUnMatchingUri() throws Exception {

        RoleName roleName1 = new RoleName();
        RoleName roleName2 = new RoleName();
        List<RoleName> roleNames = new ArrayList<RoleName>();
        roleName1.setRoleName("admin");
        roleName2.setRoleName("visitha");
        roleNames.add(roleName1);
        roleNames.add(roleName2);
        AuthParam authParam = new AuthParam();
        List<AuthParam> authParamList = new ArrayList<AuthParam>();
        authParam.setRoleName(roleNames);
        authParam.setUrlPattern("/greeting");
        authParamList.add(authParam);
        List<String> authRoles = new ArrayList<String>();
        authRoles.add("admin");
        authRoles.add("visitha");
        authRoles.add("Minura");
        authRoles.add("Ravindu");
        authRoles.add("Chandupa");
        authRoles.add("WIjesinghe");
        authRoles.add("Manel");
        String uri = "Test/Admin";
        UserAuthenticator userAuthenticator = new UserAuthenticator(authParamList);
       // userAuthenticator.authenticateUser();
        assertEquals(true,userAuthenticator.authenticateUser( authRoles, uri) );
    }
    public void testMultipleUsersInJWTMultipleUsersInXmlUnmatching() throws Exception {

        RoleName roleName1 = new RoleName();
        RoleName roleName2 = new RoleName();
        List<RoleName> roleNames = new ArrayList<RoleName>();
        roleName1.setRoleName("adminx");
        roleName2.setRoleName("visithay");
        roleNames.add(roleName1);
        roleNames.add(roleName2);
        AuthParam authParam = new AuthParam();
        List<AuthParam> authParamList = new ArrayList<AuthParam>();
        authParam.setRoleName(roleNames);
        authParam.setUrlPattern("/greeting");
        authParamList.add(authParam);
        List<String> authRoles = new ArrayList<String>();
        authRoles.add("admin");
        authRoles.add("visitha");
        authRoles.add("Minura");
        authRoles.add("Ravindu");
        authRoles.add("Chandupa");
        authRoles.add("WIjesinghe");
        authRoles.add("Manel");
        String uri = "first/greeting";
        UserAuthenticator userAuthenticator = new UserAuthenticator(authParamList);
       // userAuthenticator.authenticateUser();
        assertEquals(false,userAuthenticator.authenticateUser( authRoles, uri) );
    }


}