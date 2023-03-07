package com.bankapp.application.data.repository;

import com.bankapp.application.data.entity.Account;
import com.bankapp.application.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
//    @Query("select account from Account account" +
//            "where user_id = account.user_id")
    List<Account> findByUser(User user);

//    @Override
//    <S extends Account> S save(S entity);

//    @Transactional
//    default Account createAccount(Account account) {
//        return save(account);
//    }
}
