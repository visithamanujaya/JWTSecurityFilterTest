package org.wso2.examples.filter.utils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Created by visitha on 1/27/16.
 */
@XmlType (name = "SecurityData")
@XmlAccessorType(XmlAccessType.FIELD)
public class SecurityData {

    @XmlElement(name = "authParam")
    private List<AuthParam> authParams;

    public List<AuthParam> getAuthParams() {
        return authParams;
    }
    public void setAuthParams(List<AuthParam> authParams){
        this.authParams = authParams;
    }
}
