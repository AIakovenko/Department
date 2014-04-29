package com.bionic.iakovenko.department.dao.entity;

import java.io.Serializable;

/**
 * @autor Alex Iakovenko Date: 3/31/14 Time: 11:35 AM
 */
public class Plan implements Serializable {

    private int requestID;
    private short workerID;

    public Plan() {
    }

    public Plan(int requestID, short workerID) {
        this.requestID = requestID;
        this.workerID = workerID;
    }

    /**
     * Returns unique identifier of the request.
     *
     * @return request id.
     */
    public int getRequestID() {
        return requestID;

    }

    /**
     * Sets unique identifier of the request.
     *
     * @param requestID unique identifier of the request.
     */
    public void setRequestID(int requestID) {
        this.requestID = requestID;

    }

    /**
     * Returns unique identifier of the worker who will be engaged for current
     * work.
     *
     * @return worker id.
     */
    public short getWorkerID() {
        return workerID;

    }

    /**
     * Sets unique identifier of the worker who will be engaged for current
     * work.
     *
     * @param workerID unique identifier of the worker.
     */
    public void setWorkerID(short workerID) {
        this.workerID = workerID;

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
        Plan plan = (Plan) obj;
        if (requestID != plan.requestID || workerID != plan.workerID) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = requestID;
        result = 31 * result + (int) workerID;
        return result;
    }

    @Override
    public String toString() {
        return requestID + " " + workerID;
    }
}
