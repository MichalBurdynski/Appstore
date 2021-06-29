package com.company.people;

import java.util.concurrent.ThreadLocalRandom;

public class CoWorker {
    public final int idWorker;
    public final Integer typeOfWorker;
    public double salaryPerDay;
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
        return "CoWorker{" +
                "idWorker=" + idWorker +
                ", typeOfWorker=" + typeOfWorker +
                ", salaryPerDay=" + salaryPerDay +
                ", knowsBackend=" + knowsBackend +
                ", knowsFrontend=" + knowsFrontend +
                ", knowsDatabase=" + knowsDatabase +
                ", knowsMobile=" + knowsMobile +
                ", knowsWordpress=" + knowsWordpress +
                ", knowsPrestashop=" + knowsPrestashop +
                "}\n";
    }

    public CoWorker(int idWorker, Integer typeOfWorker) {
        this.idWorker = idWorker;
        this.typeOfWorker = typeOfWorker;
        switch (this.typeOfWorker)
        {
            //the best
            case 1:
            {
                this.salaryPerDay = Math.floor(ThreadLocalRandom.current().nextDouble(100.0,201.0));
                this.accuracy = 1.0;
                this.punctuality = 1.0;
                this.knowsBackend = true;
                this.knowsFrontend = ThreadLocalRandom.current().nextInt(0, 2) == 1;
                this.knowsDatabase = ThreadLocalRandom.current().nextInt(0, 2) == 1;
                this.knowsMobile = ThreadLocalRandom.current().nextInt(0, 2) == 1;
                this.knowsPrestashop = ThreadLocalRandom.current().nextInt(0, 2) == 1;
                this.knowsWordpress = ThreadLocalRandom.current().nextInt(0, 2) == 1;
            }
            break;

            //medium
            case 2:
            {
                this.salaryPerDay = Math.floor(ThreadLocalRandom.current().nextDouble(50.0,101.0));
                this.accuracy = 0.9;
                this.punctuality = 1.0;
                this.knowsBackend = true;
                this.knowsFrontend = ThreadLocalRandom.current().nextInt(0, 2) == 1;
                this.knowsDatabase = ThreadLocalRandom.current().nextInt(0, 2) == 1;
                this.knowsMobile = ThreadLocalRandom.current().nextInt(0, 2) == 1;
                this.knowsPrestashop = ThreadLocalRandom.current().nextInt(0, 2) == 1;
                this.knowsWordpress = ThreadLocalRandom.current().nextInt(0, 2) == 1;
            }
            break;

            //the worst
            case 3:
            {
                this.salaryPerDay = Math.floor(ThreadLocalRandom.current().nextDouble(30.0,51.0));
                this.accuracy = 0.8;
                this.punctuality = 0.8;
                this.knowsBackend = true;
                this.knowsFrontend = ThreadLocalRandom.current().nextInt(0, 2) == 1;
                this.knowsDatabase = ThreadLocalRandom.current().nextInt(0, 2) == 1;
                this.knowsMobile = ThreadLocalRandom.current().nextInt(0, 2) == 1;
                this.knowsPrestashop = ThreadLocalRandom.current().nextInt(0, 2) == 1;
                this.knowsWordpress = ThreadLocalRandom.current().nextInt(0, 2) == 1;
            }
            break;
            default: break;

        }
    }
}
