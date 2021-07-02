package com.company.people;

import com.company.project.Project;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Programmer extends Worker {

    public final boolean knowsBackend;
    public final boolean knowsFrontend;
    public final boolean knowsDatabase;
    public final boolean knowsMobile;
    public final boolean knowsWordpress;
    public final boolean knowsPrestashop;
    public final double accuracy;
    public final double punctuality;

    public Programmer(int idWorker) {
        super(idWorker);
        this.salary = Math.floor(ThreadLocalRandom.current().nextDouble(3000.0, 5001.0));
        this.accuracy = ThreadLocalRandom.current().nextInt(8, 11) / 10.0;
        this.punctuality = ThreadLocalRandom.current().nextInt(8, 11) / 10.0;
        this.knowsBackend = true;
        this.knowsFrontend = ThreadLocalRandom.current().nextInt(0, 2) == 1;
        this.knowsDatabase = ThreadLocalRandom.current().nextInt(0, 2) == 1;
        this.knowsMobile = ThreadLocalRandom.current().nextInt(0, 2) == 1;
        this.knowsPrestashop = ThreadLocalRandom.current().nextInt(0, 2) == 1;
        this.knowsWordpress = ThreadLocalRandom.current().nextInt(0, 2) == 1;
    }

    @Override
    public @NotNull String toString() {
        return "\nProgrammer\n" +
                super.toString() + "\n" +
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
}

