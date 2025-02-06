package com.example.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

//Edited by Gabriel Evans
@Component
public class MessageService {
    private MessageRepository msgReposit;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.msgReposit = messageRepository;
    }

    public List<Message> getAllMessages() {
        return msgReposit.findAll();
    }

    /*The creation of the message will be successful if and only if the messageText is not blank, 
        is not over 255 characters, and postedBy refers to a real, existing user. */
    public Message addMessage(Message msg) {
        if(msg.getMessageText().isEmpty() || msg.getMessageText().length() > 255){
            return null;
        }
        return msgReposit.save(msg);

    }

    public Message getMessageById(int msgID){
        return  msgReposit.getById(msgID);
    }

    public List<Message> getAllMessagesByWhoPosted(int poster_ID){
        return msgReposit.getAllUsersMessages(poster_ID);
    }

    public void deleteMessage(int msgID){
        msgReposit.deleteById(msgID);
    }

    /*The update of a message should be successful if and only if the message id already 
    exists and the new message_text is not blank and is not over 255 characters. */
    public Message updateMessage(int msgID, String updateText){
        if(updateText.isEmpty() || updateText.length() > 255 ||
        this.msgReposit.getById(msgID) == null){
            return null;
        }
        Optional<Message> compareMessage = msgReposit.findById(msgID);
        if(compareMessage.isPresent()){
            Message msg = compareMessage.get();
            msg.setMessageText(updateText);
            msgReposit.save(msg);
        }
        return msgReposit.getById(msgID);
    }
}
