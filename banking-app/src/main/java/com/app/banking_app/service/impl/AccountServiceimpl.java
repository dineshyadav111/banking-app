package com.app.banking_app.service.impl;

import com.app.banking_app.dto.AccountDto;
import com.app.banking_app.entity.Account;
import com.app.banking_app.mapper.AccountMapper;
import com.app.banking_app.repositroy.AccountRepositroy;
import com.app.banking_app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceimpl implements AccountService {

    private final AccountRepositroy accountRepositroy;
    public AccountServiceimpl(AccountRepositroy accountRepositroy) {
        this.accountRepositroy = accountRepositroy;
    }

    @Override
    public AccountDto CreateAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount=accountRepositroy.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account= accountRepositroy.findById(id)
                .orElseThrow(()->new RuntimeException("Account does not exist"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposite(Long id, double amount) {
        Account account= accountRepositroy.findById(id)
                .orElseThrow(()->new RuntimeException("Account does not exist"));
        double total = account.getBalance()+amount;
        account.setBalance(total);
        Account savedAccount = accountRepositroy.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withDraw(Long id, double amount) {
        Account account= accountRepositroy.findById(id)
                .orElseThrow(()->new RuntimeException("Account does not exist"));
        if(account.getBalance()<amount){
              throw new RuntimeException("Insufficient amount");
        }
        double total = account.getBalance()-amount;
        account.setBalance(total);
        Account savedAccount = accountRepositroy.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts=accountRepositroy.findAll();
        return accounts.stream().map((account) ->AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account= accountRepositroy.findById(id)
                .orElseThrow(()->new RuntimeException("Account does not exist"));

        accountRepositroy.deleteById(id);
    }
}
