package com.company.people;

import com.company.project.Project;

import java.time.LocalDate;
import java.util.ArrayList;
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
        return "\nProgrammer\n" +
                super.toString()+ "\n" +
                "knowsBackend=" + knowsBackend + "\n" +
                "knowsFrontend=" + knowsFrontend + "\n" +
                "knowsDatabase=" + knowsDatabase + "\n" +
                "knowsMobile=" + knowsMobile + "\n" +
                "knowsWordpress=" + knowsWordpress + "\n" +
                "knowsPrestashop=" + knowsPrestashop + "\n" +
                "Month salary=" + salary +
                "\n";
    }

    @Override
    public int GenerateProject(ArrayList<Project> unfinishedProjects, int projectIndex, LocalDate actualDate) {
        return 0;
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

