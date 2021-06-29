package com.company.project;

import com.company.people.CoWorker;
import com.company.people.Worker;

import java.time.LocalDate;

public class WorkDay {
    public Integer typeOfWork;
    public Integer typeOfWorker;
    public LocalDate dateWhenWorksStarts;
    public LocalDate dateWhenWorksFinishes;
    public Boolean isTested;
    public Worker worker;
    public CoWorker coWorker;

    public WorkDay(Integer typeOfWork, Integer typeOfWorker, LocalDate dateWhenWorksStarts, LocalDate dateWhenWorksFinishes, Boolean isTested) {
        this.typeOfWork = typeOfWork;
        this.typeOfWorker = typeOfWorker;
        this.dateWhenWorksStarts = dateWhenWorksStarts;
        this.dateWhenWorksFinishes = dateWhenWorksFinishes;
        this.isTested = isTested;
        this.worker = null;
        this.coWorker = null;
    }
}
