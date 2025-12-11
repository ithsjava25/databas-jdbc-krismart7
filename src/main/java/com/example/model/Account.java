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
}