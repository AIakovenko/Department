package com.bionic.iakovenko.department.dao.entity;

import java.io.Serializable;

/**
 * Class describes users who is allowed data manipulation. It contains
 * information about login and password each user.
 *
 * @autor Alex Iakovenko Date: 4/9/14 Time: 6:19 PM
 */
public class Users implements Serializable {

    private String login;
    private String password;
    private byte groupID;

    public Users() {
    }

    public Users(String login, String password, byte groupID) {
        this.login = login;
        this.password = password;
        this.groupID = groupID;
    }

    /**
     * Returns user's login name.
     *
     * @return user's login name.
     */
    public String getLogin() {

        return login;
    }

    /**
     * Sets user's login name.
     *
     * @param login of the user.
     */
    public void setLogin(String login) {

        this.login = login;
    }

    /**
     * Returns user's password.
     *
     * @return user's password.
     */
    public String getPassword() {

        return password;
    }

    /**
     * Sets user's password name.
     *
     * @param password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns group id user belong to .
     *
     * @return group id.
     */
    public byte getGroup_ID() {
        return groupID;
    }

    /**
     * Sets group id user belong to .
     *
     * @param groupID;
     */
    public void setGroup_ID(byte groupID) {
        this.groupID = groupID;
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
        Users users = (Users) obj;
        if (groupID != users.groupID || !login.equals(users.login)
                || !password.equals(users.password)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (int) groupID;
        return result;
    }

    @Override
    public String toString() {
        return "Login: " + login + ", GroupID = " + groupID;
    }
}
