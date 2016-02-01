# WebApp_with_JWTSecurityFilter_NewReadme


This is a filter which can be applied to j2ee security model of web applications. Basically main objective of this filter is to bypass the second login page for AppManager users. If you want to use secured web applications in in AppManager which is secured in j2ee security model you can use this filter to bypass the login page of the web application. What you have to do is copy .java files mentioned bellow to your webbApp.

JWTSecurityFilter.java
UserRoleRequestWrapper.java
SecurityInfo.java
SecurutyConstraintReader.java


Then go to web.xml file of the webb app and delete the <login-config> section and remove the authentication mechanism.

E.g Delete the part mentioned bellow.

  		<login-config>
			<auth-method>FORM</auth-method>
			<realm-name>file</realm-name>
			<form-login-config>
   			 	<form-login-page>/logon.jsp</form-login-page>
   			 	<form-error-page>/logonError.jsp</form-error-page>
			</form-login-config>
		</login-config>
Then create a securityinfo.xml file in the resources folder in the webb app. After that define security constraints which are defined in the web.xml.

E.g

Security constrains defined in the web.xml.

		<security-constraint>
			<display-name>SecurityConstraint</display-name>
			<web-resource-collection>
    				<web-resource-name>WRCollection</web-resource-name>
    				<url-pattern>/*</url-pattern>
			</web-resource-collection>
			<auth-constraint>
			      <role-name>loginUser</role-name>
			</auth-constraint>
		</security-constraint>
The way you want to define them in the securityinfo.xml ( Here do not keep – between words instead of that use camel case)

		<securityInfo id="1">
  			  <roleName>loginUser</roleName>
   			  <webResourceName>WRCollection</webResourceName>
   			  <urlPattern>/*</urlPattern>
				.
				.
				.
		</securityInfo>

Then copy Error.jsp to webapp folder, after that go to web.xml and define the filter as well as the error page as shown bellow.

E.g
		


		<filter>
 		 	<filter-name>f1</filter-name>
 		 	<filter-class>org.wso2.JWTSecurityFilter</filter-class>
		</filter>

		<filter-mapping>
  			<filter-name>f1</filter-name>
  			<url-pattern>/*</url-pattern>
		</filter-mapping>

		<error-page>
  			<error-code>403</error-code>
  			<location>/Error.jsp</location>
		</error-page>
The modifications are finished now you can build your webapp, deploy on tomcat and publish on AppManag
