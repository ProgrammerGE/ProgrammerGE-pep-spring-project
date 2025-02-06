package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Message;
//Edited by Gabriel Evans
public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query("FROM MESSAGE WHERE postedBy = :postedVar")
    List<Message> getAllUsersMessages(@Param("postedVar") int postedBy);
}
