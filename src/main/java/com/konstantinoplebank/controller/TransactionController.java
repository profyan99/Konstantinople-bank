package com.konstantinoplebank.controller;

import com.konstantinoplebank.entity.Transaction;
import com.konstantinoplebank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller, which handle queries of {@link Transaction} only for {@link com.konstantinoplebank.entity.Role} ADMIN
 *
 * @author Konstantin Artushkevich
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/transactions")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService service) {
        this.transactionService = service;
    }

    @GetMapping
    public ResponseEntity<?> allTransactions() {
        return new ResponseEntity<>(transactionService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> transactionById(
            @RequestParam(required = false, defaultValue = "", value = "id") long trId) {
        return new ResponseEntity<>(transactionService.findById(trId).orElse(null), HttpStatus.OK);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<?> transactionsByUserId(
            @RequestParam(required = false, defaultValue = "", value = "userId") long userId) {
        return new ResponseEntity<>(transactionService.findByUserId(userId), HttpStatus.OK);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<?> transactionsByUserName(
            @RequestParam(required = false, defaultValue = "", value = "userName") String name) {
        return new ResponseEntity<>(transactionService.findByUserName(name), HttpStatus.OK);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<?> transactionsByBillId(
            @RequestParam(required = false, defaultValue = "", value = "billId") long billId) {
        return new ResponseEntity<>(transactionService.findByBillId(billId), HttpStatus.OK);
    }



}
