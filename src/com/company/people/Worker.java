package com.company.people;

public abstract class Worker {
    public final int idWorker;
    public double salary;

    public Worker(int idWorker) {
        this.idWorker = idWorker;
    }

    @Override
    public String toString() {
        return "idWorker=" + idWorker;
    }
}
