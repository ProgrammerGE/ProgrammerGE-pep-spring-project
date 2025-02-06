package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;
//Edited by Gabriel Evans
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

    @Query("FROM ACCOUNT WHERE username = :userVar")
    Account doesUsernameAlreadyExist(@Param("userVar") String name);
}
