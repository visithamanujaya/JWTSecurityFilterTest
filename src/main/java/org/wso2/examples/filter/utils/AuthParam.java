package org.wso2.examples.filter.utils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Created by visitha on 1/27/16.
 */
@XmlType(name = "AuthParam")
@XmlAccessorType(XmlAccessType.FIELD)
public class AuthParam {
    @XmlElement(name = "roleName")
    private List<RoleName> roleName;
    @XmlElement(name = "urlPattern")
    private String urlPattern;

    public List<RoleName> getRoleName() {

        return roleName;
    }

    public void setRoleName(List<RoleName> roleName) {

        this.roleName = roleName;
    }

    public String getUrlPattern() {

        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {

        this.urlPattern = urlPattern;
    }

}
