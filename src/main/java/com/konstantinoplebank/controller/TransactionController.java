package com.konstantinoplebank.controller;

import com.konstantinoplebank.entity.Transaction;
import com.konstantinoplebank.request.CreateTransaction;
import com.konstantinoplebank.response.SimpleResponse;
import com.konstantinoplebank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Controller, which handle queries of {@link Transaction}
 *
 * @author Konstantin Artushkevich
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService service) {
        this.transactionService = service;
    }

    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody CreateTransaction transaction, Principal principal) {
        ResponseEntity resp;

        // checking, that user create bill in his account
        if(principal.getName().equals(transaction.getUserName())) {
            transactionService.create(
                    transaction.getUserid(),
                    transaction.getBillid(),
                    transaction.getAmount(),
                    transaction.getDescription()
            );
            resp = new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            resp = new ResponseEntity<>(
                    new SimpleResponse("You don't have permissions to create transaction in foreign account"), HttpStatus.FORBIDDEN);
        }
        return resp;
    }

    @GetMapping(path = "/{trId}")
    public ResponseEntity<?> transactionInformation(@PathVariable("trId") long id) {
        return new ResponseEntity<>(transactionService.findById(id), null, HttpStatus.OK);
    }

    @GetMapping(path = "/{userName}")
    public ResponseEntity<?> userTransactions(@PathVariable("userName") String name) {
        return new ResponseEntity<>(transactionService.findByUserName(name), null, HttpStatus.OK);
    }

}
