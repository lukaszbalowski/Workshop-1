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
    scan.close();

    if ("list".equals(input)) {

        lineCount();


/*        Path path = Paths.get("tasks.csv");


        try {
            lineCount = (int) Files.lines(path).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(lineCount); // todo  ogarnąć wyjątki przy tej części*/

    }
    } catch (FileNotFoundException e) { // todo rozwiązać problem z wyjątkiem FileNotFoundException
        System.out.println("Błąd pliku" + e);
    }

    }

    public static int lineCount() { // todo metoda do zlicznia lini w tekście?
        int lineCount = 0;
        try {
            File file = new File ("tasks.csv");
            Scanner scan = new Scanner (file);

            while (scan.hasNextLine()) {
                scan.nextLine();
                lineCount++;
            }
            System.out.println(lineCount);
            scan.close();

        } catch (Exception e) {
            e.getStackTrace();
        }
        return lineCount;



    }
}
