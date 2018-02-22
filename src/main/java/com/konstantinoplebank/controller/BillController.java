package com.konstantinoplebank.controller;


import com.konstantinoplebank.entity.Bill;
import com.konstantinoplebank.response.UserBill;
import com.konstantinoplebank.service.BillService;
import com.konstantinoplebank.utils.exception.BillNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller, which handle queries of {@link Bill} only for {@link com.konstantinoplebank.entity.Role} ADMIN
 *
 * @author Konstantin Artushkevich
 * @version 1.0
 */

@Controller
@RequestMapping(value = "/bill")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class BillController {

    private final BillService billService;

    @Autowired
    public BillController(BillService service) {
        this.billService = service;
    }

    @GetMapping
    public ResponseEntity<?> allBills() {
        return new ResponseEntity<>(billService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> billById(
            @RequestParam(required = false, defaultValue = "", value = "id") long billId) {
        Bill bill = billService.findById(billId).orElseThrow(BillNotFoundException::new);
        return new ResponseEntity<>(new UserBill(bill.getId(),bill.getTransactions(), bill.getAmount()), HttpStatus.OK);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<?> billByUserId(
            @RequestParam(required = false, defaultValue = "", value = "userId") long userId) {
        return new ResponseEntity<>(billService.findByUserId(userId), HttpStatus.OK);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<?> billByUserName(
            @RequestParam(required = false, defaultValue = "", value = "userName") String name) {
        return new ResponseEntity<>(billService.findByUserName(name), HttpStatus.OK);
    }

}
