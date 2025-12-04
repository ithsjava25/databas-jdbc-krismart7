package com.example;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Optional<Account> findNameAndPassword(String name, String password);
    List<Account> findAll();
    long create(Account account);
    boolean updatePassword(long userId, String newPassword);
    boolean delete(long userId);
}
