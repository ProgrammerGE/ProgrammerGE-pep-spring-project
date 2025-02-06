package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;
//Edited by Gabriel Evans
/* I used the examples from the lectures and coding labs to help construct methods for the repository class.
 * 
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query("FROM Message WHERE postedBy = :postedVar")
    List<Message> getAllUsersMessages(@Param("postedVar") int postedBy);

    @Query("FROM Message WHERE messageId = :idVar")
    Message getMessageById(@Param("idVar") int msgID);
}
