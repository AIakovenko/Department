package com.bionic.iakovenko.department.dao.entity;

import java.io.Serializable;

/**
 * Class binds persons and flats. It contains information about Person and Flat.
 * It describes dependencies between owners (persons) and flats which persons
 * own. Instead, each flat will has to have several owners.
 *
 * @autor Alex Iakovenko Date: 3/31/14 Time: 11:34 AM
 */
public class Owner implements Serializable {

    private String personID;
    private short flatID;

    public Owner() {}

    public Owner(String personID, short flatID) {
        this.personID = personID;
        this.flatID = flatID;
    }

    /**
     * Returns owner's (person's) id.
     *
     * @return ID number;
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * Sets owner's (person's) id.
     *
     * @param personID ID number;
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }

    /**
     * Returns id number of the flat which owner possesses.
     *
     * @return ID number;
     */
    public short getFlatID() {
        return flatID;
    }

    /**
     * Sets id number of the flat which owner possesses.
     *
     * @param flatID number;
     */
    public void setFlatID(short flatID) {
        this.flatID = flatID;
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
        Owner owner = (Owner) obj;
        if (flatID != owner.flatID || !personID.equals(owner.personID)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = personID.hashCode();
        result = 31 * result + (int) flatID;
        return result;
    }

    @Override
    public String toString() {
        return personID + " " + flatID;
    }
}
