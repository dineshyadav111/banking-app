package com.app.banking_app.service;

import com.app.banking_app.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto CreateAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);
    AccountDto deposite(Long id,double amount);
    AccountDto withDraw(Long id,double amount);
    List<AccountDto> getAllAccounts();
    void deleteAccount(Long id);
}
