package com.company.people;

import com.company.project.Project;

import java.time.LocalDate;
import java.util.ArrayList;

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

    public abstract int GenerateProject(ArrayList<Project> unfinishedProjects, int projectIndex, LocalDate actualDate);
}
