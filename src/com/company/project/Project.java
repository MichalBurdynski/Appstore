package com.company.project;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Project {
    public final int projectIndex;
    public final @NotNull String projectName;
    public final Integer typeOfClient;
    public final LocalDate generatingProjectDate;
    public final @NotNull LocalDate releaseProjectDate;
    public final Double penaltyFixedByContract;
    public final Double projectPrice;
    public final Integer paymentDays;
    public final @NotNull LocalDate dateOfPayment;
    public final Integer projectLevel;
    public final @NotNull LocalDate dateWhenProjectIsWeekLate;
    public final @NotNull ArrayList<WorkDay> workDays;
    public final int whoGeneratedProject;
    public final double paymentOnAccount;
    public Integer frontendDays = 0;
    public Integer backendDays = 0;
    public Integer databaseDays = 0;
    public Integer mobileDays = 0;
    public Integer wordpressDays = 0;
    public Integer prestashopDays = 0;
    public boolean isProjectRunnable = false;
    public LocalDate dateWhenClientGetBackProject;
    public Double projectRealPrice;
    public LocalDate dateWhenProjectIsPaidByClient;
    public int daysToFinishTesting;

    //Project level
    //1 - basic project
    //2 - medium project
    //3 - big project

    //whoGenerateProject
    //1 - company owner
    //2 - seller

    //Client level
    //1 - hassle-free client
    //2 - regular client
    //3 - cheater

    public Project(int projectIndex, int whoGeneratedProject, LocalDate date) {
        this.projectLevel = ThreadLocalRandom.current().nextInt(1, 4);
        this.projectName = "Project No." + projectIndex;
        switch (this.projectLevel) {
            case 1: {
                int numberOfTechnology = ThreadLocalRandom.current().nextInt(1, 6);
                switch (numberOfTechnology) {
                    case 1:
                        this.frontendDays = ThreadLocalRandom.current().nextInt(1, 4);
                        break;
                    case 2:
                        this.backendDays = ThreadLocalRandom.current().nextInt(1, 4);
                        break;
                    case 3:
                        this.databaseDays = ThreadLocalRandom.current().nextInt(1, 4);
                        break;
                    case 4:
                        this.wordpressDays = ThreadLocalRandom.current().nextInt(1, 4);
                        break;
                    case 5:
                        this.prestashopDays = ThreadLocalRandom.current().nextInt(1, 4);
                        break;
                    default:
                        break;
                }
            }
            break;


            case 2: {
                int numberOfTechnology = ThreadLocalRandom.current().nextInt(2, 7);
                switch (numberOfTechnology) {
                    case 2:
                        this.frontendDays = ThreadLocalRandom.current().nextInt(1, 4);
                        break;
                    case 3: {
                        this.frontendDays = ThreadLocalRandom.current().nextInt(1, 4);
                        this.wordpressDays = ThreadLocalRandom.current().nextInt(1, 4);
                    }
                    break;

                    case 4: {
                        this.frontendDays = ThreadLocalRandom.current().nextInt(1, 4);
                        this.wordpressDays = ThreadLocalRandom.current().nextInt(1, 4);
                        this.databaseDays = ThreadLocalRandom.current().nextInt(1, 4);
                    }
                    break;

                    case 5: {
                        this.frontendDays = ThreadLocalRandom.current().nextInt(1, 4);
                        this.wordpressDays = ThreadLocalRandom.current().nextInt(1, 4);
                        this.databaseDays = ThreadLocalRandom.current().nextInt(1, 4);
                        this.prestashopDays = ThreadLocalRandom.current().nextInt(1, 4);
                    }
                    break;

                    case 6: {
                        this.frontendDays = ThreadLocalRandom.current().nextInt(1, 4);
                        this.wordpressDays = ThreadLocalRandom.current().nextInt(1, 4);
                        this.databaseDays = ThreadLocalRandom.current().nextInt(1, 4);
                        this.mobileDays = ThreadLocalRandom.current().nextInt(1, 4);
                    }
                    break;

                    default:
                        break;
                }
                this.backendDays = ThreadLocalRandom.current().nextInt(1, 4);
            }
            break;

            case 3: {
                int numberOfTechnology = ThreadLocalRandom.current().nextInt(3, 7);
                switch (numberOfTechnology) {
                    case 3: {
                        this.wordpressDays = ThreadLocalRandom.current().nextInt(1, 4);
                    }
                    break;

                    case 4: {
                        this.wordpressDays = ThreadLocalRandom.current().nextInt(1, 4);
                        this.databaseDays = ThreadLocalRandom.current().nextInt(1, 4);
                    }
                    break;

                    case 5: {
                        this.wordpressDays = ThreadLocalRandom.current().nextInt(1, 4);
                        this.databaseDays = ThreadLocalRandom.current().nextInt(1, 4);
                        this.prestashopDays = ThreadLocalRandom.current().nextInt(1, 4);
                    }
                    break;

                    case 6: {
                        this.wordpressDays = ThreadLocalRandom.current().nextInt(1, 4);
                        this.databaseDays = ThreadLocalRandom.current().nextInt(1, 4);
                        this.mobileDays = ThreadLocalRandom.current().nextInt(1, 4);
                    }
                    break;

                    default:
                        break;
                }
                this.backendDays = ThreadLocalRandom.current().nextInt(1, 4);
                this.frontendDays = ThreadLocalRandom.current().nextInt(1, 4);
            }
            break;

            default:
                break;
        }
        this.projectIndex = projectIndex;
        this.typeOfClient = ThreadLocalRandom.current().nextInt(1, 4);
        this.generatingProjectDate = date;
        this.releaseProjectDate = this.generatingProjectDate.plusDays(((this.prestashopDays + this.databaseDays + this.wordpressDays + this.backendDays + this.mobileDays + this.frontendDays) * 2L) + 7);
        this.projectPrice = Math.floor(ThreadLocalRandom.current().nextDouble(3000.0, 15000.0));
        this.penaltyFixedByContract = Math.floor(ThreadLocalRandom.current().nextDouble(this.projectPrice / 10, this.projectPrice / 5));
        this.paymentDays = ThreadLocalRandom.current().nextInt(0, 5);
        this.dateWhenProjectIsWeekLate = this.releaseProjectDate.plusDays(7);
        this.whoGeneratedProject = whoGeneratedProject;
        this.dateOfPayment = this.releaseProjectDate.plusDays(this.paymentDays);
        this.workDays = new ArrayList<>();
        this.daysToFinishTesting = this.prestashopDays + this.databaseDays + this.wordpressDays + this.backendDays + this.mobileDays + this.frontendDays;
        if (this.projectLevel == 3) {
            this.paymentOnAccount = 0.2 * this.projectPrice;
        } else {
            this.paymentOnAccount = 0.0;
        }
    }

    public Project(int projectIndex, int whoGeneratedProject, LocalDate date, int level) {
        this.projectLevel = level;
        this.projectName = "Project No." + projectIndex;
        switch (level) {
            case 1: {
                int numberOfTechnology = ThreadLocalRandom.current().nextInt(1, 6);
                switch (numberOfTechnology) {
                    case 1:
                        this.frontendDays = ThreadLocalRandom.current().nextInt(1, 4);
                        break;
                    case 2:
                        this.backendDays = ThreadLocalRandom.current().nextInt(1, 4);
                        break;
                    case 3:
                        this.databaseDays = ThreadLocalRandom.current().nextInt(1, 4);
                        break;
                    case 4:
                        this.wordpressDays = ThreadLocalRandom.current().nextInt(1, 4);
                        break;
                    case 5:
                        this.prestashopDays = ThreadLocalRandom.current().nextInt(1, 4);
                        break;
                    default:
                        break;
                }
            }
            break;


            case 2: {
                int numberOfTechnology = ThreadLocalRandom.current().nextInt(2, 7);
                switch (numberOfTechnology) {
                    case 2:
                        this.frontendDays = ThreadLocalRandom.current().nextInt(1, 4);
                        break;
                    case 3: {
                        this.frontendDays = ThreadLocalRandom.current().nextInt(1, 4);
                        this.wordpressDays = ThreadLocalRandom.current().nextInt(1, 4);
                    }
                    break;

                    case 4: {
                        this.frontendDays = ThreadLocalRandom.current().nextInt(1, 4);
                        this.wordpressDays = ThreadLocalRandom.current().nextInt(1, 4);
                        this.databaseDays = ThreadLocalRandom.current().nextInt(1, 4);
                    }
                    break;

                    case 5: {
                        this.frontendDays = ThreadLocalRandom.current().nextInt(1, 4);
                        this.wordpressDays = ThreadLocalRandom.current().nextInt(1, 4);
                        this.databaseDays = ThreadLocalRandom.current().nextInt(1, 4);
                        this.prestashopDays = ThreadLocalRandom.current().nextInt(1, 4);
                    }
                    break;

                    case 6: {
                        this.frontendDays = ThreadLocalRandom.current().nextInt(1, 4);
                        this.wordpressDays = ThreadLocalRandom.current().nextInt(1, 4);
                        this.databaseDays = ThreadLocalRandom.current().nextInt(1, 4);
                        this.mobileDays = ThreadLocalRandom.current().nextInt(1, 4);
                    }
                    break;

                    default:
                        break;
                }
                this.backendDays = ThreadLocalRandom.current().nextInt(1, 4);
            }
            break;

            case 3: {
                int numberOfTechnology = ThreadLocalRandom.current().nextInt(3, 7);
                switch (numberOfTechnology) {
                    case 3: {
                        this.wordpressDays = ThreadLocalRandom.current().nextInt(1, 4);
                    }
                    break;

                    case 4: {
                        this.wordpressDays = ThreadLocalRandom.current().nextInt(1, 4);
                        this.databaseDays = ThreadLocalRandom.current().nextInt(1, 4);
                    }
                    break;

                    case 5: {
                        this.wordpressDays = ThreadLocalRandom.current().nextInt(1, 4);
                        this.databaseDays = ThreadLocalRandom.current().nextInt(1, 4);
                        this.prestashopDays = ThreadLocalRandom.current().nextInt(1, 4);
                    }
                    break;

                    case 6: {
                        this.wordpressDays = ThreadLocalRandom.current().nextInt(1, 4);
                        this.databaseDays = ThreadLocalRandom.current().nextInt(1, 4);
                        this.mobileDays = ThreadLocalRandom.current().nextInt(1, 4);
                    }
                    break;

                    default:
                        break;
                }
                this.backendDays = ThreadLocalRandom.current().nextInt(1, 4);
                this.frontendDays = ThreadLocalRandom.current().nextInt(1, 4);
            }
            break;

            default:
                break;
        }
        this.projectIndex = projectIndex;
        this.typeOfClient = ThreadLocalRandom.current().nextInt(1, 4);
        this.generatingProjectDate = date;
        this.releaseProjectDate = this.generatingProjectDate.plusDays(((this.prestashopDays + this.databaseDays + this.wordpressDays + this.backendDays + this.mobileDays + this.frontendDays) * 2L) + 7);
        this.projectPrice = Math.floor(ThreadLocalRandom.current().nextDouble(3000.0, 15000.0));
        this.penaltyFixedByContract = Math.floor(ThreadLocalRandom.current().nextDouble(this.projectPrice / 10, this.projectPrice / 5));
        this.paymentDays = ThreadLocalRandom.current().nextInt(0, 5);
        this.dateWhenProjectIsWeekLate = this.releaseProjectDate.plusDays(7);
        this.whoGeneratedProject = whoGeneratedProject;
        this.dateOfPayment = this.releaseProjectDate.plusDays(this.paymentDays);
        this.workDays = new ArrayList<>();
        this.daysToFinishTesting = this.prestashopDays + this.databaseDays + this.wordpressDays + this.backendDays + this.mobileDays + this.frontendDays;
        if (this.projectLevel == 3) {
            this.paymentOnAccount = 20 * this.projectPrice / 100;
        } else {
            this.paymentOnAccount = 0.0;
        }
    }

    @Override
    public @NotNull String toString() {
        String x = "";
        switch (whoGeneratedProject) {
            case 1:
                x = "Project generated by company owner.\n";
                break;
            case 2:
                x = "Project generated by seller.\n";
                break;
            default:
                break;
        }
        return "\nidProject: " + projectIndex + "\n" +
                "Project Name: " + projectName + "\n" +
                "Frontend working days: " + frontendDays + "\n" +
                "Backend working days: " + backendDays + "\n" +
                "Database working days: " + databaseDays + "\n" +
                "Mobile working days: " + mobileDays + "\n" +
                "Wordpress working days: " + wordpressDays + "\n" +
                "Prestashop working days: " + prestashopDays + "\n" +
                "Release project date: " + releaseProjectDate + "\n" +
                "Payment project date: " + dateOfPayment + "\n" +
                "Project Value: " + projectPrice + "\n" +
                "Contractual penalty : " + penaltyFixedByContract + "\n" +
                "Payment on account: " + paymentOnAccount + "\n" +
                "Project Level: " + projectLevel + "\n" +
                x +
                "\n" +
                "Project is runnable: " + isProjectRunnable + "\n" +
                "Project working days remaining: " + (frontendDays + backendDays + databaseDays + mobileDays +
                prestashopDays + wordpressDays) + "\n" +
                "Project test working days remaining: " + daysToFinishTesting + "\n" +
                "Actual release project date: " + dateWhenClientGetBackProject + "\n" +
                "Actual payment project date: " + dateWhenProjectIsPaidByClient + "\n" +
                "Actual payment: " + projectRealPrice +
                "\n";

    }
}
