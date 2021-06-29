package com.company.people;

import java.util.concurrent.ThreadLocalRandom;

public class Programmer extends Worker{

    public boolean knowsBackend;
    public boolean knowsFrontend;
    public boolean knowsDatabase;
    public boolean knowsMobile;
    public boolean knowsWordpress;
    public boolean knowsPrestashop;
    public double accuracy;
    public double punctuality;

    @Override
    public String toString() {
        return "Programmer{" +
                super.toString()+
                ", knowsBackend=" + knowsBackend +
                ", knowsFrontend=" + knowsFrontend +
                ", knowsDatabase=" + knowsDatabase +
                ", knowsMobile=" + knowsMobile +
                ", knowsWordpress=" + knowsWordpress +
                ", knowsPrestashop=" + knowsPrestashop +
                ", salary=" + salary +
                "} \n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Programmer)) return false;
        Programmer that = (Programmer) o;
        return knowsBackend == that.knowsBackend && knowsFrontend == that.knowsFrontend && knowsDatabase == that.knowsDatabase && knowsMobile == that.knowsMobile && knowsWordpress == that.knowsWordpress && knowsPrestashop == that.knowsPrestashop && Double.compare(that.accuracy, accuracy) == 0 && Double.compare(that.punctuality, punctuality) == 0;
    }

    public Programmer(int idWorker) {
        super(idWorker);
        this.salary = Math.floor(ThreadLocalRandom.current().nextDouble(3000.0, 5001.0));
        this.accuracy = ThreadLocalRandom.current().nextInt(8, 11)/10.0;
        this.punctuality = ThreadLocalRandom.current().nextInt(8, 11)/10.0;
        this.knowsBackend = true;
        this.knowsFrontend = ThreadLocalRandom.current().nextInt(0, 2) == 1;
        this.knowsDatabase = ThreadLocalRandom.current().nextInt(0, 2) == 1;
        this.knowsMobile = ThreadLocalRandom.current().nextInt(0, 2) == 1;
        this.knowsPrestashop = ThreadLocalRandom.current().nextInt(0, 2) == 1;
        this.knowsWordpress = ThreadLocalRandom.current().nextInt(0, 2) == 1;
    }
}

