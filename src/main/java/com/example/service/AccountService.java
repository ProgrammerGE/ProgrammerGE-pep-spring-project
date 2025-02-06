package com.example.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.*;
import com.example.repository.AccountRepository;

//Edited by Gabriel Evans
/* I used the examples from the lectures and coding labs to help construct methods for the service class.
 * Along with my previous DAO methods as references from the previous project.
 * 
 */
@Service
public class AccountService {
    @Autowired
    private AccountRepository accntReposit;
    
    /*The registration will be successful if and only if the username is not blank, the password is at least 4 characters long, 
        and an Account with that username does not already exist. */
    /* - If the registration is not successful due to a duplicate username, the response status should be 409. (Conflict)
     * - If the registration is not successful for some other reason, the response status should be 400. (Client error)
     */
    public Account createAccount(Account account) throws Exception {
        if(account.getUsername().isEmpty() || account.getPassword().length() < 4){
            throw new LoginFailureException();
        }
        else if(accntReposit.doesUsernameAlreadyExist(account.getUsername()) != null){
            throw new DuplicateUserException();
        }
        return accntReposit.save(account);
    }

    /*The login will be successful if and only if the username and password provided in the request 
        body JSON match a real account existing on the database */
    //- If the login is not successful, the response status should be 401. (Unauthorized)
    public Account getAccount(Account account){
        Optional<Account> compareAccount = accntReposit.doesUsernameAndPasswordExist(account.getUsername(), account.getPassword());
        if(compareAccount.isPresent()){
            return compareAccount.get();
        }
        return null;
    }
}
