package com.konstantinoplebank.controller;

import com.konstantinoplebank.entity.Bill;
import com.konstantinoplebank.entity.Transaction;
import com.konstantinoplebank.entity.User;
import com.konstantinoplebank.request.CreateBill;
import com.konstantinoplebank.request.CreateTransaction;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;
import java.util.Set;

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
    @PreAuthorize("hasRole('ROLE_ADMIN') or #userId == @userService.getByName(principal.getUsername()).get().getName()")
    public ResponseEntity<?> userProfile(@PathVariable("id") long userId) {
        return new ResponseEntity<>(userService.getUserProfile(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}/bills")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #userId == @userService.getByName(principal.getUsername()).get().getName()")
    public ResponseEntity<?> allUserBills(@PathVariable("id") long userId) {
        ResponseEntity resp;
        Set<Bill> bills = userService.getUserBills(userId);
        if(!bills.isEmpty()) {
            resp = new ResponseEntity<>(bills, HttpStatus.OK);
        }
        else
        {
            resp = new ResponseEntity<>(new SimpleResponse("No bills found"), HttpStatus.NOT_FOUND);
        }
        return resp;
    }

    @PostMapping(value = "/user/{id}/bills", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN') or #userId == @userService.getByName(principal.getUsername()).get().getName()")
    public ResponseEntity<?> createUserBill(@PathVariable("userId") long userId, @RequestBody CreateBill bill) {
        userService.createBill(userId, bill.getAmount());
        //TODO exceptions
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}/bills/{billId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #userId == @userService.getByName(principal.getUsername()).get().getName()")
    public ResponseEntity<?> userBill(@PathVariable("id") long userId, @PathVariable("billId") long billId) {
        ResponseEntity resp;
        Optional<Bill> bill = userService.getBill(billId);
        if(!bill.isPresent()) {
            resp = new ResponseEntity<>(bill.orElse(null), HttpStatus.OK);
        }
        else
        {
            resp = new ResponseEntity<>(new SimpleResponse("No bill with id: "+billId+" found"), HttpStatus.NOT_FOUND);
        }
        return resp;
    }


    @GetMapping(value = "/user/{id}/bills/{billId}/transactions")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #userId == @userService.getByName(principal.getUsername()).get().getName()")
    public ResponseEntity<?> userAllTransactions(@PathVariable("id") long userId) {
        ResponseEntity resp;
        Set<Transaction> transactions = userService.getUserTransactions(userId);
        if(!transactions.isEmpty()) {
            resp = new ResponseEntity<>(transactions, HttpStatus.OK);
        }
        else
        {
            resp = new ResponseEntity<>(new SimpleResponse("No transactions found"), HttpStatus.NOT_FOUND);
        }
        return resp;
    }

    @PostMapping(value = "/user/{id}/bills/{billId}/transactions", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN') or #userId == @userService.getByName(principal.getUsername()).get().getName()")
    public ResponseEntity<?> createUserTransaction(@PathVariable("id") long userId, @RequestBody CreateTransaction transaction) {
        userService.createTransaction(transaction.getAmount(),transaction.getDescription(), transaction.getBillid(), userId);
        //TODO exceptions
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}/bills/{billId}/transactions/{trId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #userId == @userService.getByName(principal.getUsername()).get().getName()")
    public ResponseEntity<?> userTransaction(@PathVariable("id") long userId, @PathVariable("trId") long trId) {
        ResponseEntity resp;
        Optional<Transaction> transaction = userService.getTransaction(trId);
        if(!transaction.isPresent()) {
            resp = new ResponseEntity<>(transaction.orElse(null), HttpStatus.OK);
        }
        else
        {
            resp = new ResponseEntity<>(new SimpleResponse("No transaction with id: "+trId+" found"), HttpStatus.NOT_FOUND);
        }
        return resp;
    }

    @GetMapping(value = "/user/userlist")
    public ResponseEntity<?> userList() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

}
