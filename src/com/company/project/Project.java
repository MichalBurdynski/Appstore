package com.company.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Project {
    public final int projectIndex;
    public Integer frontendDays = 0;
    public Integer backendDays = 0;
    public Integer databaseDays = 0;
    public Integer mobileDays = 0;
    public Integer wordpressDays = 0;
    public Integer prestashopDays = 0;
    public Integer typeOfClient;
    public LocalDate generatingProjectDate;
    public LocalDate releaseProjectDate;
    public Double penaltyFixedByContract;
    public Double projectPrice;
    public Integer paymentDays;
    public LocalDate DateOfPayment;
    public Integer projectLevel;
    public boolean isProjectRunnable = false;
    public LocalDate dateWhenClientGetBackProject;
    public LocalDate dateWhenProjectIsWeekLate;
    public LocalDate dateWhenProjectIsMonthLate;
    public Double projectRealPrice;
    public LocalDate dateWhenProjectIsPaidByClient;
    public ArrayList<WorkDay> workDays;
    public int whoGeneratedProject;
    public int daysToFinishTesting;

    public Project(int projectIndex, int whoGeneratedProject, LocalDate date) {
        this.projectLevel = ThreadLocalRandom.current().nextInt(1,4);
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
        this.typeOfClient = ThreadLocalRandom.current().nextInt(1,4);
        this.generatingProjectDate = date;
        this.releaseProjectDate = this.generatingProjectDate.plusDays((long)(this.prestashopDays + this.databaseDays+this.wordpressDays+this.backendDays+this.mobileDays+this.frontendDays)*2);
        this.projectPrice = Math.floor(ThreadLocalRandom.current().nextDouble(3000.0, 15000.0));
        this.penaltyFixedByContract = Math.floor(ThreadLocalRandom.current().nextDouble(this.projectPrice/10, this.projectPrice/5));
        this.paymentDays = ThreadLocalRandom.current().nextInt(0, 5);
        this.dateWhenProjectIsWeekLate = this.releaseProjectDate.plusDays(7);
        this.dateWhenProjectIsMonthLate = this.releaseProjectDate.plusMonths(1);
        this.whoGeneratedProject = whoGeneratedProject;
        this.DateOfPayment = this.releaseProjectDate.plusDays(this.paymentDays);
        this.workDays = new ArrayList<>();
        this.daysToFinishTesting = this.prestashopDays + this.databaseDays+this.wordpressDays+this.backendDays+this.mobileDays+this.frontendDays;
    }

    @Override
    public String toString() {
        return "Project{" +
                "idProject='" + projectIndex + '\'' +
                ", frontendDays=" + frontendDays +
                ", backendDays=" + backendDays +
                ", databaseDays=" + databaseDays +
                ", mobileDays=" + mobileDays +
                ", wordpressDays=" + wordpressDays +
                ", prestashopDays=" + prestashopDays +
                ", releaseProjectDate=" + releaseProjectDate +
                ", penaltyFixedByContract=" + penaltyFixedByContract +
                ", projectPrice=" + projectPrice +
                ", paymentDays=" + paymentDays +
                ", projectLevel=" + projectLevel +
                ", days to finish tests=" + daysToFinishTesting +
                "}\n";
    }
}
