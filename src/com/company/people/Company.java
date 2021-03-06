package com.company.people;

import com.company.project.Project;
import com.company.project.WorkDay;
import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.System.exit;

public class Company {
    public static @NotNull
    final Double DEFAULT_START_CASH = 5000.0;
    public final String companyName;
    public final @NotNull ArrayList<Project> unfinishedProjects;
    public final @NotNull ArrayList<Project> finishedProjects;
    public final @NotNull ArrayList<CoWorker> coWorkers;
    public final @NotNull ArrayList<Worker> workers;
    public Double availableCash;
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

    //Signing the contract
    public boolean setProject(int index, @NotNull ArrayList<Project> projects) {
        boolean isOK = false;
        try {
            int indexInArrayList = -1;
            boolean isValid = false;
            for (Project project : projects) {
                if (project.projectIndex == index) {
                    isValid = true;
                    indexInArrayList = projects.indexOf(project);
                    break;
                }
            }
            if (!isValid) {
                throw new Exception();
            }
            if (projects.get(indexInArrayList).projectLevel == 3 && this.workers.size() + this.coWorkers.size() == 0) {
                System.out.println("A level 3 contract cannot be signed if there are no employees and associates.");
            } else {
                this.availableCash += projects.get(indexInArrayList).paymentOnAccount;
                this.unfinishedProjects.add(projects.get(indexInArrayList));
                projects.remove(indexInArrayList);
                System.out.println("Contract signed.\n");
                isOK = true;
            }
        } catch (Exception e) {
            System.out.println("There is no project with the index: " + index + ".\n");
        }
        return isOK;
    }

    //Releasing the project to the client
    public boolean releaseProject(int index, @NotNull LocalDate date) {
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
                    checkingProject(releasedProject, date);
                    this.unfinishedProjects.remove(indexInArrayList);
                    this.finishedProjects.add(releasedProject);
                    System.out.println("Project successfully released.");
                    isOK = true;
                }
            }
        } catch (Exception e) {
            System.out.println("The project does not exist or the project has been released.");
        }
        return isOK;
    }

    //Player alone working on the project
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
                        this.unfinishedProjects.get(indexInArrayList).backendDays -= 1;
                        WorkDay workDay = new WorkDay(1, date, false);
                        this.unfinishedProjects.get(indexInArrayList).workDays.add(workDay);
                        System.out.println("New code has been written.");
                        isOK = true;
                    } else if (this.unfinishedProjects.get(indexInArrayList).frontendDays > 0) {
                        this.unfinishedProjects.get(indexInArrayList).frontendDays -= 1;
                        WorkDay workDay = new WorkDay(1, date, false);
                        this.unfinishedProjects.get(indexInArrayList).workDays.add(workDay);
                        System.out.println("New code has been written.");
                        isOK = true;
                    } else if (this.unfinishedProjects.get(indexInArrayList).databaseDays > 0) {
                        this.unfinishedProjects.get(indexInArrayList).databaseDays -= 1;
                        WorkDay workDay = new WorkDay(1, date, false);
                        this.unfinishedProjects.get(indexInArrayList).workDays.add(workDay);
                        System.out.println("New code has been written.");
                        isOK = true;
                    } else if (this.unfinishedProjects.get(indexInArrayList).wordpressDays > 0) {
                        this.unfinishedProjects.get(indexInArrayList).wordpressDays -= 1;
                        WorkDay workDay = new WorkDay(1, date, false);
                        this.unfinishedProjects.get(indexInArrayList).workDays.add(workDay);
                        System.out.println("New code has been written.");
                        isOK = true;
                    } else if (this.unfinishedProjects.get(indexInArrayList).prestashopDays > 0) {
                        this.unfinishedProjects.get(indexInArrayList).prestashopDays -= 1;
                        WorkDay workDay = new WorkDay(1, date, false);
                        this.unfinishedProjects.get(indexInArrayList).workDays.add(workDay);
                        System.out.println("New code has been written.");
                        isOK = true;
                    } else {
                        throw new Exception();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("The project does not exist or is finished.");
        }
        return isOK;
    }

    //Player generating a new project
    public int lookingForClient(@NotNull ArrayList<Project> projects, int projectIndex, LocalDate date) {
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

    //Player tests the code itself
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
                    for (WorkDay workDay : this.unfinishedProjects.get(indexInArrayList).workDays) {
                        if (!workDay.isTested) {
                            workDay.isTested = true;
                            WorkDay workDay1 = new WorkDay(1, date, true);
                            this.unfinishedProjects.get(indexInArrayList).workDays.add(workDay1);
                            this.unfinishedProjects.get(indexInArrayList).daysToFinishTesting -= 1;
                            System.out.println("New code has been tested.");
                            isOK = true;
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("The project does not exist or is fully tested or there is no code to test");
        }
        return isOK;
    }

    //Hiring a new associate
    public boolean setCoWorker(int index, @NotNull ArrayList<CoWorker> coWorkers) {
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

    //Hiring a new employee
    public int setWorker(int index, @NotNull ArrayList<Worker> workers, int workerIndex) {
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
        } catch (Exception e) {
            System.out.println("There is no candidate with the idWorker: " + index + "\n");
        }
        return workerIndex;
    }

    //Dismissal of a colleague
    public boolean dismissCoWorker(int index, @NotNull ArrayList<CoWorker> coWorkers) {
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

    //Dismissal of an employee
    public boolean dismissWorker(int index, @NotNull ArrayList<Worker> workers) {
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
        } catch (Exception e) {
            System.out.println("There is no employee with the idWorker: " + index + "\n");
        }
        return isOK;
    }

    //Spending a day at the tax office
    public void payOffBills() {
        {
            this.daysToPayOffBills += 1;
            System.out.println("A visit to the tax office completed.");
        }
    }

    @Override
    public @NotNull String toString() {
        return "Company Name:'" + companyName + "'\n" +
                "Available Cash: " + availableCash + '\n' +
                "Unfinished Projects:\n" + unfinishedProjects + '\n' +
                "Released Projects:\n" + finishedProjects + '\n' +
                "Associates: \n" + coWorkers + "\n\n" +
                "Employees: \n" + workers + "\n\n" +
                "Days To New Project: " + daysToNewProject + '\n' +
                "Visits in tax offices: " + daysToPayOffBills + "\n";
    }

    //End of the working day and month
    public int finishingDay(int index, @NotNull LocalDate date, ArrayList<Project> projects, @NotNull ArrayList<Worker> allWorkers, @NotNull ArrayList<CoWorker> allCoWorkers) {

        double runningCostsCoWorkers = 0.0;
        double runningCostsWorkers = 0.0;
        double runningCostsOverall;
        double incomeTax;

        //Working day of employees
        DayOfWeek day = date.getDayOfWeek();
        int dayNumber = day.getValue();

        if (!(dayNumber == 6 || dayNumber == 7)) {
            if (!(coWorkers.isEmpty()) && !(this.unfinishedProjects.isEmpty())) {
                for (CoWorker coWorker : coWorkers) {
                    for (Project project : unfinishedProjects) {
                        if (workOnProject(project, coWorker, date)) {
                            break;
                        }
                    }
                }
            }

            if (!(workers.isEmpty()) && !(this.unfinishedProjects.isEmpty())) {
                for (Worker worker : workers) {
                    for (Project project : unfinishedProjects) {
                        if (worker instanceof Programmer)
                            if (workOnProject(project, (Programmer) worker, date)) {
                                break;
                            }
                    }
                }
            }
            if (!(workers.isEmpty()) && !(this.unfinishedProjects.isEmpty())) {
                for (Worker worker : workers) {
                    for (Project project : unfinishedProjects) {
                        if (worker instanceof Tester)
                            if (testingProject(project, date)) {
                                break;
                            }
                    }
                }
            }

            for (Worker worker : workers) {
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
                ((dayOfMonth == 31) && ((monthNumber == 1) || (monthNumber == 3) || (monthNumber == 5) || (monthNumber == 7) || (monthNumber == 8) || (monthNumber == 10) || (monthNumber == 12)))) {
            if (this.daysToPayOffBills < 2) {
                this.availableCash = -100000000.0;
            } else {
                this.daysToPayOffBills = 0;
            }

            for (CoWorker coWorker : coWorkers) {
                double salary = salaryCheckCoWorker(this.unfinishedProjects, this.finishedProjects, coWorker.idWorker, date);
                runningCostsCoWorkers += salary;
            }
            Iterator<CoWorker> coWorkerIterator = coWorkers.iterator();
            while (coWorkerIterator.hasNext()) {
                CoWorker coWorker = coWorkerIterator.next();
                if (salaryCheckCoWorker(this.unfinishedProjects, this.finishedProjects, coWorker.idWorker, date) > this.availableCash) {
                    allCoWorkers.add(coWorker);
                    coWorkerIterator.remove();
                }

            }

            for (Worker worker : this.workers) {
                runningCostsWorkers += worker.salary;
            }
            Iterator<Worker> workerIterator = workers.iterator();
            while (workerIterator.hasNext()) {
                Worker worker = workerIterator.next();
                if (worker.salary > this.availableCash) {
                    allWorkers.add(worker);
                    workerIterator.remove();
                }

            }

            //10% for juices and bananas for developers and cookies for sellers
            runningCostsOverall = 110 * (runningCostsCoWorkers + runningCostsWorkers) / 100;

            this.availableCash -= runningCostsOverall;

            //Tax
            {
                double income = 0.0;
                for (Project project : this.finishedProjects) {
                    if (project.dateWhenProjectIsPaidByClient.getMonthValue() == (monthNumber) && (project.dateWhenProjectIsPaidByClient.getYear() == date.getYear())) {
                        income += (project.projectRealPrice + project.paymentOnAccount);
                    }
                }

                incomeTax = 10 * income / 100;
                if (incomeTax > this.availableCash) {
                    this.availableCash = -10000000.0;
                } else {
                    this.availableCash -= incomeTax;
                }
            }
        }


        //Victory conditions
        boolean isSolvable = this.availableCash > DEFAULT_START_CASH;

        int numberOfFinishedBigProjects = 0;
        int numberOfProjectsGeneratedBySellers = 0;

        for (Project project : this.finishedProjects) {
            if (project.whoGeneratedProject == 2 && project.projectLevel == 3 && Objects.equals(project.projectPrice, (project.projectRealPrice + project.paymentOnAccount))) {
                numberOfProjectsGeneratedBySellers++;
            }
        }

        for (Project project : this.finishedProjects) {
            if (project.projectLevel == 3 && Objects.equals(project.projectPrice, (project.projectRealPrice + project.paymentOnAccount))) {
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

        if ((isSolvable) && (numberOfFinishedBigProjects >= 3) && (numberOfProjectsGeneratedBySellers >= 1)) {
            System.out.println("VICTORY!!!");
            exit(0);
        }

        return index;
    }

    //Employees working on the project
    public boolean workOnProject(@NotNull Project project, @NotNull Programmer programmer, @NotNull LocalDate date) {

        boolean isOK = false;
        boolean workThisDay = ThreadLocalRandom.current().nextInt(0, 100) >= 5;

        boolean willBeLate;
        if (programmer.punctuality != 1.0) {
            willBeLate = !(ThreadLocalRandom.current().nextInt(0, 100) < programmer.punctuality * 100);
        } else {
            willBeLate = false;
        }

        boolean willBeWorkingProject;
        if (programmer.accuracy != 1.0) {
            int chanceOfWorkingProject = ThreadLocalRandom.current().nextInt(0, 100);
            willBeWorkingProject = !(chanceOfWorkingProject < programmer.accuracy * 100);
        } else {
            willBeWorkingProject = true;
        }

        if (workThisDay) {
            if (project.backendDays > 0 && programmer.knowsBackend) {
                project.backendDays -= 1;
                WorkDay workDay;
                if (willBeLate) {
                    workDay = new WorkDay(4, date.plusDays(1), willBeWorkingProject);
                } else {
                    workDay = new WorkDay(4, date, willBeWorkingProject);
                }
                if (willBeWorkingProject) {
                    project.daysToFinishTesting -= 1;
                }
                project.workDays.add(workDay);
                isOK = true;
            } else if (project.frontendDays > 0 && programmer.knowsFrontend) {
                project.frontendDays -= 1;
                WorkDay workDay;
                if (willBeLate) {
                    workDay = new WorkDay(4, date.plusDays(1), willBeWorkingProject);
                } else {
                    workDay = new WorkDay(4, date, willBeWorkingProject);
                }
                if (willBeWorkingProject) {
                    project.daysToFinishTesting -= 1;
                }
                project.workDays.add(workDay);
                isOK = true;
            } else if (project.databaseDays > 0 && programmer.knowsDatabase) {
                project.databaseDays -= 1;
                WorkDay workDay;
                if (willBeLate) {
                    workDay = new WorkDay(4, date.plusDays(1), willBeWorkingProject);
                } else {
                    workDay = new WorkDay(4, date, willBeWorkingProject);
                }
                if (willBeWorkingProject) {
                    project.daysToFinishTesting -= 1;
                }
                project.workDays.add(workDay);
                isOK = true;
            } else if (project.wordpressDays > 0 && programmer.knowsWordpress) {
                project.wordpressDays -= 1;
                WorkDay workDay;
                if (willBeLate) {
                    workDay = new WorkDay(4, date.plusDays(1), willBeWorkingProject);
                } else {
                    workDay = new WorkDay(4, date, willBeWorkingProject);
                }
                if (willBeWorkingProject) {
                    project.daysToFinishTesting -= 1;
                }
                project.workDays.add(workDay);
                isOK = true;
            } else if (project.prestashopDays > 0 && programmer.knowsPrestashop) {
                project.prestashopDays -= 1;
                WorkDay workDay;
                if (willBeLate) {
                    workDay = new WorkDay(4, date.plusDays(1), willBeWorkingProject);
                } else {
                    workDay = new WorkDay(4, date, willBeWorkingProject);
                }
                if (willBeWorkingProject) {
                    project.daysToFinishTesting -= 1;
                }
                project.workDays.add(workDay);
                isOK = true;
            } else if (project.mobileDays > 0 && programmer.knowsMobile) {
                project.mobileDays -= 1;
                WorkDay workDay;
                if (willBeLate) {
                    workDay = new WorkDay(4, date.plusDays(1), willBeWorkingProject);
                } else {
                    workDay = new WorkDay(4, date, willBeWorkingProject);
                }
                if (willBeWorkingProject) {
                    project.daysToFinishTesting -= 1;
                }
                project.workDays.add(workDay);
                isOK = true;
            }
        }
        return isOK;
    }

    //Colleagues working on the project
    public boolean workOnProject(@NotNull Project project, @NotNull CoWorker coWorker, @NotNull LocalDate date) {
        boolean isOK = false;

        boolean workThisDay = ThreadLocalRandom.current().nextInt(0, 100) >= 5;

        boolean willBeLate;
        if (coWorker.punctuality != 1.0) {
            willBeLate = !(ThreadLocalRandom.current().nextInt(1, 101) < coWorker.punctuality * 100);
        } else {
            willBeLate = false;
        }

        boolean willBeWorkingProject;
        if (coWorker.accuracy != 1.0) {
            int chanceOfWorkingProject = ThreadLocalRandom.current().nextInt(1, 101);
            willBeWorkingProject = chanceOfWorkingProject < coWorker.accuracy * 100;
        } else {
            willBeWorkingProject = true;
        }

        if (workThisDay) {
            if (project.backendDays > 0 && coWorker.knowsBackend) {
                project.backendDays -= 1;
                WorkDay workDay;
                if (willBeLate) {
                    workDay = new WorkDay(3, date.plusDays(1), willBeWorkingProject);
                } else {
                    workDay = new WorkDay(3, date, willBeWorkingProject);
                }
                if (willBeWorkingProject) {
                    project.daysToFinishTesting -= 1;
                }
                workDay.coWorker = coWorker;
                project.workDays.add(workDay);
                isOK = true;
            } else if (project.frontendDays > 0 && coWorker.knowsFrontend) {
                project.frontendDays -= 1;
                WorkDay workDay;
                if (willBeLate) {
                    workDay = new WorkDay(3, date.plusDays(1), willBeWorkingProject);
                } else {
                    workDay = new WorkDay(3, date, willBeWorkingProject);
                }
                if (willBeWorkingProject) {
                    project.daysToFinishTesting -= 1;
                }
                workDay.coWorker = coWorker;
                project.workDays.add(workDay);
                isOK = true;
            } else if (project.databaseDays > 0 && coWorker.knowsDatabase) {
                project.databaseDays -= 1;
                WorkDay workDay;
                if (willBeLate) {
                    workDay = new WorkDay(3, date.plusDays(1), willBeWorkingProject);
                } else {
                    workDay = new WorkDay(3, date, willBeWorkingProject);
                }
                if (willBeWorkingProject) {
                    project.daysToFinishTesting -= 1;
                }
                workDay.coWorker = coWorker;
                project.workDays.add(workDay);
                isOK = true;
            } else if (project.wordpressDays > 0 && coWorker.knowsWordpress) {
                project.wordpressDays -= 1;
                WorkDay workDay;
                if (willBeLate) {
                    workDay = new WorkDay(3, date.plusDays(1), willBeWorkingProject);
                } else {
                    workDay = new WorkDay(3, date, willBeWorkingProject);
                }
                if (willBeWorkingProject) {
                    project.daysToFinishTesting -= 1;
                }
                workDay.coWorker = coWorker;
                project.workDays.add(workDay);
                isOK = true;
            } else if (project.prestashopDays > 0 && coWorker.knowsPrestashop) {
                project.prestashopDays -= 1;
                WorkDay workDay;
                if (willBeLate) {
                    workDay = new WorkDay(3, date.plusDays(1), willBeWorkingProject);
                } else {
                    workDay = new WorkDay(3, date, willBeWorkingProject);
                }
                if (willBeWorkingProject) {
                    project.daysToFinishTesting -= 1;
                }
                workDay.coWorker = coWorker;
                project.workDays.add(workDay);
                isOK = true;
            } else if (project.mobileDays > 0 && coWorker.knowsMobile) {
                project.mobileDays -= 1;
                WorkDay workDay;
                if (willBeLate) {
                    workDay = new WorkDay(3, date.plusDays(1), willBeWorkingProject);
                } else {
                    workDay = new WorkDay(3, date, willBeWorkingProject);
                }
                if (willBeWorkingProject) {
                    project.daysToFinishTesting -= 1;
                }
                workDay.coWorker = coWorker;
                project.workDays.add(workDay);
                isOK = true;
            }
        }
        return isOK;
    }

    //Employees testing the code
    public boolean testingProject(@NotNull Project project, LocalDate date) {

        boolean isOK = false;

        boolean workThisDay = ThreadLocalRandom.current().nextInt(1, 101) >= 5;
        if (workThisDay) {
            int numberOfProgrammers = 0;
            for (Worker worker : workers) {
                if (worker instanceof Programmer) {
                    numberOfProgrammers++;
                }
            }
            if (numberOfProgrammers >= 3) {
                for (WorkDay workDay : project.workDays) {
                    if (!workDay.isTested) {
                        workDay.isTested = true;
                        WorkDay workDay1 = new WorkDay(2, date, true);
                        project.workDays.add(workDay1);
                    } else {
                        WorkDay workDay1 = new WorkDay(2, date, true);
                        project.workDays.add(workDay1);
                    }
                }
                project.daysToFinishTesting = 0;
                project.isProjectRunnable = true;
                isOK = true;
            } else {
                if (project.daysToFinishTesting > 0) {
                    for (WorkDay workDay : project.workDays) {
                        if (!workDay.isTested) {
                            workDay.isTested = true;
                            WorkDay workDay1 = new WorkDay(2, date, true);
                            project.workDays.add(workDay1);
                            project.daysToFinishTesting -= 1;
                            isOK = true;
                            break;
                        }
                    }
                }
            }
        }
        return isOK;
    }

    //Testing the project before releasing to the client
    public void checkingProject(@NotNull Project project, @NotNull LocalDate date) {

        //Is the project finished?
        boolean isProjectFinished = (project.wordpressDays + project.databaseDays +
                project.backendDays + project.frontendDays + project.prestashopDays + project.mobileDays) == 0;

        //Is the project runnable?
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

        //Is the project late?
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
                    project.dateWhenClientGetBackProject.plusDays(7).isBefore(project.dateWhenProjectIsWeekLate)) {
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
                        project.projectRealPrice = project.projectPrice - project.penaltyFixedByContract - project.paymentOnAccount;
                    } else {
                        project.projectRealPrice = project.projectPrice - project.paymentOnAccount;
                    }
                    if (ThreadLocalRandom.current().nextInt(1, 101) >= 30) {
                        if (project.dateOfPayment.isBefore(date)) {
                            project.dateWhenProjectIsPaidByClient = date;
                        } else {
                            project.dateWhenProjectIsPaidByClient = project.dateOfPayment;
                        }
                    } else {
                        project.dateWhenProjectIsPaidByClient = project.dateOfPayment.plusWeeks(1);
                    }
                } else if (isProjectLateMoreAWeek) {
                    project.projectRealPrice = project.projectPrice - project.penaltyFixedByContract - project.paymentOnAccount;
                    if (project.dateOfPayment.isBefore(date)) {
                        project.dateWhenProjectIsPaidByClient = date;
                    } else {
                        project.dateWhenProjectIsPaidByClient = project.dateOfPayment;
                    }
                } else {
                    project.projectRealPrice = project.projectPrice - project.paymentOnAccount;
                    project.dateWhenProjectIsPaidByClient = project.dateOfPayment;
                }
            } else {
                project.projectRealPrice = 0.0 - project.paymentOnAccount;
                project.dateWhenProjectIsPaidByClient = date;
            }
        }

        //Regular client
        if (project.typeOfClient == 2) {
            if (isProjectFinished) {
                if (project.isProjectRunnable) {
                    if (isProjectLate) {
                        project.projectRealPrice = project.projectPrice - project.penaltyFixedByContract - project.paymentOnAccount;
                        if (project.dateOfPayment.isBefore(date)) {
                            project.dateWhenProjectIsPaidByClient = date;
                        } else {
                            project.dateWhenProjectIsPaidByClient = project.dateOfPayment;
                        }
                    } else {
                        project.projectRealPrice = project.projectPrice - project.paymentOnAccount;
                        project.dateWhenProjectIsPaidByClient = project.dateOfPayment;
                    }
                } else {
                    if (ThreadLocalRandom.current().nextInt(0, 2) == 0) {
                        project.projectRealPrice = 0.0 - project.paymentOnAccount;
                        if (isProjectLate) {
                            if (project.dateOfPayment.isBefore(date)) {
                                project.dateWhenProjectIsPaidByClient = date;
                            } else {
                                project.dateWhenProjectIsPaidByClient = project.dateOfPayment;
                            }
                        } else {
                            project.dateWhenProjectIsPaidByClient = project.dateOfPayment;
                        }
                    } else {
                        if (isProjectLate) {
                            project.projectRealPrice = project.projectPrice - project.penaltyFixedByContract - project.paymentOnAccount;
                            if (project.dateOfPayment.isBefore(date)) {
                                project.dateWhenProjectIsPaidByClient = date;
                            } else {
                                project.dateWhenProjectIsPaidByClient = project.dateOfPayment;
                            }
                        } else {
                            project.projectRealPrice = project.projectPrice - project.paymentOnAccount;
                            project.dateWhenProjectIsPaidByClient = project.dateOfPayment;
                        }
                    }
                }
            } else {
                project.projectRealPrice = 0.0 - project.paymentOnAccount;
                project.dateWhenProjectIsPaidByClient = date;
            }

        }

        //Cheater
        if (project.typeOfClient == 3) {
            if (isProjectFinished) {
                if (project.isProjectRunnable) {
                    if (isProjectLate) {
                        project.projectRealPrice = project.projectPrice - project.penaltyFixedByContract - project.paymentOnAccount;
                        int x = ThreadLocalRandom.current().nextInt(1, 101);
                        if (x > 30) {
                            if (project.dateOfPayment.isBefore(date)) {
                                project.dateWhenProjectIsPaidByClient = date;
                            } else {
                                project.dateWhenProjectIsPaidByClient = project.dateOfPayment;
                            }
                        } else if (x >= 5) {
                            if (project.dateOfPayment.isBefore(date)) {
                                project.dateWhenProjectIsPaidByClient = date.plusWeeks(1);
                            } else {
                                project.dateWhenProjectIsPaidByClient = project.dateOfPayment.plusWeeks(1);
                            }
                        } else if (x > 1) {
                            if (project.dateOfPayment.isBefore(date)) {
                                project.dateWhenProjectIsPaidByClient = date.plusMonths(1);
                            } else {
                                project.dateWhenProjectIsPaidByClient = project.dateOfPayment.plusMonths(1);
                            }
                        } else if (x == 1) {
                            project.projectRealPrice = 0.0 - project.paymentOnAccount;
                            project.dateWhenProjectIsPaidByClient = date;
                        }
                    } else {
                        project.projectRealPrice = project.projectPrice - project.paymentOnAccount;
                        int x = ThreadLocalRandom.current().nextInt(1, 101);
                        if (x > 30) {
                            project.dateWhenProjectIsPaidByClient = project.dateOfPayment;
                        } else if (x >= 5) {
                            project.dateWhenProjectIsPaidByClient = project.dateOfPayment.plusWeeks(1);
                        } else if (x > 1) {
                            project.dateWhenProjectIsPaidByClient = project.dateOfPayment.plusMonths(1);
                        } else if (x == 1) {
                            project.projectRealPrice = 0.0 - project.paymentOnAccount;
                            project.dateWhenProjectIsPaidByClient = date;
                        }
                    }
                } else {
                    project.projectRealPrice = 0.0 - project.paymentOnAccount;
                    project.dateWhenProjectIsPaidByClient = date;
                }
            } else {
                project.projectRealPrice = 0.0 - project.paymentOnAccount;
                project.dateWhenProjectIsPaidByClient = date;
            }
        }
    }

    //Calculation of colleagues' salaries
    public double salaryCheckCoWorker(@NotNull ArrayList<Project> unfinishedProjects, @NotNull ArrayList<Project> finishedProjects, int idCoworker, @NotNull LocalDate date) {
        double salary = 0.0;
        int monthNumber = date.getMonthValue();
        for (Project project : finishedProjects) {
            if (project.dateWhenProjectIsPaidByClient.getMonthValue() == (monthNumber) && (project.dateWhenProjectIsPaidByClient.getYear() == date.getYear())) {
                for (WorkDay workDay : project.workDays) {
                    if (workDay.typeOfWorker == 3) {
                        assert workDay.coWorker != null;
                        if (workDay.coWorker.idWorker == idCoworker) {
                            salary += workDay.coWorker.salaryPerDay;
                        }
                    }
                }
            }
        }
        for (Project project : unfinishedProjects) {
            for (WorkDay workDay : project.workDays) {
                if (workDay.typeOfWorker == 3 && workDay.dateWhenWorksFinishes.getMonthValue() == date.getMonthValue()
                        && workDay.dateWhenWorksFinishes.getYear() == date.getYear()) {
                    assert workDay.coWorker != null;
                    if (workDay.coWorker.idWorker == idCoworker) {
                        salary += workDay.coWorker.salaryPerDay;
                    }
                }
            }
        }
        return salary;
    }
}





