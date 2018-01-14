package main.controller;

import main.entity.Transaction;
import main.service.TransactionServiceImpl;
import org.omg.IOP.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    private final TransactionServiceImpl service;

    @Autowired
    public TransactionController(TransactionServiceImpl service) {
        this.service = service;
    }


    @GetMapping(path = "/all")
    public @ResponseBody List<Transaction> allTransactions() {
        return service.getAllTransactions();
    }

    @GetMapping(path = "/{trId}")
    public @ResponseBody Transaction transactionInformation(@PathVariable("trId") long id) {
        return service.getTransactionById(id);
    }

    @GetMapping(path = "/{userName}")
    public @ResponseBody List<Transaction> userTransactions(@PathVariable("userName") String name) {
        return service.getAllTransactionsByUserName(name);
    }

}
