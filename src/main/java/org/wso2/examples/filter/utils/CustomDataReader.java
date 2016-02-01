package org.wso2.examples.filter.utils;

/**
 * Created by visitha on 1/22/16.
 */

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;

public class CustomDataReader {

    public CustomData getCustomData(InputStream inputStream) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CustomData.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            CustomData que = (CustomData) jaxbUnmarshaller.unmarshal(inputStream);
            return que;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Deprecated
    public CustomData getCustomData() {

        ClassLoader classLoader = getClass().getClassLoader();
        String filePath = "CustomData.xml";
        File file = new File(classLoader.getResource(filePath).getFile());

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CustomData.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            CustomData que = (CustomData) jaxbUnmarshaller.unmarshal(file);
            return que;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}