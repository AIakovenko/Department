package com.bionic.iakovenko.department.dao.entity;

import java.sql.Date;

/**
 * @autor Alex Iakovenko
 * Date: 3/31/14
 * Time: 11:40 AM
 */
public class Request {
    private int requestID;
    private String personID;
    private short flatID;
    private short worksID;
    private java.sql.Date requestedTime;
    private short dispatcherID;

    public Request(){}

    public Request(int requestID, String personID, short flatID, short worksID, Date requestedTime,
                   short dispatcherID) {
        this.requestID = requestID;
        this.personID = personID;
        this.flatID = flatID;
        this.worksID = worksID;
        this.requestedTime = requestedTime;
        this.dispatcherID = dispatcherID;
    }

    /**
     * Returns request id number (unique identifier).
     * @return unique identifier of the request.
     */
    public int getRequestID() {
        return requestID;
    }

    /**
     * Sets request id number (unique identifier).
     * @param requestID unique identifier of request.
     */
    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    /**
     * Returns person id number (person who makes the request)
     * @return  unique identifier of the person.
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * Sets person id number (person who makes the request)
     * @param personID  unique identifier of the person.
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }

    /**
     * Returns apartment id number (apartment where work will be done).
     * @return  unique identifier of the apartment.
     */
    public short getFlatID() {
        return flatID;
    }

    /**
     * Sets apartment id number (apartment where work will be done).
     * @param flatID unique identifier of the apartment.
     */
    public void setFlatID(short flatID) {
        this.flatID = flatID;

    }

    /**
     * Returns work id number (work which must be done).
     * @return  unique identifier of the work.
     */
    public short getWorksID() {
        return worksID;

    }

    /**
     * Sets work id number (work which must be done).
     * @param worksID unique identifier of the work.
     */
    public void setWorksID(short worksID) {
        this.worksID = worksID;

    }

    /**
     * Returns value of date when the work must be done.
     * @return  value of date.
     */
    public Date getRequestedTime() {
        return requestedTime;

    }

    /**
     * Sets value of date when the work must be done.
     * @param requestedTime value of date.
     */
    public void setRequestedTime(Date requestedTime) {
        this.requestedTime = requestedTime;

    }

    /**
     * Returns id number of the dispatcher who should work this request on.
     * @return dispatcherID value dispatcher id number.
     */
    public short getDispatcherID() {
        return dispatcherID;
    }

    /**
     * Sets id number of the dispatcher who should work this request on.
     * @param dispatcherID value dispatcher id number.
     */
    public void setDispatcherID(short dispatcherID) {
        this.dispatcherID = dispatcherID;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()){
            return false;
        }
        Request request = (Request) obj;
        if (flatID != request.flatID  ||requestID != request.requestID ||
            worksID != request.worksID || !personID.equals(request.personID) ||
            !requestedTime.equals(request.requestedTime) || dispatcherID != request.dispatcherID){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result =  requestID;
        result = 31 * result + personID.hashCode();
        result = 31 * result + (int) flatID;
        result = 31 * result + (int) worksID;
        result = 31 * result + requestedTime.hashCode();
        result = 31 * result + (int) dispatcherID;
        return result;
    }

    @Override
    public String toString() {
        return  requestID + " " + personID + " " + flatID + " " + worksID + " " + requestedTime + " " + dispatcherID;
    }
}
