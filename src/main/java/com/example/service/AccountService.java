package com.example.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.entity.Account;
import com.example.exception.*;
import com.example.repository.AccountRepository;

//Edited by Gabriel Evans
@Component
public class AccountService {
    private AccountRepository accntReposit;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accntReposit = accountRepository;
    }
    
    /*The registration will be successful if and only if the username is not blank, the password is at least 4 characters long, 
        and an Account with that username does not already exist. */
    /* - If the registration is not successful due to a duplicate username, the response status should be 409. (Conflict)
     * - If the registration is not successful for some other reason, the response status should be 400. (Client error)
     */
    public Account createAccount(Account account) throws Exception {
        if(account.getUsername().isEmpty() || account.getPassword().length() < 4){
            throw new DuplicateUserException();
        }
        else if(accntReposit.doesUsernameAlreadyExist(account.getUsername()) != null){
            throw new LoginFailureException();
        }
        return accntReposit.save(account);
    }

    /*The login will be successful if and only if the username and password provided in the request 
        body JSON match a real account existing on the database */
    //- If the login is not successful, the response status should be 401. (Unauthorized)
    public Account getAccount(Account account){
        Optional<Account> compareAccount = accntReposit.findById(account.getAccountId());
        if(compareAccount.isPresent()){
            return compareAccount.get();
        }
        return null;
    }
}
