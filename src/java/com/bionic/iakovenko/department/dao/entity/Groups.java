package com.bionic.iakovenko.department.dao.entity;

import java.io.Serializable;

/**
 * Class describes groups which users belong to.
 *
 * @autor Alex Iakovenko Date: 4/9/14 Time: 6:36 PM
 */
public class Groups implements Serializable {

    private byte group_ID;
    private String description;

    public Groups() {
    }

    public Groups(byte group_ID, String description) {
        this.group_ID = group_ID;
        this.description = description;
    }

    /**
     * Returns number of group user belong to.
     *
     * @return group id number.
     */
    public byte getGroup_ID() {
        return group_ID;

    }

    /**
     * Sets number of group user belong to.
     *
     * @param group_ID group id number.
     */
    public void setGroup_ID(byte group_ID) {
        this.group_ID = group_ID;

    }

    /**
     * Returns the description which describes the group.
     *
     * @return group description.
     */
    public String getDescription() {
        return description;

    }

    /**
     * Sets the description which describes of the group.
     *
     * @param description description of the group.
     */
    public void setDescription(String description) {
        this.description = description;

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
        Groups groups = (Groups) obj;
        if (group_ID != groups.group_ID || !description.equals(groups.description)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) group_ID;
        result = 31 * result + description.hashCode();
        return result;
    }
}
