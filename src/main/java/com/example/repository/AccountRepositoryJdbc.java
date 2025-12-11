package com.example.repository;

import com.example.model.Account;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class AccountRepositoryJdbc extends BaseRepository<Account> implements AccountRepository {

    public AccountRepositoryJdbc(DataSource dataSource, boolean devMode) {
        super(dataSource, devMode);
    }

    @Override
    protected Account map(java.sql.ResultSet rs) throws java.sql.SQLException {
        return new Account(
                rs.getLong("user_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("ssn"),
                rs.getString("password"),
                rs.getString("name")
        );
    }

    @Override
    public List<Account> listAccounts() {
        return queryList("SELECT * FROM account");
    }

    @Override
    public Optional<Account> getById(long userId) {
        return querySingle("SELECT * FROM account WHERE user_id=?", userId);
    }

    @Override
    public boolean validateLogin(String username, String password) {
        return executeQuery(
                "SELECT COUNT(*) FROM account WHERE name=? AND password=?",
                rs -> { rs.next(); return rs.getInt(1) > 0; },
                username, password
        );
    }

    @Override
    public long createAccount(String firstName, String lastName, String ssn, String password) {
        String name = firstName.substring(0, 3) + lastName.substring(0, 3);
        String sql = "INSERT INTO account (first_name, last_name, ssn, password, name) VALUES (?,?,?,?,?)";
        return executeUpdateReturnId(sql, firstName, lastName, ssn, password, name);
    }

    @Override
    public void updatePassword(long userId, String newPassword) {
        executeUpdate("UPDATE account SET password=? WHERE user_id=?", newPassword, userId);
    }

    @Override
    public void deleteAccount(long userId) {
        executeUpdate("DELETE FROM account WHERE user_id=?", userId);
    }
}