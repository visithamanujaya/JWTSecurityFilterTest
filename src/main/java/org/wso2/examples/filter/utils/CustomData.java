package org.wso2.examples.filter.utils;

/**
 * Created by visitha on 1/22/16.
 */

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "customData")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomData {
    @XmlElement (name = "securityData")
    private SecurityData securityData;

    public CustomData() {
    }

    public CustomData(int id, SecurityData securityData) {
        super();
        this.securityData = securityData;
    }

    public SecurityData getSecurityData() {
        return securityData;
    }

    public void setSecurityData(SecurityData securityData) {
        this.securityData = securityData;
    }
}
