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
        String x = "";
        switch (typeOfWorker)
        {
            case 1 : x = "Type of worker: the best student\n"; break;
            case 2 : x = "Type of worker: medium student\n"; break;
            case 3 : x = "Type of worker: the worst student\n"; break;
            default: break;
        }
        return  "\nidWorker=" + idWorker + '\n' +
                x +
                "Daily wage: " + salaryPerDay + '\n' +
                "knowsBackend: " + knowsBackend + '\n' +
                "knowsFrontend: " + knowsFrontend + '\n' +
                "knowsDatabase: " + knowsDatabase + '\n' +
                "knowsMobile: " + knowsMobile + '\n' +
                "knowsWordpress: " + knowsWordpress + '\n' +
                "knowsPrestashop: " + knowsPrestashop + '\n';
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
