package com.example.model;

public record Account(
        long userId,
        String firstName,
        String lastName,
        String ssn,
        String password,
        String name
) {

    @Override
    public String toString() {
        return "Account: " +
                "ID: " + userId +
                ", First: " + firstName +
                ", Last: " + lastName +
                ", SSN: " + ssn +
                ", Name: " + name;
    }
    // fix: skriv ut visuell record-account vid delete eller update password?
}