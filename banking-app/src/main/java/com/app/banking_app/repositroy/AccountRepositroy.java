package com.app.banking_app.repositroy;

import com.app.banking_app.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepositroy extends JpaRepository<Account,Long> {
}
