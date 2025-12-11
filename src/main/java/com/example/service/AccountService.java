package com.example.service;

import com.example.model.Account;
import com.example.repository.AccountRepository;
import com.example.repository.RepositoryException;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing accounts. Wraps AccountRepository
 * and handles exceptions by throwing RepositoryException.
 */
public class AccountService {
    private final AccountRepository repo;

    /**
     * Constructs the service with a given repository.
     *
     * @param repo the {@link AccountRepository} to use for data access
     */
    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    /**
     * Validates a user's login credentials.
     *
     * @param username the username to validate
     * @param password the password to validate
     * @return true if the credentials are valid, false otherwise
     * @throws RepositoryException if an error occurs while accessing the repository
     */
    public boolean validateLogin(String username, String password) {
        try {
            return repo.validateLogin(username, password);
        } catch (Exception e) {
            throw new RepositoryException("Error validating login", e);
        }
    }

    /**
     * Creates a new account in the system.
     *
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     * @param ssn       the Swedish personal identity number (######-####)
     * @param password  the password for the account
     * @return the generated user ID of the new account
     * @throws RepositoryException if an error occurs while creating the account
     */
    public long createAccount(String firstName, String lastName, String ssn, String password) {
        try {
            return repo.createAccount(firstName, lastName, ssn, password);
        } catch (Exception e) {
            throw new RepositoryException("Error creating account", e);
        }
    }

    /**
     * Updates the password for an existing account.
     *
     * @param userId      the ID of the account to update
     * @param newPassword the new password to set
     * @throws RepositoryException if an error occurs while updating the password
     */
    public void updatePassword(long userId, String newPassword) {
        try {
            repo.updatePassword(userId, newPassword);
        } catch (Exception e) {
            throw new RepositoryException("Error updating password", e);
        }
    }

    /**
     * Deletes an account by its user ID.
     *
     * @param userId the ID of the account to delete
     * @throws RepositoryException if an error occurs while deleting the account
     */
    public void deleteAccount(long userId) {
        try {
            repo.deleteAccount(userId);
        } catch (Exception e) {
            throw new RepositoryException("Error deleting account", e);
        }
    }

    /**
     * Lists all accounts in the system.
     *
     * @return a {@link List} of {@link Account} objects
     * @throws RepositoryException if an error occurs while retrieving accounts
     */
    public List<Account> listAccounts() {
        try {
            return repo.listAccounts();
        } catch (Exception e) {
            throw new RepositoryException("Error listing accounts", e);
        }
    }

    /**
     * Retrieves an account by its user ID.
     *
     * @param userId the ID of the account to retrieve
     * @return an {@link Optional} containing the {@link Account} if found, otherwise empty
     * @throws RepositoryException if an error occurs while fetching the account
     */
    public Optional<Account> getById(long userId) {
        try {
            return repo.getById(userId);
        } catch (Exception e) {
            throw new RepositoryException("Error fetching account by ID", e);
        }
    }
}
