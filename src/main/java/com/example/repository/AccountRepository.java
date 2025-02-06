package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;
//Edited by Gabriel Evans
/* I used the examples from the lectures and coding labs to help construct methods for the repository class.
 * 
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

    @Query("FROM Account WHERE username = :userVar")
    Account doesUsernameAlreadyExist(@Param("userVar") String name);

    @Query("FROM Account WHERE username = :userVar AND password = :passVar")
    Optional<Account> doesUsernameAndPasswordExist(@Param("userVar") String user, @Param("passVar") String pass);
}
