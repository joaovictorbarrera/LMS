package org.joaobarrera.ui;

import org.joaobarrera.model.Patron;
import org.joaobarrera.service.Library;

import java.util.Scanner;

public class Menu {
    Library library;

    public Menu (Library library) {
        this.library = library;
    }

    // Loops menu until user chooses to exit
    public void launchMenu() {
        System.out.println("=============================================");
        System.out.println("= Welcome to the Library Management System! =");
        System.out.println("=============================================");

        Scanner sc = new Scanner(System.in);

        FileHandler.promptForFile(library, sc);

        if (library.hasPatrons()) {
            library.listPatrons();
            System.out.println("\nPress Enter to continue...");
            sc.nextLine();
        }

        do {
            System.out.println();
            System.out.println("===============================");
            System.out.println("=            LMS Menu         =");
            System.out.println("===============================");
            System.out.println("[1] Add new Patron");
            System.out.println("[2] Remove Patron");
            System.out.println("[3] Display all patrons");
            System.out.println("[4] Exit");
            System.out.println("===============================");
        } while (handleInput(sc));
    }

    // Returns false if user decides to exit
    private boolean handleInput(Scanner sc) {
        int input = -1;

        // Only accept 1 - 4
        while (input < 1 || input > 4) {
            if (sc.hasNextInt()) {
                input = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("[Warning] Please enter a valid number between 1 and 4.");
                sc.nextLine();
            }
        }

        // Match input to action
        switch (input) {
            case 1:
                try {
                    handleAddPatron(sc);
                } catch (Exception e) {
                    System.out.println("[Warning] Error adding patron: " + e.getMessage());
                }
                break;
            case 2:
                try {
                    handleRemovePatron(sc);
                } catch (Exception e) {
                    System.out.println("[Warning] Error removing patron: " + e.getMessage());
                }
                break;
            case 3:
                library.listPatrons();
                break;
            case 4:
                System.out.println("Exiting...");
                return false;
            default:
                System.out.println("[Warning] Invalid option");
        }

        // Keep it on the screen
        System.out.println("\nPress Enter to continue...");
        sc.nextLine();

        return true;
    }

    private void handleAddPatron(Scanner sc) {
        System.out.println("Please enter the patron's ID:");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Please enter the patron's name:");
        String name = sc.nextLine();
        System.out.println("Please enter the patron's address:");
        String address = sc.nextLine();
        System.out.println("Please enter the patron's overdue amount:");
        double overdueAmount = sc.nextDouble();
        sc.nextLine();

        Patron patron = new Patron(id, name, address, overdueAmount);
        if (library.addPatron(patron)) {
            System.out.println("[Success] Patron " + id + " added successfully");
            library.listPatrons();
        }
    }

    private void handleRemovePatron(Scanner sc) {
        System.out.println("Please enter the patron's ID:");
        int id = sc.nextInt();
        sc.nextLine();
        if (library.removePatron(id)) {
            System.out.println("[Success] Patron " + id + " removed successfully");
            library.listPatrons();
        }
    }
}
