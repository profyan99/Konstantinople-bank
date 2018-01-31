package main.controller;

import main.entity.Transaction;
import main.service.TransactionService;
import main.service.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller, which handle queries of {@link Transaction}
 *
 * @author Konstantin Artushkevich
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    private final TransactionService service;

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
