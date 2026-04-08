package com.panda.goals_list.model;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Goal {

    @Setter
    private String name;
    private double targetAmount;
    private double currentAmount;

    public Goal (String name, double targetAmount) {
        this.name = name;
        this.targetAmount = targetAmount;
        this.currentAmount = 0.0;
    }

    public void addMoney(double amount) {
        if (amount > 0.0){
            currentAmount += amount;
        }
    }

    public double getProgress() {
        if (targetAmount == 0) return 0.0;
        double progress = currentAmount / targetAmount;
        return progress > 1.0 ? 1.0 : progress;
    }

}
