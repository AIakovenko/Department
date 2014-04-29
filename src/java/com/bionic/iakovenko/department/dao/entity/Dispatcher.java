package com.bionic.iakovenko.department.dao.entity;

import java.io.Serializable;

/**
 * @autor Alex Iakovenko Date: 3/28/14 Time: 7:18 PM
 */
public class Dispatcher implements Serializable {

    private short dispatcherID;
    private String name;
    private String login;

    public Dispatcher() {
    }

    public Dispatcher(short dispatcherID, String name, String login) {
        this.dispatcherID = dispatcherID;
        this.name = name;
        this.login = login;
    }

    /**
     * Returns dispatcher ID (unique identifier)
     *
     * @return dispatcher ID
     */
    public short getDispatcherID() {
        return dispatcherID;
    }

    /**
     * Sets dispatcher ID (unique identifier)
     *
     * @param dispatcherID unique ID of the dispatcher.
     */
    public void setDispatcherID(short dispatcherID) {
        this.dispatcherID = dispatcherID;
    }

    /**
     * Returns dispatcher name.
     *
     * @return dispatcher name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets dispatcher name.
     *
     * @param name of the dispatcher.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns dispatcher login name.
     *
     * @return dispatcher login name.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets dispatcher login name.
     *
     * @param login of the dispatcher.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Dispatcher that = (Dispatcher) obj;
        if (dispatcherID != that.dispatcherID || !name.equals(that.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) dispatcherID;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return dispatcherID + " " + name;
    }
}
