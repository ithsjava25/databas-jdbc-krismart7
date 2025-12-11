package com.example.service;

import com.example.model.Account;
import com.example.repository.AccountRepository;
import com.example.repository.RepositoryException;

import java.util.List;
import java.util.Optional;

public class AccountService {
    private final AccountRepository repo;

    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    public boolean validateLogin(String username, String password) {
        try {
            return repo.validateLogin(username, password);
        } catch (Exception e) {
            throw new RepositoryException("Error validating login", e);
        }
    }

    public long createAccount(String firstName, String lastName, String ssn, String password) {
        try {
            return repo.createAccount(firstName, lastName, ssn, password);
        } catch (Exception e) {
            throw new RepositoryException("Error creating account", e);
        }
    }

    public void updatePassword(long userId, String newPassword) {
        try {
            repo.updatePassword(userId, newPassword);
        } catch (Exception e) {
            throw new RepositoryException("Error updating password", e);
        }
    }

    public void deleteAccount(long userId) {
        try {
            repo.deleteAccount(userId);
        } catch (Exception e) {
            throw new RepositoryException("Error deleting account", e);
        }
    }

    public List<Account> listAccounts() {
        try {
            return repo.listAccounts();
        } catch (Exception e) {
            throw new RepositoryException("Error listing accounts", e);
        }
    }

    public Optional<Account> getById(long userId) {
        try {
            return repo.getById(userId);
        } catch (Exception e) {
            throw new RepositoryException("Error fetching account by ID", e);
        }
    }
}
