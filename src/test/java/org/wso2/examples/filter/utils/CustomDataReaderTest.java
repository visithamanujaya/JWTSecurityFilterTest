package org.wso2.examples.filter.utils;

import junit.framework.TestCase;

import java.io.InputStream;

/**
 * Created by visitha on 1/27/16.
 */
public class CustomDataReaderTest extends TestCase {

    public void testGetCustomData()
            throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();


        InputStream inputStream = classLoader.getResourceAsStream("CustomData.xml");
        CustomDataReader customDataReader = new CustomDataReader();
        CustomData customData = customDataReader.getCustomData(inputStream);
        assertNotNull(customData);
        assertNotNull(customData.getSecurityData());
        assertNotNull((customData.getSecurityData().getAuthParams()));
        assertNotNull((customData.getSecurityData().getAuthParams().get(0)));
        assertNotNull((customData.getSecurityData().getAuthParams().get(0).getRoleName()));
        assertNotNull((customData.getSecurityData().getAuthParams().get(0).getRoleName().get(0)));
        assertNotNull((customData.getSecurityData().getAuthParams().get(1).getRoleName().get(0)));
        assertEquals(2,customData.getSecurityData().getAuthParams().size());
        assertEquals(2,customData.getSecurityData().getAuthParams().get(0).getRoleName().size());
       assertEquals("admin",customData.getSecurityData().getAuthParams().get(0).getRoleName().get(0).getRoleName());

    }
}