package com.company.people;

import java.util.concurrent.ThreadLocalRandom;

public class Tester extends Worker{

    @Override
    public String toString() {
        return "Tester{" + super.toString() +
                ", salary=" + salary +
                "} \n";
    }

    public Tester(int idWorker) {
       super(idWorker);
       this.salary = Math.floor(ThreadLocalRandom.current().nextDouble(1500.0, 2501.0));
    }

}
