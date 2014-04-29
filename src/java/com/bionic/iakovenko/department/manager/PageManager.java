
package com.bionic.iakovenko.department.manager;

import java.util.ResourceBundle;

/**
 *
 * @autor Alex Iakovenko
 * Date: Apr 18, 2014
 * Time: 11:47:50 AM
 */
public class PageManager {
   
    public static final String REGISTRATION_PATH = "REGISTRATION_PATH";
    public static final String REGISTRATION_COMMIT_PATH = "REGISTRATION_COMMIT_PATH";
    public static final String REGISTRATION_CONFIRM_PATH = "REGISTRATION_CONFIRM_PATH";
    public static final String ENTER_PAGE_PATH = "ENTER_PAGE_PATH";
    public static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
    public static final String MAIN_CLIENT_PAGE_PATH = "MAIN_CLIENT_PAGE_PATH";
    public static final String MAIN_DISPATCHER_PAGE_PATH = "MAIN_DISPATCHER_PAGE_PATH";
    public static final String LOGIN_PAGE_PATH = "LOGIN_PAGE_PATH";
    public static final String PREPARE_REQUEST_PATH = "PREPARE_REQUEST_PATH";
    public static final String SEND_REQUEST_PATH = "SEND_REQUEST_PATH";
    public static final String FORMING_WORKGROUP_PATH = "FORMING_WORKGROUP_PATH";
    public static final String COMMIT_WORK_GROUP_PATH = "COMMIT_WORK_GROUP_PATH";
    public static final String CHOICE_UNDONE_REQUEST = "CHOICE_UNDONE_REQUEST";
    public static final String SEARCH_REQUEST_PATH = "SEARCH_REQUEST_PATH";
    public static final String COMMIT_REEQUEST_PATH = "COMMIT_REEQUEST_PATH";
    public static final String SHOW_DETAILS_PATH = "SHOW_DETAILS_PATH";
    public static final String SHOW_OWN_REQUESTS_PATH = "SHOW_OWN_REQUESTS_PATH";
    
    private static final String BUNDLE_NAME = "com.bionic.iakovenko.department.manager.pages";
    private static PageManager instance;
    private ResourceBundle resourceBundle;
    
    public static PageManager getInstance() {
        if (instance == null) {
            instance = new PageManager();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }

}
