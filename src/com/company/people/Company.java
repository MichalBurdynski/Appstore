package com.company.people;

import com.company.project.Project;
import com.company.project.WorkDay;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Company {
    public String companyName;
    public Double availableCash;
    public ArrayList<Project> unfinishedProjects;
    public ArrayList<Project> finishedProjects;
    public ArrayList<CoWorker> coWorkers;
    public ArrayList<Worker> workers;
    public ArrayList<Seller> sellers;
    public ArrayList<Tester> testers;
    public static Double DEFAULT_START_CASH = 5000.0;
    public int daysToNewProject = 0;
    public int daysToPayOffBills = 0;
    public LocalDate actualDate;
    public int projectIndex;

    public Company(String companyName, LocalDate date) {
        this.companyName = companyName;
        this.availableCash = DEFAULT_START_CASH;
        this.unfinishedProjects = new ArrayList<>();
        this.finishedProjects = new ArrayList<>();
        this.coWorkers = new ArrayList<>();
        this.workers = new ArrayList<>();
        this.actualDate = date;

    }

    //OK
    public void setProject(int index, ArrayList<Project> projects) {
        try {
            int indexInArrayList = -1;
            boolean isValid = false;
            for (Project project: projects) {
                if (project.projectIndex == index) {
                    isValid = true;
                    indexInArrayList = projects.indexOf(project);
                    break;
                }
            }
            if (!isValid) {
                throw new Exception();
            }
            else {
                this.unfinishedProjects.add(projects.get(indexInArrayList));
                projects.remove(indexInArrayList);
                System.out.println("Contract signed.\n");
            }
        }
        catch (Exception e)
        {
            System.out.println("There is no project with the index: "+ index+ "./n");
        }
    }

    public void releaseProject(int index) {
        try {
            if ((this.unfinishedProjects.isEmpty())) {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            System.out.println("Nie masz żadnych nieukończonych projektów");
        }

        try {
            if (index < 0) {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            System.out.println("Nie ma takiego projektu.");
        }
        try {
                if (index > this.unfinishedProjects.size() - 1) {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            System.out.println("Nie ma takiego projektu");
        }


        {
        Project releasedProject = this.unfinishedProjects.get(index);
        releasedProject.dateWhenClientGetBackProject = actualDate;
        checkingProject(releasedProject);
        this.unfinishedProjects.remove(index);
        this.finishedProjects.add(releasedProject);
        }

    }

    //OK
    public void workOnProjectAlone(int index) {
        try {
            if (this.unfinishedProjects.isEmpty()) {
                throw new Exception();
            } else {
                int indexInArrayList = -1;
                boolean isValid = false;
                for (Project project : this.unfinishedProjects) {
                    if (project.projectIndex == index) {
                        isValid = true;
                        indexInArrayList = this.unfinishedProjects.indexOf(project);
                        break;
                    }
                }
                if (!isValid) {
                    throw new Exception();
                } else {
                    if (this.unfinishedProjects.get(indexInArrayList).backendDays > 0) {
                        this.unfinishedProjects.get(indexInArrayList).backendDays -=1;
                        WorkDay workDay = new WorkDay(1, 1, actualDate, actualDate, false);
                        this.unfinishedProjects.get(indexInArrayList).workDays.add(workDay);
                        System.out.println("New code has been written.");
                    } else if (this.unfinishedProjects.get(indexInArrayList).frontendDays > 0) {
                        this.unfinishedProjects.get(indexInArrayList).frontendDays -= 1;
                        WorkDay workDay = new WorkDay(2, 1, actualDate, actualDate, false);
                        this.unfinishedProjects.get(indexInArrayList).workDays.add(workDay);
                        System.out.println("New code has been written.");
                    } else if (this.unfinishedProjects.get(indexInArrayList).databaseDays > 0) {
                        this.unfinishedProjects.get(indexInArrayList).databaseDays -= 1;
                        WorkDay workDay = new WorkDay(3, 1, actualDate, actualDate, false);
                        this.unfinishedProjects.get(indexInArrayList).workDays.add(workDay);
                        System.out.println("New code has been written.");
                    } else if (this.unfinishedProjects.get(indexInArrayList).wordpressDays > 0) {
                        this.unfinishedProjects.get(indexInArrayList).wordpressDays -= 1;
                        WorkDay workDay = new WorkDay(4, 1, actualDate, actualDate, false);
                        this.unfinishedProjects.get(indexInArrayList).workDays.add(workDay);
                        System.out.println("New code has been written.");
                    } else if (this.unfinishedProjects.get(indexInArrayList).prestashopDays > 0) {
                        this.unfinishedProjects.get(indexInArrayList).prestashopDays -= 1;
                        WorkDay workDay = new WorkDay(5, 1, actualDate, actualDate, false);
                        this.unfinishedProjects.get(indexInArrayList).workDays.add(workDay);
                        System.out.println("New code has been written.");
                    } else
                    {
                        throw new Exception();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("The project does not exist or is finished.");
        }
    }

    //OK
    public int lookingForClient(ArrayList<Project> projects, int projectIndex) {
        Project project;
        if (this.daysToNewProject == 4) {
            project = new Project(projectIndex, 1, actualDate);
            projects.add(project);
            System.out.println("There is a new project!!!\n");
            projectIndex++;
            this.daysToNewProject = 0;
        } else {
            this.daysToNewProject += 1;
        }
        return projectIndex;
    }


    public void testingProject(int index) {
        try {
            if (this.unfinishedProjects.isEmpty()) {
                throw new Exception();
            } else {
                int indexInArrayList = -1;
                boolean isValid = false;
                for (Project project : this.unfinishedProjects) {
                    if (project.projectIndex == index) {
                        isValid = true;
                        indexInArrayList = this.unfinishedProjects.indexOf(project);
                        break;
                    }
                }
                if (!isValid) {
                    throw new Exception();
                } else {
                    int daysLeft = 1;
                    for (WorkDay workDay : this.unfinishedProjects.get(indexInArrayList).workDays) {
                        if (!workDay.isTested && daysLeft != 0) {
                            workDay.isTested = true;
                            WorkDay workDay1 = new WorkDay(7, 1, actualDate, actualDate, true);
                            daysLeft -= 1;
                            this.unfinishedProjects.get(indexInArrayList).workDays.add(workDay1);
                            this.unfinishedProjects.get(indexInArrayList).daysToFinishTesting -= 1;
                            System.out.println("New code has been tested.");
                            if (this.unfinishedProjects.get(indexInArrayList).daysToFinishTesting > 0)
                            {
                                System.out.println("Remaining days to finish testing the project: "+this.unfinishedProjects.get(indexInArrayList).daysToFinishTesting);
                            }
                            else
                            {
                                System.out.println("Project successfully tested.");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("The project does not exist or is finished.");
        }
    }









    //OK
    public void setCoWorker(int index, ArrayList<CoWorker> coWorkers) {
        try {
            int indexInArrayList = -1;
            boolean isValid = false;
            for (CoWorker coWorker : coWorkers) {
                if (coWorker.idWorker == index) {
                    isValid = true;
                    indexInArrayList = coWorkers.indexOf(coWorker);
                    break;
                }
            }
            if (!isValid) {
                throw new Exception();
            } else {
                CoWorker coWorker = coWorkers.get(indexInArrayList);
                coWorkers.remove(indexInArrayList);
                this.coWorkers.add(coWorker);
                this.availableCash = this.availableCash - 100.0;
                System.out.println("New associate successfully hired.\n");
            }
        } catch (Exception e) {
            System.out.println("There is no coworker with the idWorker: " + index + "\n");
        }
    }

    //OK
    public int setWorker(int index, ArrayList<Worker> workers, int workerIndex) {
        try {
            int indexInArrayList = -1;
            boolean isValid = false;
            for (Worker worker : workers) {
                if (worker.idWorker == index) {
                    isValid = true;
                    indexInArrayList = workers.indexOf(worker);
                    break;
                }
            }
            if (!isValid) {
                throw new Exception();
            } else {
                Worker worker = workers.get(indexInArrayList);
                workers.remove(indexInArrayList);
                this.workers.add(worker);
                this.availableCash = this.availableCash - 100.0;
                System.out.println("New employee successfully hired.\n");
                if (worker.getClass() == Programmer.class) {
                    Programmer programmer = new Programmer(workerIndex);
                    workers.add(programmer);
                    workerIndex++;
                } else if (worker.getClass() == Tester.class) {
                    Tester tester = new Tester(workerIndex);
                    workers.add(tester);
                    workerIndex++;
                } else if (worker.getClass() == Seller.class) {
                    Seller seller = new Seller(workerIndex);
                    workers.add(seller);
                    workerIndex++;
                }
            }
        }
        catch (Exception e) {
            System.out.println("There is no candidate with the idWorker: " + index + "\n");
        }
        return workerIndex;
    }

    //OK
    public void dismissCoWorker(int index, ArrayList<CoWorker> coWorkers) {
        try {
            int indexInArrayList = -1;
            boolean isValid = false;
            for (CoWorker coWorker : this.coWorkers) {
                if (coWorker.idWorker == index) {
                    isValid = true;
                    indexInArrayList = this.coWorkers.indexOf(coWorker);
                    break;
                }
            }
            if (!isValid) {
                throw new Exception();
            } else {
                CoWorker coWorker = this.coWorkers.get(indexInArrayList);
                this.coWorkers.remove(indexInArrayList);
                coWorkers.add(coWorker);
                this.availableCash = this.availableCash - 100.0;
                System.out.println("The associate successfully dismissed.\n");
            }
        } catch (Exception e) {
            System.out.println("There is no coworker with the idWorker: " + index + "\n");
        }
    }

    //OK
    public void dismissWorker(int index, ArrayList<Worker> workers) {
        try {
            boolean isValid = false;
            int indexInArrayList = -1;
            for (Worker worker : this.workers) {
                if (worker.idWorker == index) {
                    isValid = true;
                    indexInArrayList = this.workers.indexOf(worker);
                    break;

                }
            }
            if (!isValid) {
                throw new Exception();
            } else {
                Worker worker = this.workers.get(indexInArrayList);
                this.workers.remove(indexInArrayList);
                workers.add(worker);
                this.availableCash = this.availableCash - 100.0;
                System.out.println("The employee successfully dismissed.\n");
            }
        }
        catch (Exception e) {
            System.out.println("There is no employee with the idWorker: " + index + "\n");
        }
    }

    //OK
    public void payOffBills() {
        {
            this.daysToPayOffBills += 1;
        }
    }

    @Override
    public String toString() {
        return "Company{" +
                "ownerName='" + companyName + '\'' +
                ", availableCash=" + availableCash +
                ", unfinishedProjects=" + unfinishedProjects +
                ", finishedProjects=" + finishedProjects +
                ", coWorkers=" + coWorkers +
                ", Workers=" + workers +
                ", daysToNewProject=" + daysToNewProject +
                ", daysToPayOffBills=" + daysToPayOffBills +
                ", actualDate=" + actualDate +
                '}';
    }

    public void finishingDay() {

        double runningCostsCoWorkers = 0.0;
        double runningCostsWorkers = 0.0;
        double runningCostsOverall;
        double incomeTax = 0.0;

        //Odczytanie dnia i numeru dnia miesiąca
        DayOfWeek day = this.actualDate.getDayOfWeek();
        Integer dayNumber = day.getValue();

        //tydzień pracy współpracowników
        if (!(dayNumber == 6 || dayNumber == 7)) {
            //pracują wspolpracownicy
            if (!(coWorkers.isEmpty()) && !(this.unfinishedProjects.isEmpty())) {
                for (CoWorker coWorker : coWorkers) {
                    for (Project project : unfinishedProjects) {
                        workOnProject(project, coWorker);
                    }
                }
            }
            //programisci
            if (!(workers.isEmpty()) && !(this.unfinishedProjects.isEmpty())) {
                for (Worker worker : workers) {
                    for (Project project : unfinishedProjects) {
                        if (worker instanceof Programmer)
                            workOnProject(project, (Programmer) worker);
                    }
                }
            }
            //testerzy
            if (!(testers.isEmpty()) && !(this.unfinishedProjects.isEmpty())) {
                for (Worker worker : testers) {
                    for (Project project : unfinishedProjects) {
                        if (worker instanceof Tester)
                            testingProject(project, (Tester) worker);
                    }
                }
            }

            //sellerzy
            lookingForProjectBySeller();
        }

        //sprawdzenie czy kasa przyszła i zaksiegowanie w saldzie
        for (Project project : this.finishedProjects) {
            if (project.dateWhenProjectIsPaidByClient.equals(actualDate)) {
                this.availableCash += project.projectRealPrice;
            }
        }

        //Zakonczenie miesiaca
        int dayOfMonth = this.actualDate.getDayOfMonth();
        int monthNumber = this.actualDate.getMonthValue();
        if (((dayOfMonth == 28) && (monthNumber == 2)) ||
                ((dayOfMonth == 30) && ((monthNumber == 4) || (monthNumber == 6) || (monthNumber == 9) || (monthNumber == 11))) ||
                ((dayOfMonth == 31) && ((monthNumber == 1) || (monthNumber == 3) || (monthNumber == 5) || (monthNumber == 7) || (monthNumber == 8) || (monthNumber == 10) || (monthNumber == 12)))) {
            //sprawdzenie czy poswiecono odpowiednia ilosc dni na roliczenia z urzedami
            if (this.daysToPayOffBills != 2) {
                //jesteś bankrutem ;-P
                this.availableCash = -100000000.0;
            }

            //obliczenie wynagrodzen wspolpracowników
            //sprawdzenie czy są jakieś projekty rozliczone w danym miesiącu dla współpracowników
            for (Project project : this.finishedProjects) {
                if (project.dateWhenProjectIsPaidByClient.getMonthValue() == (monthNumber) && (project.dateWhenProjectIsPaidByClient.getYear() == actualDate.getYear())) {
                    for (WorkDay workDay : project.workDays) {
                        if (workDay.typeOfWorker == 3) {
                            runningCostsCoWorkers += workDay.coWorker.salaryPerDay;
                            if ((this.availableCash - workDay.coWorker.salaryPerDay) < 0.0) {
                                this.coWorkers.remove(workDay.coWorker);
                            }
                        }
                    }
                }
            }

            //obliczenie czy są jakies projekty do rozliczenie w danym miesiącu dla pracowników
            /*for (Project project : this.finishedProjects) {
                if (project.dateWhenProjectIsPaidByClient.getMonthValue() == (monthNumber) && (project.dateWhenProjectIsPaidByClient.getYear() == actualDate.getYear())) {
                    for (WorkDay workDay : project.workDays) {
                        if (workDay.typeOfWorker == 2) {
                            if ((this.availableCash - workDay.salaryPerDay) < 0.0)
                            {
                                this.testers.remove(workDay.worker);
                            }
                        }
                    }
                }
            }



            for (Project project : this.finishedProjects) {
                if (project.dateWhenProjectIsPaidByClient.getMonthValue() == (monthNumber) && (project.dateWhenProjectIsPaidByClient.getYear() == actualDate.getYear())) {
                    for (WorkDay workDay : project.workDays) {
                        if (workDay.typeOfWorker == 4) {
                            if ((this.availableCash - workDay.salaryPerDay) < 0.0)
                            {
                                this.workers.remove(workDay.worker);
                            }
                        }
                    }
                }
            }

             */

            //obliczenie wynagrodzen wspolpracowników
            //sprawdzenie czy są jakieś projekty rozliczone w danym miesiącu dla współpracowników
            for (Project project : this.unfinishedProjects) {
                for (WorkDay workDay : project.workDays) {
                    if (workDay.typeOfWorker == 3 && workDay.dateWhenWorksFinishes.getMonthValue() == actualDate.getMonthValue()) {
                        runningCostsCoWorkers += workDay.coWorker.salaryPerDay;
                        if ((this.availableCash - workDay.coWorker.salaryPerDay) < 0.0) {
                            this.coWorkers.remove(workDay.coWorker);
                        }
                    }
                }
            }


            /*
            //obliczenie czy są jakies projekty do rozliczenie w danym miesiącu dla pracowników
            for (Project project : this.unfinishedProjects) {
                for (WorkDay workDay : project.workDays) {
                    if (workDay.typeOfWorker == 2 && workDay.dateWhenWorksFinishes.getMonthValue() == actualDate.getMonthValue()) {
                        if ((this.availableCash - workDay.salaryPerDay) < 0.0) {
                            this.coWorkers.remove(workDay.coWorker);
                        }
                    }
                }
            }

            for (Project project : this.unfinishedProjects) {
                for (WorkDay workDay : project.workDays) {
                    if (workDay.typeOfWorker == 4 && workDay.dateWhenWorksFinishes.getMonthValue() == actualDate.getMonthValue()) {
                        if ((this.availableCash - workDay.salaryPerDay) < 0.0) {
                            this.coWorkers.remove(workDay.coWorker);
                        }
                    }
                }
            }



             */
            //obliczenie wynagrodzen pracowników
            for (Worker worker : this.workers) {
                runningCostsWorkers += worker.salary;


                //obliczenie kosztów pracowników

                runningCostsOverall = 0.1 * (runningCostsCoWorkers + runningCostsWorkers);

                //sprawdzenie wpływów

                for (Project project : this.finishedProjects) {
                    if (project.dateWhenProjectIsPaidByClient.getMonthValue() == (monthNumber) && (project.dateWhenProjectIsPaidByClient.getYear() == actualDate.getYear())) {
                        incomeTax += project.projectRealPrice;
                    }
                }

                //Obliczenie podatku

                incomeTax *= 0.1;

                this.availableCash -= runningCostsOverall;
                this.availableCash -= incomeTax;

                //sprawdzenie czy nie jest bankrutem
                try {
                    if (availableCash <= DEFAULT_START_CASH) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("Przegrałeś. Jesteś bankrutem. Spróbuj ponownie.");
                }

                //sprawdzenie warunków wygranej

                //sprawdzenie stanu konta
                boolean isSolvable = false;

                try {
                    if (this.availableCash > 0.0) {
                        isSolvable = true;
                    }

                    //sprawdzenie projektów

                    boolean isWon = false;
                    int numberofFinishedBigProjects = 0;
                    int numberofProjectsGeneratedBySellers = 0;

                    for (Project project : this.finishedProjects) {
                        if (project.whoGeneratedProject == 2) {
                            numberofProjectsGeneratedBySellers++;
                        }
                    }

                    for (Project project : this.finishedProjects) {
                        if (project.projectLevel == 3) {
                            boolean isDoneByWorkers = true;
                            for (WorkDay workDay : project.workDays) {
                                if (workDay.typeOfWorker == 1) {
                                    isDoneByWorkers = false;
                                    break;
                                }
                            }
                            if (isDoneByWorkers) {
                                numberofFinishedBigProjects++;
                            }
                        }
                    }

                    if ((isSolvable == true) && (numberofFinishedBigProjects == 3) && (numberofProjectsGeneratedBySellers >= 1)) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("WYGRANA!!!");
                }
            }
            //dodanie kolejnego dnia;
            actualDate = actualDate.plusDays((long) 1);
        }
    }


    public void workOnProject(Project project, Programmer programmer) {
        int daysLeft = 1;
        boolean workThisDay = false;
        if (ThreadLocalRandom.current().nextInt(0,2) !=0)
        {
            workThisDay = false;
        }

        boolean willBeLate = false;
        if (programmer.punctuality != 1.0) {
            if (ThreadLocalRandom.current().nextInt(0, 101) < programmer.punctuality * 100) {
                willBeLate = false;
            }
            else
            {
                willBeLate = true;
            }
        } else
        {
            willBeLate = false;
        }

        boolean willBeWorkingProject = false;
        if (programmer.accuracy != 1.0) {
            int chancheOfWorkingProject = ThreadLocalRandom.current().nextInt(0, 101);
            if (chancheOfWorkingProject < programmer.accuracy * 100) {
                willBeWorkingProject = true;
            }
            else
            {
                willBeWorkingProject = false;
            }
        } else
        {
            willBeWorkingProject = true;
        }

        if (workThisDay != false)
        if (project.backendDays > 0 && daysLeft > 0 && programmer.knowsBackend == true ) {
            project.backendDays -= 1;
            daysLeft -= 1;
            if (willBeLate == true) {
                WorkDay workDay = new WorkDay(1, 4, actualDate, actualDate.plusDays(1), willBeWorkingProject);
                workDay.worker = programmer;
                project.workDays.add(workDay);
            }
            else
            {
                WorkDay workDay = new WorkDay(1, 4, actualDate, actualDate, willBeWorkingProject);
                workDay.worker = programmer;
                project.workDays.add(workDay);
            }
        } else if (project.frontendDays > 0 && daysLeft > 0 && programmer.knowsFrontend == true) {
            project.frontendDays -= 1;
            daysLeft -= 1;
            if (willBeLate == true) {
                WorkDay workDay = new WorkDay(1, 4,  actualDate, actualDate.plusDays(1), willBeWorkingProject);
                workDay.worker = programmer;
                project.workDays.add(workDay);
            }
            else
            {
                WorkDay workDay = new WorkDay(1, 4, actualDate, actualDate, willBeWorkingProject);
                workDay.worker = programmer;
                project.workDays.add(workDay);
            }
        } else if (project.databaseDays > 0 && daysLeft > 0 && programmer.knowsDatabase == true) {
            project.databaseDays -= 1;
            daysLeft -= 1;
            if (willBeLate == true) {
                WorkDay workDay = new WorkDay(1, 4,  actualDate, actualDate.plusDays(1), willBeWorkingProject);
                workDay.worker = programmer;
                project.workDays.add(workDay);
            }
            else
            {
                WorkDay workDay = new WorkDay(1, 4, actualDate, actualDate, willBeWorkingProject);
                workDay.worker = programmer;
                project.workDays.add(workDay);
            }
        } else if (project.wordpressDays > 0 && daysLeft > 0 && programmer.knowsWordpress == true) {
            project.wordpressDays -= 1;
            daysLeft -= 1;
            if (willBeLate == true) {
                WorkDay workDay = new WorkDay(1, 4, actualDate, actualDate.plusDays(1), willBeWorkingProject);
                workDay.worker = programmer;
                project.workDays.add(workDay);
            }
            else
            {
                WorkDay workDay = new WorkDay(1, 4, actualDate, actualDate, willBeWorkingProject);
                workDay.worker = programmer;
                project.workDays.add(workDay);
            }
        } else if (project.prestashopDays > 0 && daysLeft > 0 && programmer.knowsPrestashop == true) {
            project.prestashopDays -= 1;
            daysLeft -= 1;
            if (willBeLate == true) {
                WorkDay workDay = new WorkDay(1, 4, actualDate, actualDate.plusDays(1), willBeWorkingProject);
                workDay.worker = programmer;
                project.workDays.add(workDay);
            }
            else
            {
                WorkDay workDay = new WorkDay(1, 4, actualDate, actualDate, willBeWorkingProject);
                workDay.worker = programmer;
                project.workDays.add(workDay);
            }
        } else if (project.mobileDays > 0 && daysLeft > 0 && programmer.knowsMobile == true)
            project.mobileDays -= 1;
           daysLeft -= 1;
            if (willBeLate == true) {
                WorkDay workDay = new WorkDay(1, 4,  actualDate, actualDate.plusDays(1), willBeWorkingProject);
                workDay.worker = programmer;
                project.workDays.add(workDay);
            }
            else
            {
                WorkDay workDay = new WorkDay(1, 4, actualDate, actualDate, willBeWorkingProject);
                workDay.worker = programmer;
                project.workDays.add(workDay);
            }
    }

    public void workOnProject(Project project, CoWorker coWorker) {
        int daysLeft = 1;


        boolean workThisDay = false;
        if (ThreadLocalRandom.current().nextInt(0,2) !=0)
        {
            workThisDay = false;
        }

        boolean willBeLate = false;
        if (coWorker.punctuality != 1.0) {
            if (ThreadLocalRandom.current().nextInt(0, 101) < coWorker.punctuality * 100) {
                willBeLate = false;
            }
            else
            {
                willBeLate = true;
            }
        } else
        {
            willBeLate = false;
        }

        boolean willBeWorkingProject = false;
        if (coWorker.accuracy != 1.0) {
            int chancheOfWorkingProject = ThreadLocalRandom.current().nextInt(0, 101);
            if (chancheOfWorkingProject < coWorker.accuracy * 100) {
                willBeWorkingProject = true;
            }
            else
            {
                willBeWorkingProject = false;
            }
        } else
        {
            willBeWorkingProject = true;
        }

        if (workThisDay != false)
            if (project.backendDays > 0 && daysLeft > 0 && coWorker.knowsBackend == true ) {
                project.backendDays -= 1;
                daysLeft -= 1;
                if (willBeLate == true) {
                    WorkDay workDay = new WorkDay(1, 3, actualDate, actualDate.plusDays(1), willBeWorkingProject);
                    workDay.coWorker = coWorker;
                    project.workDays.add(workDay);
                }
                else
                {
                    WorkDay workDay = new WorkDay(1, 3, actualDate, actualDate, willBeWorkingProject);
                    workDay.coWorker = coWorker;
                    project.workDays.add(workDay);
                }
            } else if (project.frontendDays > 0 && daysLeft > 0 && coWorker.knowsFrontend == true) {
                project.frontendDays -= 1;
                daysLeft -= 1;
                if (willBeLate == true) {
                    WorkDay workDay = new WorkDay(1, 3, actualDate, actualDate.plusDays(1), willBeWorkingProject);
                    workDay.coWorker = coWorker;
                    project.workDays.add(workDay);
                }
                else
                {
                    WorkDay workDay = new WorkDay(1, 3, actualDate, actualDate, willBeWorkingProject);
                    workDay.coWorker = coWorker;
                    project.workDays.add(workDay);
                }
            } else if (project.databaseDays > 0 && daysLeft > 0 && coWorker.knowsDatabase == true) {
                project.databaseDays -= 1;
                daysLeft -= 1;
                if (willBeLate == true) {
                    WorkDay workDay = new WorkDay(1, 3,  actualDate, actualDate.plusDays(1), willBeWorkingProject);
                    workDay.coWorker = coWorker;
                    project.workDays.add(workDay);
                }
                else
                {
                    WorkDay workDay = new WorkDay(1, 3,  actualDate, actualDate, willBeWorkingProject);
                    workDay.coWorker = coWorker;
                    project.workDays.add(workDay);
                }
            } else if (project.wordpressDays > 0 && daysLeft > 0 && coWorker.knowsWordpress == true) {
                project.wordpressDays -= 1;
                daysLeft -= 1;
                if (willBeLate == true) {
                    WorkDay workDay = new WorkDay(1, 3,  actualDate, actualDate.plusDays(1), willBeWorkingProject);
                    workDay.coWorker = coWorker;
                    project.workDays.add(workDay);
                }
                else
                {
                    WorkDay workDay = new WorkDay(1, 3, actualDate, actualDate, willBeWorkingProject);
                    workDay.coWorker = coWorker;
                    project.workDays.add(workDay);
                }
            } else if (project.prestashopDays > 0 && daysLeft > 0 && coWorker.knowsPrestashop == true) {
                project.prestashopDays -= 1;
                daysLeft -= 1;
                if (willBeLate == true) {
                    WorkDay workDay = new WorkDay(1, 3,  actualDate, actualDate.plusDays(1), willBeWorkingProject);
                    workDay.coWorker = coWorker;
                    project.workDays.add(workDay);
                }
                else
                {
                    WorkDay workDay = new WorkDay(1, 3, actualDate, actualDate, willBeWorkingProject);
                    workDay.coWorker = coWorker;
                    project.workDays.add(workDay);
                }
            } else if (project.mobileDays > 0 && daysLeft > 0 && coWorker.knowsMobile == true)
                project.mobileDays -= 1;
        daysLeft -= 1;
        if (willBeLate == true) {
            WorkDay workDay = new WorkDay(1, 3, actualDate, actualDate.plusDays(1), willBeWorkingProject);
            workDay.coWorker = coWorker;
            project.workDays.add(workDay);
        }
        else
        {
            WorkDay workDay = new WorkDay(1, 3,  actualDate, actualDate, willBeWorkingProject);
            workDay.coWorker = coWorker;
            project.workDays.add(workDay);
        }
    }

    public void testingProject(Project project, Tester tester) {
        int daysLeft = 1;
        int numberOfProgrammers = 0;

        for (Worker programmer : workers) {
            if (programmer instanceof Programmer) {
                numberOfProgrammers++;
            }
        }
        if (numberOfProgrammers >= 3) {
            for (WorkDay workDay : project.workDays) {
                workDay.isTested = true;
                WorkDay workDay1 = new WorkDay(7, 2, actualDate, actualDate, true);
                workDay1.worker = tester;
                project.workDays.add(workDay1);
            }
            project.isProjectRunnable = true;
        } else {
            for (WorkDay workDay : project.workDays) {
                if (workDay.isTested != true && daysLeft != 0) {
                    workDay.isTested = true;
                    WorkDay workDay1 = new WorkDay(7, 2, actualDate, actualDate, true);
                    workDay1.worker = tester;
                    daysLeft -= 1;
                    project.workDays.add(workDay1);
                }
            }
        }
    }

    public void checkingProject(Project project) {

        //sprawdzenie czy projekt ma wyrobione godziny pracy
        boolean isProjectFinished = project.workDays.size() == (2 * (project.wordpressDays + project.databaseDays + project.backendDays + project.frontendDays + project.prestashopDays + project.mobileDays));

        //sprawdzenie czy projekt jest działający jeśli jest skończony
        if (isProjectFinished) {
            for (WorkDay workDay : project.workDays) {
                if (!workDay.isTested) {
                    break;
                } else {
                    project.isProjectRunnable = true;
                }
            }
        }

        //sprawdzenie czy projekt jest opóźniony i o ile
        boolean isProjectLate = false;
        boolean isProjectLateToAWeek = false;
        boolean isProjectLateMoreAWeek = false;
        if (project.dateWhenClientGetBackProject.isAfter(project.releaseProjectDate))
        {
            if (project.dateWhenClientGetBackProject.plusDays(1).isBefore(project.dateWhenProjectIsWeekLate) ||
                    project.dateWhenClientGetBackProject.plusDays(2).isBefore(project.dateWhenProjectIsWeekLate) ||
                    project.dateWhenClientGetBackProject.plusDays(3).isBefore(project.dateWhenProjectIsWeekLate) ||
                    project.dateWhenClientGetBackProject.plusDays(4).isBefore(project.dateWhenProjectIsWeekLate) ||
                    project.dateWhenClientGetBackProject.plusDays(5).isBefore(project.dateWhenProjectIsWeekLate) ||
                    project.dateWhenClientGetBackProject.plusDays(6).isBefore(project.dateWhenProjectIsWeekLate) ||
                    project.dateWhenClientGetBackProject.plusDays(7).isBefore(project.dateWhenProjectIsWeekLate)
            ) {
                isProjectLateToAWeek = true;
            } else {
                isProjectLateMoreAWeek = true;
            }
            isProjectLate = true;
        }

        //klient wyluzowany
        if (project.typeOfClient == 1) {
            if (isProjectFinished) {
                //projekt opóżniony do tygodnia
                if (isProjectLateToAWeek) {
                    //20% szans na uniknięcie kar
                    if (ThreadLocalRandom.current().nextInt(1, 101) >= 20) {
                        project.projectRealPrice = project.projectPrice - project.penaltyFixedByContract;
                    } else {
                        project.projectRealPrice = project.projectPrice;
                    }
                    //30% szans na opóżnienie płatności
                    if (ThreadLocalRandom.current().nextInt(1, 101) >= 30) {
                        project.dateWhenProjectIsPaidByClient = project.DateOfPayment;
                    } else {
                        project.dateWhenProjectIsPaidByClient = project.DateOfPayment.plusWeeks(1);
                    }
                }
                if (isProjectLateMoreAWeek) {
                    project.projectRealPrice = project.projectPrice - project.penaltyFixedByContract;
                    project.dateWhenProjectIsPaidByClient = project.DateOfPayment;
                }

            }
            else
            {
                project.projectRealPrice = 0.0;
            }
        }

        //klient wymagający
        if (project.typeOfClient == 2) {
            //projekt skończony
            if (isProjectFinished) {
                //projekt działający
                if (project.isProjectRunnable) {
                    //projekt opóżniony
                    if (isProjectLate) {
                        project.projectRealPrice = project.projectPrice - project.penaltyFixedByContract;
                    } else {
                        project.projectRealPrice = project.projectPrice;
                    }
                } else {
                    if (ThreadLocalRandom.current().nextInt(1, 101) >= 50) {
                        project.projectRealPrice = 0.0;
                    } else {
                        project.projectRealPrice = project.projectPrice; //- () jakieś koszty?
                    }
                }
                project.dateWhenProjectIsPaidByClient = project.DateOfPayment;
            }
            else
            {
                project.projectRealPrice = 0.0;
            }
        }

        //klient typu skrwl
        if (project.typeOfClient == 3) {
            //projekt skończony
                if (isProjectFinished) {
                    //projekt działający
                    if (project.isProjectRunnable) {
                        //projekt opóżniony
                        if (isProjectLate) {
                            project.projectRealPrice = project.projectPrice - project.penaltyFixedByContract;
                        }
                        if (ThreadLocalRandom.current().nextInt(1, 101) >= 1) {
                            //30% szans na opóżnienie płatności o tydzień
                            if (ThreadLocalRandom.current().nextInt(1, 101) >= 30) {
                                project.dateWhenProjectIsPaidByClient = project.DateOfPayment;
                            } else {
                                //5 szans o opożnienie płatności o miesiąc
                                if (ThreadLocalRandom.current().nextInt(1, 101) >= 5) {
                                    project.dateWhenProjectIsPaidByClient = project.DateOfPayment.plusWeeks(1);
                                } else {
                                    project.dateWhenProjectIsPaidByClient = project.DateOfPayment.plusMonths(1);
                                }
                            }
                        } else {
                            project.projectRealPrice = 0.0;
                            project.dateWhenProjectIsPaidByClient = project.DateOfPayment;
                        }
                    }
                }
             else {
                project.projectRealPrice = 0.0;
            }
        }




    }

    public void lookingForProjectBySeller() {
        for (Seller seller: sellers)
        {
            seller.GenerateProject(unfinishedProjects, projectIndex, actualDate);
        }
    }
}





