package org.wso2.examples.servlets;

import javax.xml.bind.DatatypeConverter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.wso2.examples.filter.utils.AuthParam;
import org.wso2.examples.filter.utils.CustomData;
import org.wso2.examples.filter.utils.CustomDataReader;
import org.wso2.examples.filter.utils.UserAuthenticator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class JWTSecurityFilter implements Filter {

    private static final String JWT_TOKEN_SUBJECT = "sub";
    private static final String JWT_TOKEN_USER_ROLES = "http://wso2.org/claims/role";
    private static final Logger log = Logger.getLogger((JWTSecurityFilter.class.getName()));//LogFactory.getLog(JWTSecurityFilter.class);
    private CustomData customData;

    public void init(FilterConfig filterConfig) throws ServletException {

        InputStream inStream = filterConfig.getServletContext().getResourceAsStream("/WEB-INF/CustomData.xml");
        CustomDataReader customDataReader = new CustomDataReader();
        customData = customDataReader.getCustomData(inStream);

    }

    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {

        PrintWriter out = resp.getWriter();
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String jwtHeader = request.getHeader("x-jwt-assertion");
        List<AuthParam> rolesAndNames = customData.getSecurityData().getAuthParams();

        //This is to handle null requests if request is not null it will continue,
        if (jwtHeader == null) {
            log.log(Level.WARNING,"Miss match in user name or user roles");
            chain.doFilter(req, resp);
            return;
        }

        String[] jwtArray = jwtHeader.split("\\.");
        if (jwtArray.length != 3) {
            //format of the JWT header is invalid so we send unautherize
            response.sendError(403);
            return;
        }

        byte[] decodedHeader = DatatypeConverter.parseBase64Binary(jwtArray[0]);
        byte[] decodedPayload = DatatypeConverter.parseBase64Binary(jwtArray[1]);
        byte[] decodedSignature = DatatypeConverter.parseBase64Binary(jwtArray[2]);
        String headerString = new String(decodedHeader);
        String payloadString = new String(decodedPayload);
        String signatureString = new String(decodedSignature);
        JSONObject payLoad = null;

        try {
            payLoad = (JSONObject) new JSONParser().parse(payloadString);
        } catch (ParseException e) {
            log.log(Level.SEVERE,"Error while creating JASON object from payloadString", e);
            response.sendError(422, "Invalid JWT Header");
        }

        String userName = (String) payLoad.get(JWT_TOKEN_SUBJECT);
        String roles = (String) payLoad.get(JWT_TOKEN_USER_ROLES);
        List<String> rolesList = new ArrayList<String>(Arrays.asList(roles.split(",")));

        UserRoleRequestWrapper userRoleRequestWrapper = new UserRoleRequestWrapper(userName,
                                                                                   rolesList,
                                                                                   request);

        Iterator<AuthParam> iterator = rolesAndNames.iterator();
        String requestedUri = request.getServletPath();

        UserAuthenticator userAuthenticator = new UserAuthenticator(rolesAndNames);
        if (userAuthenticator.authenticateUser(rolesList, requestedUri)) {
            chain.doFilter(userRoleRequestWrapper, resp);
        } else {
            response.sendError(403);
            return;
        }

    }

    public void destroy() {
    }
}