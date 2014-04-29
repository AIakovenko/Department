package com.bionic.iakovenko.department.manager;

import java.util.ResourceBundle;

/**
 * @autor Alex Iakovenko
 * Date: 4/8/14
 * Time: 10:58 PM
 */
public class ConfigurationManager {

    private static ConfigurationManager instance;
    private ResourceBundle resourceBundle;

    private static final String BUNDLE_NAME = "com.bionic.iakovenko.department.manager.database";
    public static final String DB_RESOURCE_NAME = "DB_RESOURCE_NAME";

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }

}
