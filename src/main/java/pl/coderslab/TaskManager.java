package pl.coderslab;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class TaskManager {


    public static void main(String[] args) {


    try {
        String[] options = {"Please select an option:", "add", "remove", "list", "exit"};
        String[][] tasks = {};
    for (String menu: options) {
        System.out.println(menu);
    };

    Scanner scan = new Scanner (System.in);
    System.out.println("Please select your choice, and press enter: ");

    String input = scan.nextLine();

    if ("list".equals(input)) {

        Path path = Paths.get("tasks.csv");
        int lineCount = 0;

        try {
            lineCount = (int) Files.lines(path).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(lineCount); // todo  ogarnąć wyjątki przy tej części

    }
    } catch (FileNotFoundException e) {
        System.out.println("Błąd pliku" + e);
    }

    }
}
