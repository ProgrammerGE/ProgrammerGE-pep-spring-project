package com.example.controller;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.DuplicateUserException;
import com.example.exception.LoginFailureException;
import com.example.service.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
//Edited by Gabriel Evans
@RestController
public class SocialMediaController {
    @Autowired
    private AccountService accntService;
    @Autowired
    private MessageService msgService;

    /*User Stories:
     * 1: Our API should be able to process new User registrations.
     * 2: Our API should be able to process User logins.
     * 3: Our API should be able to process the creation of new messages.
     * 4: Our API should be able to retrieve all messages.
     * 5: Our API should be able to retrieve a message by its ID.
     * 6: Our API should be able to delete a message identified by a message ID.
     * 7: Our API should be able to update a message text identified by a message ID.
     * 8: Our API should be able to retrieve all messages written by a particular user.
     */

     /*1: Our API should be able to process new User registrations
      * on the endpoint POST localhost:8080/register.
      * - If the registration is not successful due to a duplicate username, the response status should be 409. (Conflict)
      * - If the registration is not successful for some other reason, the response status should be 400. (Client error)
      * TODO: Work on this
      */
    @PostMapping(value = "/register")
    public ResponseEntity createAccount(@RequestBody Account requestBod) throws Exception{
        try{            
            Account newAccount = accntService.createAccount(requestBod);
            return ResponseEntity.status(200).body(newAccount);
        }catch(DuplicateUserException e){
            return ResponseEntity.status(409).body("Conflict");
        }catch(LoginFailureException e){
            return ResponseEntity.status(400).body("Client error");
        }
    }

    /* 2: Our API should be able to process User logins;
     *  verify my login on the endpoint POST localhost:8080/login
     * - If the login is not successful, the response status should be 401. (Unauthorized)
     * TODO: Work on this
     */
    @PostMapping(value = "/register")
    public ResponseEntity verifyAccount(@RequestBody Account requestBod){
        Account accnt = accntService.getAccount(requestBod);
        if(accnt == null)
            return ResponseEntity.status(401).body("Unauthorized");

        return ResponseEntity.status(200).body(accnt);
    }

    /* 3: Our API should be able to process the creation of new messages.
     * Submit a new post on the endpoint POST localhost:8080/messages
     * - If the creation of the message is not successful, the response status should be 400. (Client error)
     * TODO: Work on this
     */
    @PostMapping(value = "/messages")
    public ResponseEntity createMessage(@RequestBody Message requestBod){
        return ResponseEntity.status(400).body("Client error");
    }

    /* 4: Our API should be able to retrieve all messages.
     * submit a GET request on the endpoint GET localhost:8080/messages
     * The response status should always be 200, which is the default.
     * TODO: Work on this
     */
    @GetMapping("/messages/")
    public ResponseEntity getAllMessages(){
        return ResponseEntity.status(200).body(msgService.getAllMessages());
    }

    /* 5: Our API should be able to retrieve a message by its ID.
     * submit a GET request on the endpoint GET localhost:8080/messages/{messageId}.
     * The response status should always be 200, which is the default.
     * TODO: Work on this
     */
    @GetMapping("/messages/{messageId}")
    public ResponseEntity getMessageById(@PathVariable int messageId){
        return ResponseEntity.status(200).body(msgService.getMessageById(messageId));
    }

    /* 6: Our API should be able to delete a message identified by a message ID.
     * submit a DELETE request on the endpoint DELETE localhost:8080/messages/{messageId}.
     * If the message did not exist, the response status should be 200, but the response body should be empty.
     * TODO: work on this
     */
    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity deleteMessageById(@PathVariable int messageId){
        return ResponseEntity.status(200).body(msgService.deleteMessage(messageId));
    }

    /* 7: Our API should be able to update a message text identified by a message ID.
     * submit a PATCH request on the endpoint PATCH localhost:8080/messages/{messageId}.
     * - If the update of the message is not successful for any reason, the response status should be 400. (Client error)
     * TODO: work on this
     */
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity updateMessageById(@PathVariable int messageId, @RequestBody String updatedMessage){
        Message msg = msgService.updateMessage(messageId, updatedMessage);
        if(msg == null){
            return ResponseEntity.status(400).body("Client error");
        }
        return ResponseEntity.status(200).body(msg);
    }

    /* 8: Our API should be able to retrieve all messages written by a particular user.
     * submit a GET request on the endpoint GET localhost:8080/accounts/{accountId}/messages.
     * The response status should always be 200, which is the default.
     * TODO: work on this
     */
    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity getAllMessageByUser(@PathVariable int posterId){        
        return ResponseEntity.status(200).body(msgService.getAllMessagesByWhoPosted(posterId));
    }
}
