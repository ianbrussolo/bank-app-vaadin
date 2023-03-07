package com.bankapp.application.data.service;

import com.bankapp.application.data.entity.Account;
import com.bankapp.application.data.entity.User;
import com.bankapp.application.data.repository.AccountRepository;
import com.bankapp.application.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository,
                          UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }
    public List<Account> findAllAccounts(Long userId) {
        User user = userRepository.findByUserId(userId);
        return accountRepository.findByUser(user);
    }
    public Account getAccountById(Long accountId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        return optionalAccount.orElse(null);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public void deleteAccount(Account account) {
        accountRepository.delete(account);
    }

    public boolean isAccountBelongsToUser(Long accountId, Long userId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        return optionalAccount.isPresent() && optionalAccount.get().getUser().equals(userId);
    }

}
