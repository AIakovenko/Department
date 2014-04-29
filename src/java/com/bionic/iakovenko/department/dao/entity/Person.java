package com.bionic.iakovenko.department.dao.entity;

import java.io.Serializable;

/**
 * Class describes person who can get requests to department for works.
 *
 * @autor Alex Iakovenko Date: 3/28/14 Time: 5:23 PM
 */
public class Person implements Serializable {

    private String personID;
    private String familyName;
    private String givenName;
    private String additionalName;
    private String login;

    public Person() {
    }

    public Person(String personID, String familyName, String givenName, String additionalName, String login) {
        this.personID = personID;
        this.familyName = familyName;
        this.givenName = givenName;
        this.additionalName = additionalName;
        this.login = login;
    }

    /**
     * Returns the value of a person's passport number.
     *
     * @return passport number.
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * Sets the value of a person's passport number.
     *
     * @param personID passport number.
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }

    /**
     * Returns the person's surname (Family name).
     *
     * @return person's surname.
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * Sets the person's surname (Family name).
     *
     * @param familyName person's family name.
     */
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    /**
     * Returns the person's name.
     *
     * @return person's name.
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * Sets the person's name.
     *
     * @param givenName person's name.
     */
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    /**
     * Returns the person's last name.
     *
     * @return person's last name.
     */
    public String getAdditionalName() {
        return additionalName;
    }

    /**
     * Sets the person's additional name.
     *
     * @param additionalName person's additional name.
     */
    public void setAdditionalName(String additionalName) {
        this.additionalName = additionalName;
    }

    /**
     * Returns the person's login name.
     *
     * @return login name.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the person's login name.
     *
     * @param login login name.
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
        Person person = (Person) obj;
        if (!familyName.equals(person.familyName) || !additionalName.equals(person.additionalName)
                || !givenName.equals(person.givenName) || !personID.equals(person.personID)
                || !login.equals(person.login)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = personID.hashCode();
        result = 31 * result + familyName.hashCode();
        result = 31 * result + givenName.hashCode();
        result = 31 * result + additionalName.hashCode();
        result = 31 * result + login.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return personID + " " + familyName + " " + givenName + " " + additionalName;
    }
}
