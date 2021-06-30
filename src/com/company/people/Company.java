package com.company.people;

import com.company.project.Project;
import com.company.project.WorkDay;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.System.exit;

public class Company {
    public String companyName;
    public Double availableCash;
    public ArrayList<Project> unfinishedProjects;
    public ArrayList<Project> finishedProjects;
    public ArrayList<CoWorker> coWorkers;
    public ArrayList<Worker> workers;
    public static Double DEFAULT_START_CASH = 5000.0;
    public int daysToNewProject = 0;
    public int daysToPayOffBills = 0;

    public Company(String companyName) {
        this.companyName = companyName;
        this.availableCash = DEFAULT_START_CASH;
        this.unfinishedProjects = new ArrayList<>();
        this.finishedProjects = new ArrayList<>();
        this.coWorkers = new ArrayList<>();
        this.workers = new ArrayList<>();
    }

    //OK
    public boolean setProject(int index, ArrayList<Project> projects) {
        boolean isOK = false;
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
                isOK = true;
            }
        }
        catch (Exception e)
        {
            System.out.println("There is no project with the index: "+ index+ ".\n");
        }
        return isOK;
    }

    //OK
    public boolean releaseProject(int index, LocalDate date) {
        boolean isOK = false;
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
                    Project releasedProject = this.unfinishedProjects.get(indexInArrayList);
                    releasedProject.dateWhenClientGetBackProject = date;
                    checkingProject(releasedProject);
                    this.unfinishedProjects.remove(indexInArrayList);
                    this.finishedProjects.add(releasedProject);
                    System.out.println("Project successfully released.");
                    isOK = true;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("The project does not exist or the project has been released.");
        }
        return isOK;
    }

    //OK
    public boolean workOnProjectAlone(int index, LocalDate date) {
        boolean isOK = false;
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
                        WorkDay workDay = new WorkDay(1, 1, date, date, false);
                        this.unfinishedProjects.get(indexInArrayList).workDays.add(workDay);
                        System.out.println("New code has been written.");
                        isOK = true;
                    } else if (this.unfinishedProjects.get(indexInArrayList).frontendDays > 0) {
                        this.unfinishedProjects.get(indexInArrayList).frontendDays -= 1;
                        WorkDay workDay = new WorkDay(2, 1, date, date, false);
                        this.unfinishedProjects.get(indexInArrayList).workDays.add(workDay);
                        System.out.println("New code has been written.");
                        isOK = true;
                    } else if (this.unfinishedProjects.get(indexInArrayList).databaseDays > 0) {
                        this.unfinishedProjects.get(indexInArrayList).databaseDays -= 1;
                        WorkDay workDay = new WorkDay(3, 1, date, date, false);
                        this.unfinishedProjects.get(indexInArrayList).workDays.add(workDay);
                        System.out.println("New code has been written.");
                        isOK = true;
                    } else if (this.unfinishedProjects.get(indexInArrayList).wordpressDays > 0) {
                        this.unfinishedProjects.get(indexInArrayList).wordpressDays -= 1;
                        WorkDay workDay = new WorkDay(4, 1, date, date, false);
                        this.unfinishedProjects.get(indexInArrayList).workDays.add(workDay);
                        System.out.println("New code has been written.");
                        isOK = true;
                    } else if (this.unfinishedProjects.get(indexInArrayList).prestashopDays > 0) {
                        this.unfinishedProjects.get(indexInArrayList).prestashopDays -= 1;
                        WorkDay workDay = new WorkDay(5, 1, date, date, false);
                        this.unfinishedProjects.get(indexInArrayList).workDays.add(workDay);
                        System.out.println("New code has been written.");
                        isOK = true;
                    } else
                    {
                        throw new Exception();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("The project does not exist or is finished.");
        }
        return isOK;
    }

    //OK
    public int lookingForClient(ArrayList<Project> projects, int projectIndex, LocalDate date) {
        Project project;
        if (this.daysToNewProject == 4) {
            project = new Project(projectIndex, 1, date);
            projects.add(project);
            System.out.println("There is a new project!!!\n");
            projectIndex++;
            this.daysToNewProject = 0;
        } else {
            this.daysToNewProject += 1;
        }
        return projectIndex;
    }

    //OK
    public boolean testingProject(int index, LocalDate date) {
        boolean isOK = false;
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
                } else if (this.unfinishedProjects.get(indexInArrayList).daysToFinishTesting == 0) {
                    throw new Exception();
                } else {
                    int amountOfWorkDaysTested = this.unfinishedProjects.get(indexInArrayList).workDays.size();
                    for (WorkDay workDay : this.unfinishedProjects.get(indexInArrayList).workDays) {
                        if (workDay.isTested) {
                            amountOfWorkDaysTested--;
                        }
                    }

                    if (amountOfWorkDaysTested == 0) {
                        throw new Exception();
                    } else {
                        for (WorkDay workDay : this.unfinishedProjects.get(indexInArrayList).workDays) {
                            if (!workDay.isTested) {
                                workDay.isTested = true;
                                WorkDay workDay1 = new WorkDay(7, 1, date, date, true);
                                this.unfinishedProjects.get(indexInArrayList).workDays.add(workDay1);
                                this.unfinishedProjects.get(indexInArrayList).daysToFinishTesting -= 1;
                                System.out.println("New code has been tested.");
                                isOK = true;
                                if (this.unfinishedProjects.get(indexInArrayList).daysToFinishTesting > 0) {
                                    System.out.println("Remaining days to finish testing the project: " + this.unfinishedProjects.get(indexInArrayList).daysToFinishTesting);
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
         catch (Exception e) {
            System.out.println("The project does not exist or is fully tested or there is no code to test");
        }
        return isOK;
    }

    //OK
    public boolean setCoWorker(int index, ArrayList<CoWorker> coWorkers) {
        boolean isOK = false;
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
                isOK = true;
            }
        } catch (Exception e) {
            System.out.println("There is no coworker with the idWorker: " + index + "\n");
        }
        return isOK;
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
    public boolean dismissCoWorker(int index, ArrayList<CoWorker> coWorkers) {
        boolean isOK = false;
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
                isOK = true;
            }
        } catch (Exception e) {
            System.out.println("There is no coworker with the idWorker: " + index + "\n");
        }
        return isOK;
    }

    //OK
    public boolean dismissWorker(int index, ArrayList<Worker> workers) {
        boolean isOK = false;
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
                isOK = true;
            }
        }
        catch (Exception e) {
            System.out.println("There is no employee with the idWorker: " + index + "\n");
        }
        return isOK;
    }

    //OK
    public void payOffBills() {
        {
            this.daysToPayOffBills += 1;
            System.out.println("A visit to the tax office completed.");
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
                '}';
    }

    //OK
    public LocalDate finishingDay(int index, LocalDate date, ArrayList<Project> projects) {

        double runningCostsCoWorkers = 0.0;
        double runningCostsWorkers = 0.0;
        double runningCostsOverall;
        double incomeTax;

        DayOfWeek day = date.getDayOfWeek();
        int dayNumber = day.getValue();

        if (!(dayNumber == 6 || dayNumber == 7)) {
            if (!(coWorkers.isEmpty()) && !(this.unfinishedProjects.isEmpty())) {
                for (CoWorker coWorker : coWorkers) {
                    for (Project project : unfinishedProjects) {
                        workOnProject(project, coWorker, date);
                    }
                }
            }

            if (!(workers.isEmpty()) && !(this.unfinishedProjects.isEmpty())) {
                for (Worker worker : workers) {
                    for (Project project : unfinishedProjects) {
                        if (worker instanceof Programmer)
                            workOnProject(project, (Programmer) worker, date);
                    }
                }
            }
            if (!(workers.isEmpty()) && !(this.unfinishedProjects.isEmpty())) {
                for (Worker worker : workers) {
                    for (Project project : unfinishedProjects) {
                        if (worker instanceof Tester)
                            testingProject(project, (Tester) worker, date);
                    }
                }
            }

            for (Worker worker: workers)
            {
                if (worker instanceof Seller) {
                    index = worker.GenerateProject(projects, index, date);
                }
            }
        }

        for (Project project : this.finishedProjects) {
            if (project.dateWhenProjectIsPaidByClient.equals(date)) {
                this.availableCash += project.projectRealPrice;
            }
        }

        //End of the month
        int dayOfMonth = date.getDayOfMonth();
        int monthNumber = date.getMonthValue();
        if (((dayOfMonth == 28) && (monthNumber == 2)) ||
                ((dayOfMonth == 30) && ((monthNumber == 4) || (monthNumber == 6) || (monthNumber == 9) || (monthNumber == 11))) ||
                ((dayOfMonth == 31) && ((monthNumber == 1) || (monthNumber == 3) || (monthNumber == 5) || (monthNumber == 7) || (monthNumber == 8) || (monthNumber == 10) || (monthNumber == 12))))
        {

            if (this.daysToPayOffBills != 2) {
                this.availableCash = -100000000.0;
            }
            else
            {
                this.daysToPayOffBills = 0;
            }

            for (CoWorker coWorker: coWorkers)
            {
                double salary;
                salary = salaryCheckCoWorker(this.unfinishedProjects, this.finishedProjects, coWorker.idWorker, date);
                if (salary > this.availableCash)
                {
                    coWorkers.remove(coWorker);
                }
                runningCostsCoWorkers = salary;
            }

            for (Worker worker : this.workers) {
                runningCostsWorkers += worker.salary;

                //10% juices and bananas for programmers and cookies for sellers
                runningCostsOverall = 1.1 *(runningCostsCoWorkers + runningCostsWorkers);

                this.availableCash -= runningCostsOverall;

                double income = 0.0;
                for (Project project : this.finishedProjects) {
                    if (project.dateWhenProjectIsPaidByClient.getMonthValue() == (monthNumber) && (project.dateWhenProjectIsPaidByClient.getYear() == date.getYear())) {
                        income += project.projectRealPrice;
                    }
                }

                incomeTax = 0.1 * income;


                this.availableCash -= incomeTax;

                //Game over conditions
                try {
                    if (availableCash <= 0.0) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("GAME OVER !!! You are bankrupt.");
                    exit(0);
                }

                //Victory conditions

                boolean isSolvable = false;

                try {
                    if (this.availableCash > 0.0) {
                        isSolvable = true;
                    }
                    
                    int numberOfFinishedBigProjects = 0;
                    int numberOfProjectsGeneratedBySellers = 0;

                    for (Project project : this.finishedProjects) {
                        if (project.whoGeneratedProject == 2) {
                            numberOfProjectsGeneratedBySellers++;
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
                                numberOfFinishedBigProjects++;
                            }
                        }
                    }

                    if ((isSolvable) && (numberOfFinishedBigProjects == 3) && (numberOfProjectsGeneratedBySellers >= 1)) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("VICTORY!!!");
                }
            }

        }
        date = date.plusDays(1);
        return date;
    }


    //OK
    public void workOnProject(Project project, Programmer programmer, LocalDate date) {
        boolean workThisDay = ThreadLocalRandom.current().nextInt(0, 2) != 0;

        boolean willBeLate;
        if (programmer.punctuality != 1.0) {
            willBeLate = !(ThreadLocalRandom.current().nextInt(0, 101) < programmer.punctuality * 100);
        } else
        {
            willBeLate = false;
        }

        boolean willBeWorkingProject;
        if (programmer.accuracy != 1.0) {
            int chanceOfWorkingProject = ThreadLocalRandom.current().nextInt(0, 101);
            willBeWorkingProject = !(chanceOfWorkingProject < programmer.accuracy * 100);
        } else
        {
            willBeWorkingProject = true;
        }

        if (workThisDay)
        if (project.backendDays > 0 && programmer.knowsBackend) {
            project.backendDays -= 1;
            WorkDay workDay;
            if (willBeLate) {
                workDay = new WorkDay(1, 4, date, date.plusDays(1), willBeWorkingProject);
            }
            else
            {
                workDay = new WorkDay(1, 4, date, date, willBeWorkingProject);
            }
            workDay.worker = programmer;
            project.workDays.add(workDay);
        } else if (project.frontendDays > 0 && programmer.knowsFrontend) {
            project.frontendDays -= 1;
            WorkDay workDay;
            if (willBeLate) {
                workDay = new WorkDay(1, 4, date, date.plusDays(1), willBeWorkingProject);
            }
            else
            {
                workDay = new WorkDay(1, 4, date, date, willBeWorkingProject);
            }
            workDay.worker = programmer;
            project.workDays.add(workDay);
        } else if (project.databaseDays > 0 && programmer.knowsDatabase) {
            project.databaseDays -= 1;
            WorkDay workDay;
            if (willBeLate) {
                workDay = new WorkDay(1, 4, date, date.plusDays(1), willBeWorkingProject);
            }
            else
            {
                workDay = new WorkDay(1, 4, date, date, willBeWorkingProject);
            }
            workDay.worker = programmer;
            project.workDays.add(workDay);
        } else if (project.wordpressDays > 0 && programmer.knowsWordpress) {
            project.wordpressDays -= 1;
            WorkDay workDay;
            if (willBeLate) {
                workDay = new WorkDay(1, 4, date, date.plusDays(1), willBeWorkingProject);
            }
            else
            {
                workDay = new WorkDay(1, 4, date, date, willBeWorkingProject);
            }
            workDay.worker = programmer;
            project.workDays.add(workDay);
        } else if (project.prestashopDays > 0 && programmer.knowsPrestashop) {
            project.prestashopDays -= 1;
            WorkDay workDay;
            if (willBeLate) {
                workDay = new WorkDay(1, 4, date, date.plusDays(1), willBeWorkingProject);
            }
            else
            {
                workDay = new WorkDay(1, 4, date, date, willBeWorkingProject);
            }
            workDay.worker = programmer;
            project.workDays.add(workDay);
        } else if (project.mobileDays > 0 && programmer.knowsMobile)
            project.mobileDays -= 1;
        WorkDay workDay;
        if (willBeLate) {
            workDay = new WorkDay(1, 4, date, date.plusDays(1), willBeWorkingProject);
        }
            else
            {
                workDay = new WorkDay(1, 4, date, date, willBeWorkingProject);
            }
        workDay.worker = programmer;
        project.workDays.add(workDay);
    }

    //OK
    public void workOnProject(Project project, CoWorker coWorker, LocalDate date) {


        boolean workThisDay = ThreadLocalRandom.current().nextInt(0, 2) != 0;

        boolean willBeLate;
        if (coWorker.punctuality != 1.0) {
            willBeLate = !(ThreadLocalRandom.current().nextInt(0, 101) < coWorker.punctuality * 100);
        } else
        {
            willBeLate = false;
        }

        boolean willBeWorkingProject;
        if (coWorker.accuracy != 1.0) {
            int chanceOfWorkingProject = ThreadLocalRandom.current().nextInt(0, 101);
            willBeWorkingProject = chanceOfWorkingProject < coWorker.accuracy * 100;
        } else
        {
            willBeWorkingProject = true;
        }

        if (workThisDay)
            if (project.backendDays > 0 && coWorker.knowsBackend) {
                project.backendDays -= 1;
                WorkDay workDay;
                if (willBeLate) {
                    workDay = new WorkDay(1, 3, date, date.plusDays(1), willBeWorkingProject);
                }
                else
                {
                    workDay = new WorkDay(1, 3, date, date, willBeWorkingProject);
                }
                workDay.coWorker = coWorker;
                project.workDays.add(workDay);
            } else if (project.frontendDays > 0 && coWorker.knowsFrontend) {
                project.frontendDays -= 1;
                WorkDay workDay;
                if (willBeLate) {
                    workDay = new WorkDay(1, 3, date, date.plusDays(1), willBeWorkingProject);
                }
                else
                {
                    workDay = new WorkDay(1, 3, date, date, willBeWorkingProject);
                }
                workDay.coWorker = coWorker;
                project.workDays.add(workDay);
            } else if (project.databaseDays > 0 && coWorker.knowsDatabase) {
                project.databaseDays -= 1;
                WorkDay workDay;
                if (willBeLate) {
                    workDay = new WorkDay(1, 3, date, date.plusDays(1), willBeWorkingProject);
                }
                else
                {
                    workDay = new WorkDay(1, 3, date, date, willBeWorkingProject);
                }
                workDay.coWorker = coWorker;
                project.workDays.add(workDay);
            } else if (project.wordpressDays > 0 && coWorker.knowsWordpress) {
                project.wordpressDays -= 1;
                WorkDay workDay;
                if (willBeLate) {
                    workDay = new WorkDay(1, 3, date, date.plusDays(1), willBeWorkingProject);
                }
                else
                {
                    workDay = new WorkDay(1, 3, date, date, willBeWorkingProject);
                }
                workDay.coWorker = coWorker;
                project.workDays.add(workDay);
            } else if (project.prestashopDays > 0 && coWorker.knowsPrestashop) {
                project.prestashopDays -= 1;
                WorkDay workDay;
                if (willBeLate) {
                    workDay = new WorkDay(1, 3, date, date.plusDays(1), willBeWorkingProject);
                }
                else
                {
                    workDay = new WorkDay(1, 3, date, date, willBeWorkingProject);
                }
                workDay.coWorker = coWorker;
                project.workDays.add(workDay);
            } else if (project.mobileDays > 0 && coWorker.knowsMobile)
                project.mobileDays -= 1;
        WorkDay workDay;
        if (willBeLate) {
            workDay = new WorkDay(1, 3, date, date.plusDays(1), willBeWorkingProject);
        }
        else
        {
            workDay = new WorkDay(1, 3, date, date, willBeWorkingProject);
        }
        workDay.coWorker = coWorker;
        project.workDays.add(workDay);
    }

    //OK
    public void testingProject(Project project, Tester tester, LocalDate date) {
        int numberOfProgrammers = 0;

        for (Worker worker : workers) {
            if (worker instanceof Programmer) {
                numberOfProgrammers++;
            }
        }
        if (numberOfProgrammers >= 3) {
            for (WorkDay workDay : project.workDays) {
                workDay.isTested = true;
                WorkDay workDay1 = new WorkDay(7, 2, date, date, true);
                workDay1.worker = tester;
                project.workDays.add(workDay1);
            }
            project.isProjectRunnable = true;
        } else {
            for (WorkDay workDay : project.workDays) {
                if (!workDay.isTested) {
                    workDay.isTested = true;
                    WorkDay workDay1 = new WorkDay(7, 2, date, date, true);
                    workDay1.worker = tester;
                    project.workDays.add(workDay1);
                    break;
                }
            }
        }
    }

    //OK
    public void checkingProject(Project project) {

        boolean isProjectFinished = project.wordpressDays + project.databaseDays +
                project.backendDays + project.frontendDays + project.prestashopDays + project.mobileDays == 0;

        if (isProjectFinished) {
            for (WorkDay workDay : project.workDays) {
                if (!workDay.isTested) {
                    project.isProjectRunnable = false;
                    break;
                } else {
                    project.isProjectRunnable = true;
                }
            }
        }

        boolean isProjectLate = false;
        boolean isProjectLateToAWeek = false;
        boolean isProjectLateMoreAWeek = false;
        if (project.dateWhenClientGetBackProject.isAfter(project.releaseProjectDate)) {
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

        //Hassle-free client
        if (project.typeOfClient == 1) {
            if (isProjectFinished) {
                if (isProjectLateToAWeek) {
                    if (ThreadLocalRandom.current().nextInt(1, 101) >= 20) {
                        project.projectRealPrice = project.projectPrice - project.penaltyFixedByContract;
                    } else {
                        project.projectRealPrice = project.projectPrice;
                    }
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
            } else {
                project.projectRealPrice = 0.0;
                project.dateWhenProjectIsPaidByClient = project.DateOfPayment;
            }
        }

        //Regular client
        if (project.typeOfClient == 2) {
            if (isProjectFinished) {
                if (project.isProjectRunnable) {
                    if (isProjectLate) {
                        project.projectRealPrice = project.projectPrice - project.penaltyFixedByContract;
                    } else {
                        project.projectRealPrice = project.projectPrice;
                    }
                } else {
                    if (ThreadLocalRandom.current().nextInt(0, 2) == 0) {
                        project.projectRealPrice = 0.0;
                    } else {
                        project.projectRealPrice = project.projectPrice;
                    }
                }
            } else {
                project.projectRealPrice = 0.0;
            }
            project.dateWhenProjectIsPaidByClient = project.DateOfPayment;
        }

        //Cheater
        if (project.typeOfClient == 3) {
            if (isProjectFinished) {
                if (project.isProjectRunnable) {
                    if (isProjectLate) {
                        project.projectRealPrice = project.projectPrice - project.penaltyFixedByContract;
                    }
                    if (ThreadLocalRandom.current().nextInt(1, 101) >= 1) {
                        if (ThreadLocalRandom.current().nextInt(1, 101) >= 30) {
                            project.dateWhenProjectIsPaidByClient = project.DateOfPayment;
                        } else if (ThreadLocalRandom.current().nextInt(1, 101) <= 5) {
                            project.dateWhenProjectIsPaidByClient = project.DateOfPayment.plusWeeks(1);
                        } else {
                            project.dateWhenProjectIsPaidByClient = project.DateOfPayment.plusMonths(1);
                        }
                    }
                } else {
                    project.projectRealPrice = 0.0;
                    project.dateWhenProjectIsPaidByClient = project.DateOfPayment;
                }
            } else {
                project.projectRealPrice = 0.0;
                project.dateWhenProjectIsPaidByClient = project.DateOfPayment;
            }
        }
    }

    //OK
    public double salaryCheckCoWorker(ArrayList<Project> unfinishedProjects, ArrayList<Project> finishedProjects, int idCoworker, LocalDate date)
    {
        double salary = 0.0;
        int monthNumber = date.getMonthValue();
        for (Project project : finishedProjects) {
            if (project.dateWhenProjectIsPaidByClient.getMonthValue() == (monthNumber) && (project.dateWhenProjectIsPaidByClient.getYear() == date.getYear())) {
                for (WorkDay workDay : project.workDays) {
                    if (workDay.typeOfWorker == 3 && workDay.coWorker.idWorker == idCoworker) {
                        salary += workDay.coWorker.salaryPerDay;
                    }
                }
            }
        }
        for (Project project : unfinishedProjects) {
            for (WorkDay workDay : project.workDays) {
                if (workDay.typeOfWorker == 3 && workDay.dateWhenWorksFinishes.getMonthValue() == date.getMonthValue()
                        && workDay.dateWhenWorksFinishes.getYear() == date.getYear() && workDay.coWorker.idWorker == idCoworker) {
                    salary += workDay.coWorker.salaryPerDay;
                }
            }
        }
        return salary;
    }
}





