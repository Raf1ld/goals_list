package com.panda.goals_list.controller;

import com.panda.goals_list.model.Goal;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

import javax.swing.plaf.ProgressBarUI;

public class MainController {
    @FXML private TextField nameField;
    @FXML private TextField amountField;
    @FXML private Label resultLabel;
    @FXML private ProgressBar progressBar;
    @FXML private Label progressTextLabel;
    @FXML private TextField depositField;

    private Goal currentGoal;

    @FXML
    public void onAddGoalClicked(ActionEvent event) {
        String goalName = nameField.getText();
        String amountText = amountField.getText();

        try {
            double targetAmount = Double.parseDouble(amountText);
            currentGoal = new Goal(goalName, targetAmount);
            resultLabel.setText("Ты добавил новую цель: " + currentGoal.getName() + ". Успехов с её реализацией)" );
            resultLabel.setStyle("-fx-text-fill: green;");
            updateProgressUI();
            nameField.clear();
            amountField.clear();
        }
        catch (NumberFormatException e) {
            resultLabel.setText("Введи, пожалуйста, корректную сумму)");
            resultLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    public void onDepositClicked(ActionEvent event) {
        if (currentGoal == null) {
            resultLabel.setText("Сначала создай цель");
            resultLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            double depositAmount = Double.parseDouble(depositField.getText());
            currentGoal.addMoney(depositAmount);
            updateProgressUI();
            depositField.clear();
            resultLabel.setText("Копилка пополнена на " + depositAmount + ".");
            resultLabel.setStyle("-fx-text-fill: green;");
        } catch (NumberFormatException e) {
            resultLabel.setText("Введи, пожалуйста, корректную сумму)");
            resultLabel.setStyle("-fx-text-fill: red;");
        }
    }


    private void updateProgressUI() {
        if (currentGoal != null) {
            progressBar.setProgress(currentGoal.getProgress());
            progressTextLabel.setText(currentGoal.getCurrentAmount() + " / " + currentGoal.getTargetAmount());
        }
    }
}
