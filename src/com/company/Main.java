package com.company;

import com.company.people.*;
import com.company.project.Project;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {


    //Menus
    public static int userMenu(LocalDate date) {
        int userChoice;
        Scanner input = new Scanner(System.in);
        System.out.println("Actual date: " + date + "\n");
        System.out.println("Select option:");
        System.out.println("1 - let's start the day of hard work");
        System.out.println("0 - exit the game");
        userChoice = input.nextInt();
        return userChoice;
    }

    public static int mainMenu(LocalDate date, String companyName) {
        int userChoice;
        Scanner input = new Scanner(System.in);
        System.out.println("Actual date: " + date);
        System.out.println("Company name: " + companyName + "\n");
        System.out.println("Select option:");
        System.out.println("1 - sign the contract");
        System.out.println("2 - look for a client");
        System.out.println("3 - programming alone");
        System.out.println("4 - testing alone");
        System.out.println("5 - release the project to the client");
        System.out.println("6 - hire an employee");
        System.out.println("7 - dismiss an employee ");
        System.out.println("8 - accounting day");
        System.out.println("9 - check results");
        userChoice = input.nextInt();
        return userChoice;
    }

    public static int subMenuProject(ArrayList<Project> projects) {
        int userChoice;
        Scanner input = new Scanner(System.in);
        System.out.println(projects);
        System.out.println();
        System.out.println("Enter the idProject to sign the contract or enter '0' to exit.");

        userChoice = input.nextInt();
        return userChoice;
    }

    public static int subMenuHireWorkerGlobal() {
        int userChoice;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter '1' to hire an employee.");
        System.out.println("Enter '2' to hire an associate.");
        System.out.println("Enter '0' to exit.");

        userChoice = input.nextInt();
        return userChoice;

    }

    public static int subMenuHireWorkers(ArrayList<Worker> workers) {
        int userChoice;
        Scanner input = new Scanner(System.in);
        System.out.println(workers);
        System.out.println();
        System.out.println("Enter the number 'idWorker' to hire an employee or enter '0' to exit.");

        userChoice = input.nextInt();
        return userChoice;
    }

    public static int subMenuHireCoWorker(ArrayList<CoWorker> coWorkers) {
        int userChoice;
        Scanner input = new Scanner(System.in);
        System.out.println(coWorkers);
        System.out.println();
        System.out.println("Enter the number 'idWorker' to hire an associate or enter 0 to exit.");

        userChoice = input.nextInt();
        return userChoice;
    }

    public static int subMenuDismissWorkerGlobal() {
        int userChoice;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter 1 to dismiss an employee.");
        System.out.println("Enter 2 to dismiss an associate.");
        System.out.println("Enter 0 to exit.");

        userChoice = input.nextInt();
        return userChoice;

    }

    public static int subMenuDismissWorkers(ArrayList<Worker> workers) {
        int userChoice;
        Scanner input = new Scanner(System.in);
        System.out.println(workers);
        System.out.println();
        System.out.println("Enter the number 'idWorker' to dismiss an employee or enter 0 to exit.");

        userChoice = input.nextInt();
        return userChoice;
    }

    public static int subMenuDismissCoWorker(ArrayList<CoWorker> coWorkers) {
        int userChoice;
        Scanner input = new Scanner(System.in);
        System.out.println(coWorkers);
        System.out.println();
        System.out.println("Enter the number 'idWorker' to dismiss an associate or enter 0 to exit.");

        userChoice = input.nextInt();
        return userChoice;
    }

    public static int subMenuProjectWorking(ArrayList<Project> projects) {
        int userChoice;
        Scanner input = new Scanner(System.in);
        System.out.println(projects);
        System.out.println();
        System.out.println("Enter the number 'idProject' to work on enter 0 to exit.");

        userChoice = input.nextInt();
        return userChoice;
    }

    public static int subMenuProjectTesting(ArrayList<Project> projects) {
        int userChoice;
        Scanner input = new Scanner(System.in);
        System.out.println(projects);
        System.out.println();
        System.out.println("Enter the number 'idProject' to test on enter 0 to exit.");

        userChoice = input.nextInt();
        return userChoice;
    }

    public static int subMenuProjectRelease(ArrayList<Project> projects) {
        int userChoice;
        Scanner input = new Scanner(System.in);
        System.out.println(projects);
        System.out.println();
        System.out.println("Enter the number 'idProject' to release on enter 0 to exit.");

        userChoice = input.nextInt();
        return userChoice;
    }

    public static int subMenuResults() {
        int userChoice;
        Scanner input = new Scanner(System.in);
        System.out.println("Select option:");
        System.out.println("1 - available projects");
        System.out.println("2 - unfinished and released projects");
        System.out.println("3 - employees and associates");
        System.out.println("4 - account balance");
        System.out.println("Enter the number to show on enter '0' to exit.");
        userChoice = input.nextInt();
        return userChoice;
    }

    //Is the game lost
    public static int gameOverConditions(@NotNull Company company, int choice) {
        if (company.availableCash <= 0.0) {
            System.out.println("Your company " + company.companyName + " is bankrupt. GAME OVER !!!\n");
            choice = 0;
            //exit(0);
        }
        return choice;
    }

    public static void gameOverTotal(int choice1, int choice2) {
        if (choice1 == 0 && choice2 == 0) {
            System.out.println("GAME OVER !!!");
            exit(0);
        }
    }

    public static void main(String[] args) {

        int projectIndex = 1;
        int workerIndex = 1;
        int coWorkerIndex = 1;

        LocalDate actualDate = LocalDate.of(2020, 1, 1);

        ArrayList<Project> availableProjects = new ArrayList<>();
        ArrayList<Worker> availableWorkers = new ArrayList<>();
        ArrayList<CoWorker> availableCoWorkers = new ArrayList<>();

        //Add new workers, coworkers, available projects
        {
            for (int i = 1; i <= 3; i++) {
                CoWorker coWorker = new CoWorker(coWorkerIndex, i);
                availableCoWorkers.add(coWorker);
                coWorkerIndex++;
            }
            Programmer programmer = new Programmer(workerIndex);
            availableWorkers.add(programmer);
            workerIndex++;
            Seller seller = new Seller(workerIndex);
            availableWorkers.add(seller);
            workerIndex++;
            Tester tester = new Tester(workerIndex);
            availableWorkers.add(tester);
            workerIndex++;
            Programmer programmer1 = new Programmer(workerIndex);
            availableWorkers.add(programmer1);
            workerIndex++;
            Seller seller1 = new Seller(workerIndex);
            availableWorkers.add(seller1);
            workerIndex++;
            Tester tester1 = new Tester(workerIndex);
            availableWorkers.add(tester1);
            workerIndex++;

            availableProjects.add(new Project(projectIndex, 1, actualDate, 1));
            projectIndex++;
            availableProjects.add(new Project(projectIndex, 1, actualDate, 2));
            projectIndex++;
            availableProjects.add(new Project(projectIndex, 1, actualDate, 3));
            projectIndex++;
        }

        String companyName1 = "Great Apple";
        String companyName2 = "Micro Apple";
        Company company1 = new Company(companyName1);
        Company company2 = new Company(companyName2);

        System.out.println("Welcome in 'AppStore' game.");
        System.out.println("""
                Your task is to complete 3 level 3 projects,
                that was generated by vendors and were developed exclusively
                by hired programmers and tested only by employed testers.
                Work hard to have money to pay taxes and wages for your employees.
                Remember that an employee will leave your company when they are not paid.
                The game will over when company has no funds to pay taxes
                or the company account balance is less than 5000.0.
                
                REMEMBER TO ENTER NUMBERS ONLY, OTHERWISE THE APPLICATION WILL CRASH!!!
                """);

        int firstChoice;

        while (true) {
            firstChoice = userMenu(actualDate);

            int choice1 = -1;
            int choice = -1;
            choice = gameOverConditions(company1, choice);
            choice1 = gameOverConditions(company2, choice1);
            gameOverTotal(choice1, choice);
            switch (firstChoice) {
                case 1 -> {
                    while (choice != 0) {
                        choice = mainMenu(actualDate, companyName1);

                        switch (choice) {
                            case 1 -> {
                                int i = subMenuProject(availableProjects);
                                if (i != 0) {
                                    if (company1.setProject(i, availableProjects)) {
                                        projectIndex = company1.finishingDay(projectIndex, actualDate, availableProjects, availableWorkers, availableCoWorkers);
                                        choice = 0;
                                    }
                                }
                            }
                            case 2 -> {
                                projectIndex = company1.lookingForClient(availableProjects, projectIndex, actualDate);
                                projectIndex = company1.finishingDay(projectIndex, actualDate, availableProjects, availableWorkers, availableCoWorkers);
                                choice = 0;
                            }
                            case 3 -> {
                                int i = subMenuProjectWorking(company1.unfinishedProjects);
                                if (i != 0) {
                                    if (company1.workOnProjectAlone(i, actualDate)) {
                                        projectIndex = company1.finishingDay(projectIndex, actualDate, availableProjects, availableWorkers, availableCoWorkers);
                                        choice = 0;
                                    }
                                }
                            }
                            case 4 -> {
                                int i = subMenuProjectTesting(company1.unfinishedProjects);
                                if (i != 0) {
                                    if (company1.testingProject(i, actualDate)) {
                                        projectIndex = company1.finishingDay(projectIndex, actualDate, availableProjects, availableWorkers, availableCoWorkers);
                                        choice = 0;
                                    }
                                }
                            }
                            case 5 -> {
                                int i = subMenuProjectRelease(company1.unfinishedProjects);
                                if (i != 0) {
                                    if (company1.releaseProject(i, actualDate)) {
                                        projectIndex = company1.finishingDay(projectIndex, actualDate, availableProjects, availableWorkers, availableCoWorkers);
                                        choice = 0;
                                    }
                                }
                            }
                            case 6 -> {
                                int j;
                                int k;
                                int i = subMenuHireWorkerGlobal();
                                if (i == 1) {
                                    j = subMenuHireWorkers(availableWorkers);
                                    {
                                        if (j != 0) {
                                            int c = workerIndex;
                                            int x = company1.setWorker(j, availableWorkers, workerIndex);
                                            if (c != x) {
                                                projectIndex = company1.finishingDay(projectIndex, actualDate, availableProjects, availableWorkers, availableCoWorkers);
                                                choice = 0;
                                                workerIndex = x;
                                            }
                                        }
                                    }
                                } else if (i == 2) {
                                    k = subMenuHireCoWorker(availableCoWorkers);
                                    {
                                        if (k != 0) {
                                            if (company1.setCoWorker(k, availableCoWorkers)) {
                                                projectIndex = company1.finishingDay(projectIndex, actualDate, availableProjects, availableWorkers, availableCoWorkers);
                                                choice = 0;
                                            }
                                        }
                                    }
                                }
                            }
                            case 7 -> {
                                int j;
                                int k;
                                int i = subMenuDismissWorkerGlobal();
                                if (i == 1) {
                                    j = subMenuDismissWorkers(company1.workers);
                                    {
                                        if (j != 0) {
                                            if (company1.dismissWorker(j, availableWorkers)) {
                                                projectIndex = company1.finishingDay(projectIndex, actualDate, availableProjects, availableWorkers, availableCoWorkers);
                                                choice = 0;
                                            }
                                        }
                                    }
                                } else if (i == 2) {
                                    k = subMenuDismissCoWorker(company1.coWorkers);
                                    {
                                        if (k != 0) {
                                            if (company1.dismissCoWorker(k, availableCoWorkers)) {
                                                projectIndex = company1.finishingDay(projectIndex, actualDate, availableProjects, availableWorkers, availableCoWorkers);
                                                choice = 0;
                                            }
                                        }
                                    }
                                }
                            }
                            case 8 -> {
                                company1.payOffBills();
                                projectIndex = company1.finishingDay(projectIndex, actualDate, availableProjects, availableWorkers, availableCoWorkers);
                                choice = 0;
                            }
                            case 9 -> {
                                int k = subMenuResults();
                                switch (k) {
                                    case 1 -> {
                                        System.out.println("Available projects: \n");
                                        System.out.println(availableProjects);
                                    }
                                    case 2 -> {
                                        System.out.println("1Unfinished projects: \n");
                                        System.out.println(company1.unfinishedProjects);
                                        System.out.println("Released projects: \n");
                                        System.out.println(company1.finishedProjects);
                                    }
                                    case 3 -> {
                                        System.out.println("Employees: \n");
                                        System.out.println(company1.workers);
                                        System.out.println("Associates: \n");
                                        System.out.println(company1.coWorkers);
                                    }
                                    case 4 -> System.out.println("Account balance: " + company1.availableCash);
                                    default -> {
                                    }
                                }
                            }
                            default -> mainMenu(actualDate, companyName1);
                        }
                    }


                    while (choice1 != 0) {
                        choice1 = mainMenu(actualDate, companyName2);

                        switch (choice1) {
                            case 1 -> {
                                int i = subMenuProject(availableProjects);
                                if (i != 0) {
                                    if (company2.setProject(i, availableProjects)) {
                                        projectIndex = company2.finishingDay(projectIndex, actualDate, availableProjects, availableWorkers, availableCoWorkers);
                                        choice1 = 0;
                                    }
                                }
                            }
                            case 2 -> {
                                projectIndex = company2.lookingForClient(availableProjects, projectIndex, actualDate);
                                projectIndex = company2.finishingDay(projectIndex, actualDate, availableProjects, availableWorkers, availableCoWorkers);
                                choice1 = 0;
                            }
                            case 3 -> {
                                int i = subMenuProjectWorking(company1.unfinishedProjects);
                                if (i != 0) {
                                    if (company2.workOnProjectAlone(i, actualDate)) {
                                        projectIndex = company2.finishingDay(projectIndex, actualDate, availableProjects, availableWorkers, availableCoWorkers);
                                        choice1 = 0;
                                    }
                                }
                            }
                            case 4 -> {
                                int i = subMenuProjectTesting(company1.unfinishedProjects);
                                if (i != 0) {
                                    if (company2.testingProject(i, actualDate)) {
                                        projectIndex = company2.finishingDay(projectIndex, actualDate, availableProjects, availableWorkers, availableCoWorkers);
                                        choice1 = 0;
                                    }
                                }
                            }
                            case 5 -> {
                                int i = subMenuProjectRelease(company1.unfinishedProjects);
                                if (i != 0) {
                                    if (company2.releaseProject(i, actualDate)) {
                                        projectIndex = company2.finishingDay(projectIndex, actualDate, availableProjects, availableWorkers, availableCoWorkers);
                                        choice1 = 0;
                                    }
                                }
                            }
                            case 6 -> {
                                int j;
                                int k;
                                int i = subMenuHireWorkerGlobal();
                                if (i == 1) {
                                    j = subMenuHireWorkers(availableWorkers);
                                    {
                                        if (j != 0) {
                                            int c = workerIndex;
                                            int x = company2.setWorker(j, availableWorkers, workerIndex);
                                            if (c != x) {
                                                projectIndex = company2.finishingDay(projectIndex, actualDate, availableProjects, availableWorkers, availableCoWorkers);
                                                choice1 = 0;
                                                workerIndex = x;
                                            }
                                        }
                                    }
                                } else if (i == 2) {
                                    k = subMenuHireCoWorker(availableCoWorkers);
                                    {
                                        if (k != 0) {
                                            if (company2.setCoWorker(k, availableCoWorkers)) {
                                                projectIndex = company2.finishingDay(projectIndex, actualDate, availableProjects, availableWorkers, availableCoWorkers);
                                                choice1 = 0;
                                            }
                                        }
                                    }
                                }
                            }
                            case 7 -> {
                                int j;
                                int k;
                                int i = subMenuDismissWorkerGlobal();
                                if (i == 1) {
                                    j = subMenuDismissWorkers(company2.workers);
                                    {
                                        if (j != 0) {
                                            if (company2.dismissWorker(j, availableWorkers)) {
                                                projectIndex = company2.finishingDay(projectIndex, actualDate, availableProjects, availableWorkers, availableCoWorkers);
                                                choice1 = 0;
                                            }
                                        }
                                    }
                                } else if (i == 2) {
                                    k = subMenuDismissCoWorker(company2.coWorkers);
                                    {
                                        if (k != 0) {
                                            if (company2.dismissCoWorker(k, availableCoWorkers)) {
                                                projectIndex = company2.finishingDay(projectIndex, actualDate, availableProjects, availableWorkers, availableCoWorkers);
                                                choice1 = 0;
                                            }
                                        }
                                    }
                                }
                            }
                            case 8 -> {
                                company2.payOffBills();
                                projectIndex = company2.finishingDay(projectIndex, actualDate, availableProjects, availableWorkers, availableCoWorkers);
                                choice1 = 0;
                            }
                            case 9 -> {
                                int k = subMenuResults();
                                switch (k) {
                                    case 1: {
                                        System.out.println("Available projects: \n");
                                        System.out.println(availableProjects);
                                    }
                                    break;

                                    case 2: {
                                        System.out.println("1Unfinished projects: \n");
                                        System.out.println(company2.unfinishedProjects);
                                        System.out.println("Released projects: \n");
                                        System.out.println(company2.finishedProjects);
                                    }
                                    break;
                                    case 3: {
                                        System.out.println("Employees: \n");
                                        System.out.println(company2.workers);
                                        System.out.println("Associates: \n");
                                        System.out.println(company2.coWorkers);
                                    }
                                    break;
                                    case 4:
                                        System.out.println("Account balance: " + company2.availableCash);
                                        break;
                                    default:
                                        break;
                                }
                            }
                            default -> mainMenu(actualDate, companyName2);
                        }
                    }
                    actualDate = actualDate.plusDays(1);
                }
                case 0 -> exit(0);
                default -> userMenu(actualDate);
            }
        }
    }
}


