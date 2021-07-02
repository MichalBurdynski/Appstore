package com.company.project;

import com.company.people.CoWorker;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;

public class WorkDay {
    public final Integer typeOfWorker;
    public final LocalDate dateWhenWorksFinishes;
    public Boolean isTested;
    public @Nullable CoWorker coWorker;


    //typeOfWorker
    //1 - company owner
    //2 - tester
    //3 - coWorker
    //4 - programmer

    public WorkDay(Integer typeOfWorker, LocalDate dateWhenWorksFinishes, Boolean isTested) {
        this.typeOfWorker = typeOfWorker;
        this.dateWhenWorksFinishes = dateWhenWorksFinishes;
        this.isTested = isTested;
        this.coWorker = null;
    }
}
