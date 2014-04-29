package com.bionic.iakovenko.department.dao.entity;

/**
 * Class describes a type of works which person will choose.
 *
 * @autor Alex Iakovenko Date: 3/28/14 Time: 8:24 PM
 */
public class Works {

    private short worksID;
    private String name;
    private String description;

    public Works() {
    }

    public Works(short worksID, String name, String description) {
        this.worksID = worksID;
        this.name = name;
        this.description = description;
    }

    /**
     * Returns work ID (unique identifier)
     *
     * @return work ID
     */
    public short getWorksID() {
        return worksID;

    }

    /**
     * Sets work ID (unique identifier)
     *
     * @param worksID work ID.
     */
    public void setWorksID(short worksID) {
        this.worksID = worksID;

    }

    /**
     * Returns the name of work.
     *
     * @return name of work.
     */
    public String getName() {
        return name;

    }

    /**
     * Sets the name of work.
     *
     * @param name name of work.
     */
    public void setName(String name) {
        this.name = name;

    }

    /**
     * Returns work description.
     *
     * @return work description.
     */
    public String getDescription() {
        return description;

    }

    /**
     * Sets work description.
     *
     * @param description work description.
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
        Works works = (Works) obj;
        if (worksID != works.worksID || !name.equals(works.name)) {
            return false;
        }
        if (description != null ? !description.equals(works.description) : works.description != null) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) worksID;
        result = 31 * result + name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return worksID + " " + name;
    }
}
