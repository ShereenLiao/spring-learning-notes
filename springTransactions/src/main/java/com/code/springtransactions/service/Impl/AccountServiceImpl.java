package com.code.springtransactions.service.Impl;


import com.code.springtransactions.mapper.AccountMapper;
import com.code.springtransactions.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public void transfer(int inAccount, int outAccount, int money) {
        accountMapper.transferIn(inAccount, money);
        int i = 1/0;
        accountMapper.transferOut(outAccount, money);
    }
}
