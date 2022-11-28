package pl.coderslab;


import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManager {

    static final String fileName = "tasks.csv";
    static final String[] options = {"add", "remove", "list", "exit"};
    static String[][] tasks;

    public static void main(String[] args) throws IOException {

        tasks = distributeDataToArray(fileName);
        Scanner scan = new Scanner(System.in);

        showOptions(options);

        String input = scan.nextLine();
        if (input.equals("exit")) {
        saveOnExit(fileName, tasks);
            System.out.println(ConsoleColors.RED + "Bye, bye");
            System.exit(0);


        } else if (input.equals("list")) {
            System.out.println("Oto Twoja lista zadań: ");
            for (int i = 0; i < tasks.length; i++) {
                System.out.print(i + 1 + ":" + " ");
                for (int j = 0; j < tasks[i].length; j++) {
                    System.out.print(tasks[i][j] + " ");
                }
                System.out.print("\n");
            }

        } else if (input.equals("add")) {
            addTask();
            System.out.println("done");

        } else if (input.equals("remove")) {
            removeTask(tasks, taskToDelete());
            System.out.println("done");

        } else {
            System.out.println("Wprowadziłeś nieprawidłową opcją. Do widzenia");
            System.exit(0);
        }

    }


    public static void showOptions(String[] arr) {
        System.out.println(ConsoleColors.BLUE);
        System.out.println("Please select an option, and press 'enter' :  " + ConsoleColors.RESET);
        for (String option : arr) {
            System.out.println(option);

        }

    }

    public static String[][] distributeDataToArray(String filename) throws IOException {

        Path fileLocation = Paths.get(fileName);
        if (!Files.exists(fileLocation)) {
            System.out.println("Plik nie istnieje");
            System.exit(0);
        }

        String[][] tab = {};

        try {
            List<String> strings = Files.readAllLines(fileLocation);
            tab = new String[strings.size()][strings.get(0).split(",").length];

            for (int i = 0; i < strings.size(); i++) {
                String[] split = strings.get(i).split(",");
                for (int j = 0; j < split.length; j++) {
                    tab[i][j] = split[j];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tab;


    }

    public static void addTask() { // brakuje zapisu pliku

        Scanner scan = new Scanner(System.in);
        System.out.println("Please add task description: ");
        String desc = scan.nextLine();
        System.out.println("Please add task due date: ");
        String dueDate = scan.nextLine();
        System.out.println("Is your task important: true/false");
        String importance = scan.nextLine();

        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length-1] = new String[3];
        tasks[tasks.length-1][0] = desc;
        tasks[tasks.length-1][1] = dueDate;
        tasks[tasks.length-1][2] = importance;

    }

    public static int taskToDelete() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please select number to remove: ");

        String numberToDelete = scan.nextLine();

        int numToDel = 0;
        try {
            numToDel = Integer.parseInt(numberToDelete);

        } catch (NumberFormatException e )
        {
            System.out.println("nieprawidłowa liczba, wprowadź ponownie"); // todo co jeśli będzie "0"?
            scan.nextLine();
        }

        if (numToDel == 0) {
            System.out.println("Nie ma takiego elementu w tablicy");
        }

        return numToDel;


    }

    public static void removeTask(String[][] tab, int index) {

        try {
            if (index < tab.length) {
                tasks = ArrayUtils.remove(tab, index);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Nie ma takiego elementu w tablicy");
        }




    }

    public static void saveOnExit (String fileName, String[][] tab) {
        Path fileLocation = Paths.get(fileName);

        String[] lines = new String[tasks.length];

        for (int i = 0; i < tab.length; i++) {
            lines[i] = String.join(",", tab[i]);
        }

        try {
            Files.write(fileLocation, Arrays.asList(lines));
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    }


