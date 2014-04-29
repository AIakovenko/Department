package com.bionic.iakovenko.department.dao.entity;

/**
 * Class describes a worker who provides a service.
 *
 * @autor Alex Iakovenko Date: 3/28/14 Time: 7:08 PM
 */
public class Worker {

    private short workerID;
    private String name;
    private String specialization;

    public Worker() {}

    public Worker(short workerID, String name, String specialization) {
        this.workerID = workerID;
        this.name = name;
        this.specialization = specialization;
    }

    /**
     * Returns worker ID (unique identifier)
     *
     * @return worker ID
     */
    public short getWorkerID() {
        return workerID;

    }

    /**
     * Sets worker ID (unique identifier)
     *
     * @param workerID worker id.
     */
    public void setWorkerID(short workerID) {
        this.workerID = workerID;

    }

    /**
     * Returns worker name.
     *
     * @return worker name.
     */
    public String getName() {
        return name;

    }

    /**
     * Sets worker name.
     *
     * @param name worker name.
     */
    public void setName(String name) {
        this.name = name;

    }

    /**
     * Returns worker specialization.
     *
     * @return worker specialization.
     */
    public String getSpecialization() {
        return specialization;

    }

    /**
     * Sets worker specialization.
     *
     * @param specialization worker specialization.
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;

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

        Worker worker = (Worker) obj;

        if (workerID != worker.workerID || !name.equals(worker.name)
                || !specialization.equals(worker.specialization)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) workerID;
        result = 31 * result + name.hashCode();
        result = 31 * result + specialization.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return workerID + " " + name + " " + specialization;
    }
}
