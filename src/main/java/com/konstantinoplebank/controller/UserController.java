package com.konstantinoplebank.controller;

import com.konstantinoplebank.entity.Bill;
import com.konstantinoplebank.entity.Transaction;
import com.konstantinoplebank.entity.User;
import com.konstantinoplebank.request.CreateBill;
import com.konstantinoplebank.request.CreateTransaction;
import com.konstantinoplebank.response.UserBill;
import com.konstantinoplebank.response.UserProfile;
import com.konstantinoplebank.response.UserTransaction;
import com.konstantinoplebank.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
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
            UserProfile userProfile = userService.create(user);
            if(userProfile == null) {
                resp = ResponseEntity.noContent().build();
            }
            else {
                URI location = ServletUriComponentsBuilder.fromPath("/user").path(
                        "/{id}").buildAndExpand(userProfile.getId()).toUri();
                resp = ResponseEntity.created(location).build();
            }
        }
        else {
            resp = ResponseEntity.badRequest().body("Already registered");
        }
        return resp;
    }

    @GetMapping(value = "/user/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #userId == @userService.getByName(principal.getUsername()).get().getName()")
    public ResponseEntity<?> userProfile(@PathVariable("id") long userId) {
        ResponseEntity resp = ResponseEntity.notFound().build();
        Optional<UserProfile> user =  userService.getUserProfile(userId);
        if(user.isPresent()) {
            resp = ResponseEntity.ok(user.get());
        }
        return resp;
    }

    @GetMapping(value = "/user/{id}/bills")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #userId == @userService.getByName(principal.getUsername()).get().getName()")
    public ResponseEntity<?> allUserBills(@PathVariable("id") long userId) {
        ResponseEntity resp = ResponseEntity.noContent().build();
        Set<Bill> bills = userService.getUserBills(userId);
        if(!bills.isEmpty()) {
            resp = ResponseEntity.ok(bills);
        }
        return resp;
    }

    @PostMapping(value = "/user/{id}/bills", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN') or #userId == @userService.getByName(principal.getUsername()).get().getName()")
    public ResponseEntity<?> createUserBill(@PathVariable("userId") long userId, @RequestBody CreateBill bill) {
        UserBill userBill = userService.createBill(bill.getUserid(), bill.getAmount());
        ResponseEntity resp  = ResponseEntity.badRequest().body("Error with creating bill");
        if(userBill.getId() != 0) {
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path(
                    "/{bId}").buildAndExpand(userBill.getId()).toUri();
            resp = ResponseEntity.created(location).build();
        }
        return resp;
    }

    @GetMapping(value = "/user/{id}/bills/{billId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #userId == @userService.getByName(principal.getUsername()).get().getName()")
    public ResponseEntity<?> userBill(@PathVariable("id") long userId, @PathVariable("billId") long billId) {
        ResponseEntity resp = ResponseEntity.notFound().build();
        Optional<Bill> bill = userService.getBill(billId, userId);
        if(bill.isPresent()) {
            resp = ResponseEntity.ok(bill.get());
        }
        return resp;
    }


    @GetMapping(value = "/user/{id}/bills/{billId}/transactions")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #userId == @userService.getByName(principal.getUsername()).get().getName()")
    public ResponseEntity<?> userAllTransactions(@PathVariable("id") long userId) {
        ResponseEntity resp = ResponseEntity.noContent().build();
        Set<Transaction> transactions = userService.getUserTransactions(userId);
        if(!transactions.isEmpty()) {
            resp = ResponseEntity.ok(transactions);
        }
        return resp;
    }

    @PostMapping(value = "/user/{id}/bills/{billId}/transactions", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN') or #userId == @userService.getByName(principal.getUsername()).get().getName()")
    public ResponseEntity<?> createUserTransaction(@PathVariable("id") long userId, @RequestBody CreateTransaction transaction) {
        ResponseEntity resp = ResponseEntity.badRequest().body("Error with creating transaction");
        UserTransaction userTransaction = userService.createTransaction(
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getBillid(),
                userId
        );
        if(userTransaction.getId() != 0) {
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path(
                    "/{trId}").buildAndExpand(userTransaction.getId()).toUri();
            resp = ResponseEntity.created(location).build();
        }
        return resp;
    }

    @GetMapping(value = "/user/{id}/bills/{billId}/transactions/{trId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #userId == @userService.getByName(principal.getUsername()).get().getName()")
    public ResponseEntity<?> userTransaction(@PathVariable("id") long userId, @PathVariable("billId") long billId, @PathVariable("trId") long trId) {
        ResponseEntity resp = ResponseEntity.notFound().build();
        Optional<Transaction> transaction = userService.getTransaction(trId, billId, userId);
        if(transaction.isPresent()) {
            resp = ResponseEntity.ok(transaction);
        }
        return resp;
    }

    @GetMapping(value = "/user/userlist")
    public ResponseEntity<?> userList() {
        ResponseEntity resp = ResponseEntity.noContent().build();
        List<UserProfile> userProfileList = userService.findAll();
        if(!userProfileList.isEmpty()) {
            resp = ResponseEntity.ok(userProfileList);
        }
        return resp;
    }

}
