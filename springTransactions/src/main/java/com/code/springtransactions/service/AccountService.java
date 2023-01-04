package com.code.springtransactions.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface AccountService {
    @Transactional
    void transfer(int inAccount, int outAccount, int money);
}
