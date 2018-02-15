package com.konstantinoplebank.controller;

import com.konstantinoplebank.entity.Bill;
import com.konstantinoplebank.entity.User;
import com.konstantinoplebank.response.UserProfile;
import com.konstantinoplebank.service.UserService;
import com.konstantinoplebank.service.UserServiceImpl;
import com.konstantinoplebank.entity.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Set;

/**
 * Controller, which handle queries of {@link User}
 *
 * @author Konstantin Artushkevich
 * @version 1.0
 */

@RestController
@CrossOrigin
public class UserController {

    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody UserProfile user) throws Exception {
        ResponseEntity resp;
        if(!userService.getByName(user.getName()).isPresent()) {
            long id = userService.create(user);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(id)
                    .toUri());
            resp = new ResponseEntity<>(null, headers, HttpStatus.CREATED);
        }
        else {
            resp = new ResponseEntity<>("Already registered", null, HttpStatus.FORBIDDEN);
        }
        return resp;
    }

    @GetMapping(value = "/user/{id}")
    public @ResponseBody UserProfile userProfile(@PathVariable("id") long id) {
        return userService.getUserProfile(id);
    }

    @GetMapping(value = "/user/{id}/bills")
    public @ResponseBody Set<Bill> userBills(@PathVariable("id") long id) {
        return userService.getUserBills(id);
    }

    @GetMapping(value = "/user/{id}/transactions")
    public @ResponseBody Set<Transaction> userTransactions(@PathVariable("id") long id) {
        return userService.getUserTransactions(id);
    }

    @GetMapping(value = "/user/userlist")
    public @ResponseBody List<UserProfile> userList() {
        return userService.findAll();
    }

}
