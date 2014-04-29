package com.bionic.iakovenko.department.dao.entity;

import java.io.Serializable;

/**
 * Class describes an apartment. It contains information about address, building
 * where the apartment is.
 *
 * @autor Alex Iakovenko Date: 3/28/14 Time: 6:44 PM
 */
public class Flat implements Serializable {

    private short flatID;
    private String address;
    private short building;
    private short apartment;

    public Flat() {
    }

    public Flat(short flatID, String address, short building, short apartment) {
        this.flatID = flatID;
        this.address = address;
        this.building = building;
        this.apartment = apartment;
    }

    /**
     * Returns apartment ID (unique identifier)
     *
     * @return apartment ID
     */
    public short getFlatID() {
        return flatID;
    }

    /**
     * Sets apartment ID
     *
     * @param flatID apartment id (unique value)
     */
    public void setFlatID(short flatID) {
        this.flatID = flatID;
    }

    /**
     * Returns the name of a street.
     *
     * @return street name.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets street name where apartment is.
     *
     * @param address street name.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the number of a building.
     *
     * @return building number.
     */
    public short getBuilding() {
        return building;
    }

    /**
     * Sets the number of a building.
     *
     * @building  building number.
     */
    public void setBuilding(short building) {
        this.building = building;
    }

    /**
     * Returns apartment number
     *
     * @return apartment number.
     */
    public short getApartment() {
        return apartment;
    }

    /**
     * Sets apartment number.
     *
     * @param apartment number.
     */
    public void setApartment(short apartment) {
        this.apartment = apartment;
    }

    /**
     * Returns number of rooms into the apartment.
     *
     * @return number of rooms.
     */

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
        Flat flat = (Flat) obj;
        if (apartment != flat.apartment || flatID != flat.flatID
                || building != flat.building
                || !address.equals(flat.address)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) flatID;
        result = 31 * result + address.hashCode();
        result = 31 * result + (int) building;
        result = 31 * result + (int) apartment;

        return result;
    }

    @Override
    public String toString() {
        return flatID + " " + address + " " + building + " " + apartment;
    }
}
