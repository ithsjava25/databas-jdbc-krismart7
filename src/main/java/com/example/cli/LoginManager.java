package com.example.cli;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginManager {


    // LOGIN LOOP
    boolean loggedIn = false;
            while (!loggedIn) {
        System.out.print("Username: ");
        String username = readLine(in);

        System.out.print("Password: ");
        String password = readLine(in);

        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT 1 FROM account WHERE name = ? AND password = ?")) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    loggedIn = true;
                    System.out.println("Login successful!");
                } else {
                    System.out.println("Invalid username or password");
                    System.out.print("Press 0 to exit or enter to retry: ");
                    String exit = readLine(in);
                    if ("0".equals(exit)) return;
                }
            }
        }
    }
}
