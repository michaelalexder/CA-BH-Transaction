package com.bhca.transaction.controller;

import com.bhca.transaction.service.CustomerTransactionService;
import lombok.AllArgsConstructor;
import org.openapitools.client.model.TransactionItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController("/api/v1/transaction")

public class CustomerTransactionController {

    private final Logger logger = LoggerFactory.getLogger(CustomerTransactionService.class);

    private final CustomerTransactionService service;

    @PostMapping("/create")
    public void create() {
        logger.debug("Transaction creation is called");
    }

    @GetMapping
    public List<TransactionItem> list(UUID account) {
        logger.debug("Transaction list is called for account " + account);
        return service.getTransfers(account);
    }
}
