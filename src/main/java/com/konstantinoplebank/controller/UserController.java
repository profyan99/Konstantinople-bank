package com.konstantinoplebank.controller;

import com.konstantinoplebank.entity.User;
import com.konstantinoplebank.response.SimpleResponse;
import com.konstantinoplebank.response.UserProfile;
import com.konstantinoplebank.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Controller, which handle queries of {@link User}
 *
 * @author Konstantin Artushkevich
 * @version 1.0
 */

@RestController
public class UserController {

    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody UserProfile user) throws Exception {
        ResponseEntity resp;
        if(!userService.existsByName(user.getName())) {
            long id = userService.create(user);
            if(id == 0) {
                resp = new ResponseEntity<>(new SimpleResponse("Server has some errors, while creating account"), HttpStatus.FORBIDDEN);
            }
            else {
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(id)
                        .toUri());
                resp = new ResponseEntity<>(headers, HttpStatus.CREATED);
            }
        }
        else {
            resp = new ResponseEntity<>(new SimpleResponse("Already registered"), HttpStatus.FORBIDDEN);
        }
        return resp;
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<?> userProfile(@PathVariable("id") long id) {
        return new ResponseEntity<>(userService.getUserProfile(id), HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}/bills")
    public ResponseEntity<?> userBills(@PathVariable("id") long id) {
        return new ResponseEntity<>(userService.getUserBills(id), HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}/transactions")
    public ResponseEntity<?> userAllTransactions(@PathVariable("id") long id) {
        return new ResponseEntity<>(userService.getUserTransactions(id), HttpStatus.OK);
    }

    @GetMapping(value = "/user/userlist")
    public ResponseEntity<?> userList() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

}
