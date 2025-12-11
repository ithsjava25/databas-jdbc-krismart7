package com.example.cli;

import com.example.service.AccountService;
import com.example.repository.RepositoryException;

public class LoginManager implements ExitMenuHandler {

    private final AccountService service;
    private final int maxAttempts;
    private final InputReader input;

    public LoginManager(AccountService service, InputReader input) {
        this(service, input, 5);
    }

    public LoginManager(AccountService service, InputReader input, int maxAttempts) {
        this.service = service;
        this.input = input;
        this.maxAttempts = maxAttempts;
    }

    public boolean login() {
        System.out.println("Type 0 to exit anytime, or 'menu' to go back.");

        int attempts = 0;
        while (attempts < maxAttempts) {
            var usernameWrapper = input.readString("Username");
            if (handleExitOrMenu(usernameWrapper.result())) return false;

            var passwordWrapper = input.readPassword("Password");
            if (handleExitOrMenu(passwordWrapper.result())) return false;

            try {
                if (service.validateLogin(usernameWrapper.value(), passwordWrapper.value())) {
                    System.out.println("\n✅ Login successful! Welcome, " + usernameWrapper.value() + "!\n");
                    return true;
                } else {
                    System.out.println("❌ Invalid username or password ❌");
                }
            } catch (RepositoryException e) {
                System.out.println("❌ Error validating login: " + e.getMessage());
            }

            attempts++;
            if (attempts < maxAttempts) {
                System.out.println("Attempts left: " + (maxAttempts - attempts));
            }
        }

        return false;
    }
}
