package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class WinterDanceController {

    @FXML
    TextField name;

    @FXML
    TextField ticket;

    @FXML
    Button enter;

    @FXML
    TextField pathText;

    @FXML
    Button pathEnter;

    ArrayList<String> names = new ArrayList<String>();
    ArrayList<Integer> ticketNumbers = new ArrayList<Integer>();
    ArrayList<Boolean> checkStatus = new ArrayList<Boolean>();
    File file;
    Scanner input;
    String path = "C:\\Users\\ayush\\Desktop\\tickets.csv";

    public WinterDanceController() throws FileNotFoundException {
        file = new File(path);
        input = new Scanner(file);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] values = line.split(",");
            names.add(values[0]);
            ticketNumbers.add(Integer.parseInt(values[1]));
            checkStatus.add(Boolean.parseBoolean(values[2]));
        }
    }

    public void setMainScene(ActionEvent e) throws IOException {

        PrintWriter output = new PrintWriter("FilePath.txt");
        output.println(pathText.getText());
        output.close();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("WinterDance.fxml")));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void checkIn(ActionEvent e) throws FileNotFoundException {

        int ticketNumber = Integer.parseInt(ticket.getText());
        int index = ticketNumbers.indexOf(ticketNumber);

        if(index == -1) {
            System.out.println("Invalid ticket number");
        } else {
            if(checkStatus.get(index)) {
                System.out.println("You have already checked in");
            } else {
                checkStatus.set(index, true);
                System.out.println("You have been checked in");
            }
        }

        PrintWriter output = new PrintWriter("C:\\Users\\ayush\\Desktop\\tickets.csv");
        for(int i = 0; i < names.size(); i++) {
            output.println(names.get(i) + "," + ticketNumbers.get(i) + "," + checkStatus.get(i));
        }
        output.close();
    }
}

/*
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<Integer> ticketNumbers = new ArrayList<Integer>();
        ArrayList<Boolean> checkStatus = new ArrayList<Boolean>();

        File file = new File("tickets.csv");
        Scanner input = new Scanner(file);

        while(input.hasNextLine()) {
            String line = input.nextLine();
            String[] values = line.split(",");
            names.add(values[0]);
            ticketNumbers.add(Integer.parseInt(values[1]));
            checkStatus.add(Boolean.parseBoolean(values[2]));
        }

        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = userInput.nextLine();
        System.out.println("Enter your ticket number: ");
        int ticketNumber = userInput.nextInt();

        int index = ticketNumbers.indexOf(ticketNumber);
        if(index == -1) {
            System.out.println("Invalid ticket number");
        } else {
            if(checkStatus.get(index)) {
                System.out.println("You have already checked in");
            } else {
                checkStatus.set(index, true);
                System.out.println("You have been checked in");
            }
        }

        PrintWriter output = new PrintWriter(file);
        for(int i = 0; i < names.size(); i++) {
            output.println(names.get(i) + "," + ticketNumbers.get(i) + "," + checkStatus.get(i));
        }
        output.close();
 */