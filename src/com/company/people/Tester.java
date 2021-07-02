package com.company.people;

import com.company.project.Project;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Tester extends Worker {

    public Tester(int idWorker) {
        super(idWorker);
        this.salary = Math.floor(ThreadLocalRandom.current().nextDouble(1500.0, 2501.0));
    }

    @Override
    public @NotNull String toString() {
        return "\nTester\n" + super.toString() + "\n" +
                "Month salary=" + salary +
                "\n";
    }

    @Override
    public int GenerateProject(ArrayList<Project> unfinishedProjects, int projectIndex, LocalDate actualDate) {
        return 0;
    }

}
