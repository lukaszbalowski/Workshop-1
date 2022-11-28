package pl.coderslab;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class TaskManager {

    static final String fileName = "tasks.csv";
    static final String[] options = {"add", "remove", "list", "exit"};

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String[][] tasks;
        showOptions(options);

        String input = scan.nextLine();
        if (input.equals("exit")) {
            System.out.println("Do zobaczenia!");
            System.exit(0);
        } else {
            System.out.println("Jeszcze nie gotowe. Spróbuj później");
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

    public static String[][] distributeDataToArray (String filename) throws IOException {

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



}
