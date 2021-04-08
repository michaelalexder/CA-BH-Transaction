package com.bhca.transaction.controller;

import com.bhca.transaction.service.CustomerTopUpTransactionService;
import lombok.AllArgsConstructor;
import org.openapitools.client.model.CreateTransactionRequest;
import org.openapitools.client.model.TransactionItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RequestMapping("/api/v1/transaction")
@RestController
public class CustomerTopUpTransactionController {

    private final Logger logger = LoggerFactory.getLogger(CustomerTopUpTransactionController.class);

    private final CustomerTopUpTransactionService service;

    @PostMapping("/create")
    public void create(@RequestBody CreateTransactionRequest request) {
        logger.debug("Transaction creation is called for customer " + request.getCustomer());
        service.createTransfer(request.getCustomer(), request.getAccount(), request.getAmount());
        logger.debug("Transaction created");
    }

    @GetMapping("/{customer}")
    public List<TransactionItem> list(@PathVariable UUID customer) {
        logger.debug("Transaction list is called for account " + customer);
        return service.getTransfers(customer);
    }
}
