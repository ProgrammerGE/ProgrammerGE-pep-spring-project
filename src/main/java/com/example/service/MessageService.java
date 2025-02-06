package com.example.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

//Edited by Gabriel Evans
/* I used the examples from the lectures and coding labs to help construct methods for the service class.
 * Along with my previous DAO methods as references from the previous project.
 */
@Service
public class MessageService {
    @Autowired
    private MessageRepository msgReposit;

    public List<Message> getAllMessages() {
        return msgReposit.findAll();
    }

    /*The creation of the message will be successful if and only if the messageText is not blank, 
        is not over 255 characters, and postedBy refers to a real, existing user. */
    public Message createMessage(Message msg) {
        //The getAllUsersMessages() helps check if the foreign key points to real existing user
        if(msg.getMessageText().isEmpty() || msg.getMessageText().length() > 255
        || msgReposit.getAllUsersMessages(msg.getPostedBy()).isEmpty()){
            return null;
        }
        
        return msgReposit.save(msg);

    }

    public Message getMessageById(int msgID){
        return  msgReposit.getMessageById(msgID);
    }

    public List<Message> getAllMessagesByWhoPosted(int poster_ID){
        return msgReposit.getAllUsersMessages(poster_ID);
    }

    public Integer deleteMessage(int msgID){
        Message msg = msgReposit.getMessageById(msgID);
        if(msg == null){
            return null;
        }
        msgReposit.deleteById(msgID);
        return 1;
    }

    /*The update of a message should be successful if and only if the message id already 
    exists and the new message_text is not blank and is not over 255 characters. */
    public Message updateMessage(int msgID, String updateText){
        String [] splitMessage = updateText.split("\"");
        //the [3] block contains the updatedText message to test
        if(splitMessage[3].isEmpty() || splitMessage[3].length() > 255 ||
        this.msgReposit.getMessageById(msgID) == null){
            return null;
        }
        Optional<Message> compareMessage = msgReposit.findById(msgID);
        if(compareMessage.isPresent()){
            Message msg = compareMessage.get();
            msg.setMessageText(updateText);
            msgReposit.save(msg);
        }
        return this.msgReposit.getMessageById(msgID);
    }
}
