package com.konstantinoplebank.controller;


import com.konstantinoplebank.entity.Bill;
import com.konstantinoplebank.entity.User;
import com.konstantinoplebank.request.CreateBill;
import com.konstantinoplebank.response.SimpleResponse;
import com.konstantinoplebank.response.UserBill;
import com.konstantinoplebank.service.BillService;
import com.konstantinoplebank.service.UserService;
import com.konstantinoplebank.utils.exception.BillNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/bill")
public class BillController {

    private final BillService billService;

    @Autowired
    public BillController(BillService service) {
        this.billService = service;
    }


    @PostMapping
    public ResponseEntity<?> createBill(@RequestBody CreateBill bill, Principal principal) {
        ResponseEntity resp;

        // checking, that user create bill in his account
        if(principal.getName().equals(bill.getUserName())) {
            billService.create(bill.getUserid(), bill.getAmount());
            resp = new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            resp = new ResponseEntity<>(
                    new SimpleResponse("You don't have permissions to create bill in foreign account"),
                    HttpStatus.FORBIDDEN
            );
        }
        return resp;
    }

    @GetMapping
    public ResponseEntity<?> getUserBills(Principal principal) {
        List<UserBill> bills = new ArrayList<>();
        billService.findByUserName(principal.getName()).forEach(
                (e) -> bills.add(new UserBill(e.getId(), e.getTransactions(), e.getAmount())));
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    @GetMapping(path = "/{trId}")
    public ResponseEntity<?> getBill(@PathVariable("trId") long id) {
        Bill bill = billService.findById(id).orElseThrow(BillNotFoundException::new);
        return new ResponseEntity<>(new UserBill(bill.getId(),bill.getTransactions(), bill.getAmount()), HttpStatus.OK);
    }

}
