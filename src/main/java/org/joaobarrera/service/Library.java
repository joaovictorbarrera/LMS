package org.joaobarrera.service;

import org.joaobarrera.model.Patron;

import java.util.ArrayList;
import java.util.List;

public class Library {
    List<Patron> patrons;

    public Library() {
        patrons = new ArrayList<>();
    }

    public boolean addPatron(Patron patron) {
        if (patronIDExists(patron.getId())) {
            System.out.println("[Warning] Error adding patron: Patron with id " + patron.getId() + " already exists");
            return false;
        }

        patrons.add(patron);
        return true;
    }

    public boolean hasPatrons() {
        return !patrons.isEmpty();
    }

    private boolean patronIDExists(int id) {
        return patrons.stream().anyMatch(p -> p.getId() == id);
    }

    public boolean removePatron(int id) {
        boolean removed = patrons.removeIf(p -> p.getId() == id);
        if (!removed) {
            System.out.println("[Warning] Error removing patron: Patron with id " + id + " not found");
        }
        return removed;
    }

    public void listPatrons() {
        if (!hasPatrons()) {
            System.out.println("[Warning] No patrons to display");
            return;
        }
        // Header
        System.out.println("\n==============================================================================================================");
        String header = String.format("%-20s %-20s %-50s %-10s", "Patron ID", "Name", "Address", "Overdue Amount");
        System.out.println(header);
        System.out.println("==============================================================================================================");

        // Patrons
        patrons.forEach(System.out::println);
        System.out.println("==============================================================================================================");
    }
}
