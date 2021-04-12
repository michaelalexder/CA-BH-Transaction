package com.bhca.transaction.controller;

import com.bhca.transaction.service.CustomerTopUpTransactionService;
import lombok.AllArgsConstructor;
import org.openapitools.client.model.AccountsTransactionRequest;
import org.openapitools.client.model.CreateTransactionRequest;
import org.openapitools.client.model.TransactionItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/v1/transaction")
@RestController
public class CustomerTopUpTransactionController {

    private final Logger logger = LoggerFactory.getLogger(CustomerTopUpTransactionController.class);

    private final CustomerTopUpTransactionService service;

    @PostMapping
    public void create(@RequestBody CreateTransactionRequest request) {
        logger.debug("Transaction creation is called for account {}", request.getAccount());
        service.createTransfer(request.getAccount(), request.getAmount());
        logger.debug("Transaction created");
    }

    @PostMapping("/list")
    public List<TransactionItem> list(@RequestBody AccountsTransactionRequest request) {
        logger.debug("Transaction list is called for accounts {}", request.getAccountIds().toString());
        return service.getTransfers(request.getAccountIds());
    }
}
