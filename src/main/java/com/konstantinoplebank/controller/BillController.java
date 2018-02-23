package com.konstantinoplebank.controller;


import com.konstantinoplebank.entity.Bill;
import com.konstantinoplebank.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

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
        ResponseEntity resp = ResponseEntity.noContent().build();
        List<Bill> bills = billService.findAll();
            if(!bills.isEmpty()) {
            resp = ResponseEntity.ok(bills);
        }
        return resp;
    }

    @GetMapping("/search")
    public ResponseEntity<?> billById(
            @RequestParam(required = false, defaultValue = "", value = "id") long billId) {
        ResponseEntity resp = ResponseEntity.notFound().build();
        Optional<Bill> bill = billService.findById(billId, 0);
        if(bill.isPresent()) {
            resp = ResponseEntity.ok(bill.get());
        }
        return resp;
    }

    @GetMapping(path = "/search")
    public ResponseEntity<?> billByUserId(
            @RequestParam(required = false, defaultValue = "", value = "userId") long userId) {
        ResponseEntity resp = ResponseEntity.noContent().build();
        List<Bill> bills = billService.findByUserId(userId);
        if(!bills.isEmpty()) {
            resp = ResponseEntity.ok(bills);
        }
        return resp;
    }

    @GetMapping(path = "/search")
    public ResponseEntity<?> billByUserName(
            @RequestParam(required = false, defaultValue = "", value = "userName") String name) {
        ResponseEntity resp = ResponseEntity.noContent().build();
        List<Bill> bills = billService.findByUserName(name);
        if(!bills.isEmpty()) {
            resp = ResponseEntity.ok(bills);
        }
        return resp;
    }

}
