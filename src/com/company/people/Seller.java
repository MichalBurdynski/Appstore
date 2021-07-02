package com.company.people;

import com.company.project.Project;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Seller extends Worker {

    public int daysOfWorks;

    public Seller(int idWorker) {
        super(idWorker);
        this.salary = Math.floor(ThreadLocalRandom.current().nextDouble(2500.0, 4001.0));
        this.daysOfWorks = 0;
    }

    @Override
    public @NotNull String toString() {
        return "\nSeller\n" + super.toString() + "\n" +
                "Month salary=" + salary +
                "\n";
    }

    //OK
    public int GenerateProject(@NotNull ArrayList<Project> projects, int projectIndex, LocalDate date) {
        if (daysOfWorks == 4) {
            Project project = new Project(projectIndex, 2, date);
            projects.add(project);
            projectIndex++;
            daysOfWorks = 0;
        } else {
            daysOfWorks++;
        }
        return projectIndex;
    }
}
