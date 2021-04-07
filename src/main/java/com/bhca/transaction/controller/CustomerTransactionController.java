package com.bhca.transaction.controller;

import com.bhca.transaction.service.CustomerTransactionService;
import lombok.AllArgsConstructor;
import org.openapitools.client.model.TransactionItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RequestMapping("/api/v1/transaction")
@RestController
public class CustomerTransactionController {

    private final Logger logger = LoggerFactory.getLogger(CustomerTransactionController.class);

    private final CustomerTransactionService service;

    @PostMapping("/create")
    public void create() {
        logger.debug("Transaction creation is called");
    }

    @GetMapping("/{account}")
    public List<TransactionItem> list(@PathVariable UUID account) {
        logger.debug("Transaction list is called for account " + account);
        return service.getTransfers(account);
    }
}
