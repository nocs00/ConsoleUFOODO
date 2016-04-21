package com.ufood.properties;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by pdudenkov on 21.04.2016.
 */
public abstract class MyProperties {
    private static Properties properties;

    public static Properties getProps() {
        properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("constants.properties"));
        } catch (IOException e) {
            System.err.println("Unable to load properties file. "+e);
        }
        return properties;
    }

}
