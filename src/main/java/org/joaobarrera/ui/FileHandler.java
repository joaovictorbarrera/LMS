package org.joaobarrera.ui;

import org.joaobarrera.model.Patron;
import org.joaobarrera.service.Library;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler {
    public static void promptForFile(Library library, Scanner sc) {
        // Loop until the user chooses to skip or successfully loads a file
        while (true) {
            System.out.println("[1] Load patrons from a text file");
            System.out.println("[2] Take me to the main menu");

            int input = -1;
            // Only accept 1 or 2
            while (input != 1 && input != 2) {
                if (sc.hasNextInt()) {
                    input = sc.nextInt();
                    sc.nextLine();
                } else {
                    System.out.println("[Warning] Please enter a valid number (1 or 2).");
                    sc.nextLine();
                }
            }

            if (input == 2) {
                // User chose to go to menu
                break;
            }

            // input == 1 → prompt for a file
            System.out.println("Please enter the text file's path:");
            String filePath = sc.nextLine();

            try {
                loadFile(library, filePath);
                break;
            } catch (FileNotFoundException e) {
                System.out.println("[Warning] File not found. Returning to menu...");
            } catch (Exception e) {
                System.out.println("[Warning] Error loading file: " + e.getMessage());
                System.out.println("Returning to menu...");
            }
        }
    }

    private static void loadFile(Library library, String filePath) throws FileNotFoundException {
        File file = new File(filePath);

        Scanner fileScanner = new Scanner(file);
        int lineCount = 0;
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] data = line.split("-");
            lineCount++;
            try {
                Patron patron = parsePatronLine(data, lineCount);
                library.addPatron(patron);
            } catch (Exception e) {
                System.out.println("[Warning] Error parsing line " + lineCount + ": " + e.getMessage());
            }

        }
    }

    private static Patron parsePatronLine(String[] data, int lineCount) {
        if (data.length != 4) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        int id = Integer.parseInt(data[0]);
        String name = data[1];
        String address = data[2];
        double overdue = Double.parseDouble(data[3]);

        if (Patron.isInvalidId(id)) throw new IllegalArgumentException("Invalid ID. Must be 7 digits");
        if (Patron.isInvalidName(name)) throw new IllegalArgumentException("Invalid name. Cannot be empty");
        if (Patron.isInvalidAddress(address)) throw new IllegalArgumentException("Invalid address. Cannot be empty");
        if (Patron.isInvalidOverdueAmount(overdue)) throw new IllegalArgumentException("Invalid overdue amount. Must be between 0 and 250");

        return new Patron(id, name, address, overdue);
    }
}
