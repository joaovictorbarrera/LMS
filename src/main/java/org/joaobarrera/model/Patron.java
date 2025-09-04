package org.joaobarrera.model;

public class Patron {
    int id;
    String name;
    String address;
    double overdueAmount;

    public Patron(int id, String name, String address, double overdueAmount) {
        if (isInvalidId(id)) throw new IllegalArgumentException("Invalid ID. Must be 7 digits");
        if (isInvalidName(name)) throw new IllegalArgumentException("Invalid name. Cannot be empty");
        if (isInvalidAddress(address)) throw new IllegalArgumentException("Invalid address. Cannot be empty");
        if (isInvalidOverdueAmount(overdueAmount)) throw new IllegalArgumentException("Invalid overdue amount. Must be between 0 and 250");
        this.id = id;
        this.name = name;
        this.address = address;
        this.overdueAmount = overdueAmount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getOverdueAmount() {
        return overdueAmount;
    }

    public static boolean isInvalidId(int id) {
        return String.valueOf(id).length() != 7;
    }

    public static boolean isInvalidName(String name) {
        return name == null || name.isEmpty();
    }

    public static boolean isInvalidAddress(String address) {
        return address == null || address.isEmpty();
    }

    public static boolean isInvalidOverdueAmount(double overdueAmount) {
        return overdueAmount < 0 || overdueAmount > 250;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-20s %-50s %-10.2f", id, name, address, overdueAmount);
    }
}
