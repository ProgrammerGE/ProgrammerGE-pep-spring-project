package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
//Edited by Gabriel Evans
@Controller
public class SocialMediaController {
    private AccountService accntService;
    private MessageService msgService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accntService = accountService;
        this.msgService = messageService;
    }
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
    public Account createAccount(@RequestBody Account requestBod){
        return requestBod;
    }

    /* 2: Our API should be able to process User logins;
     *  verify my login on the endpoint POST localhost:8080/login
     * TODO: Work on this
     */
    @PostMapping(value = "/register")
    public Account verifyAccount(@RequestBody Account requestBod){
        return requestBod;
    }

    /* 3: Our API should be able to process the creation of new messages.
     * Submit a new post on the endpoint POST localhost:8080/messages
     * TODO: Work on this
     */
    @PostMapping(value = "/messages")
    public Message createMessage(@RequestBody Message requestBod){
        return requestBod;
    }

    /* 4: Our API should be able to retrieve all messages.
     * submit a GET request on the endpoint GET localhost:8080/messages
     * TODO: Work on this
     */
    @GetMapping("/messages/")
    public Message getAllMessages(){
        return null;
    }

    /* 5: Our API should be able to retrieve a message by its ID.
     * submit a GET request on the endpoint GET localhost:8080/messages/{messageId}.
     * TODO: Work on this
     */
    @GetMapping("/messages/{messageId}")
    public Message getMessageById(@PathVariable int messageId){
        return null;
    }

    /* 6: Our API should be able to delete a message identified by a message ID.
     * submit a DELETE request on the endpoint DELETE localhost:8080/messages/{messageId}.
     * TODO: work on this
     */
    @DeleteMapping("/messages/{messageId}")
    public Message deleteMessageById(@PathVariable int messageId){
        return null;
    }

    /* 7: Our API should be able to update a message text identified by a message ID.
     * submit a PATCH request on the endpoint PATCH localhost:8080/messages/{messageId}.
     * TODO: work on this
     */
    @PatchMapping("/messages/{messageId}")
    public Message updateMessageById(@PathVariable int messageId, @RequestBody String updatedMessage){
        return null;
    }

    /* 8: Our API should be able to retrieve all messages written by a particular user.
     * submit a GET request on the endpoint GET localhost:8080/accounts/{accountId}/messages.
     * TODO: work on this
     */
    @GetMapping("/accounts/{accountId}/messages")
    public Message getAllMessageByUser(@PathVariable int messageId){
        return null;
    }
}
