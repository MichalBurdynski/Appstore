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

    //typeofWork
    //1 - backend
    //2 - frontend
    //3 - database
    //4 - wordpress
    //5 - prestashop
    //6 - mobile
    //7 - tests

    //typeOfWorker
    //1 - company owner
    //2 - tester
    //3 - coWorker
    //4 - programmer


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
