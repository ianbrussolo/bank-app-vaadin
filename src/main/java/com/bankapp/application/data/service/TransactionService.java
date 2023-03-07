package com.bankapp.application.data.service;

import com.bankapp.application.data.entity.Account;
import com.bankapp.application.data.entity.Transaction;
import com.bankapp.application.data.entity.User;
import com.bankapp.application.data.repository.AccountRepository;
import com.bankapp.application.data.repository.TransactionRepository;
import com.bankapp.application.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private final TransactionRepository transactionRepository;
    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              AccountRepository accountRepository,
                              UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public void createTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public boolean transferFunds(long recipientAccountId, long senderAccountId, double amount, int transactionType) {
        // Retrieve sender and recipient account entities from the database
        Account senderAccount = accountRepository.findById(senderAccountId).orElseThrow();
        Account recipientAccount = accountRepository.findById(recipientAccountId).orElseThrow();

        // Validate the transaction based on the transaction type
        if (transactionType == 1 && amount > 5000) {
            return false;
        } else if (transactionType == 2 && (amount <= 5000 || amount > 10000)) {
            return false;
        } else if (transactionType == 3 && amount <= 10000) {
            return false;
               // Throw an exception for an unknown transaction type
//                throw new IllegalArgumentException("Tipo da transação não definido");
        }

        // Update the sender and recipient account balances
        senderAccount.setBalance(senderAccount.getBalance() - amount);
        recipientAccount.setBalance(recipientAccount.getBalance() + amount);

        // Save the updated account entities to the database
        accountRepository.save(senderAccount);
        accountRepository.save(recipientAccount);

        return true;
    }

}
