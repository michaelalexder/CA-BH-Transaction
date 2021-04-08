package com.bhca.transaction.service;

import com.bhca.transaction.db.CustomerTopUpTransaction;
import com.bhca.transaction.repository.CustomerTopUpTransactionRepository;
import lombok.AllArgsConstructor;
import org.openapitools.client.model.TransactionItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CustomerTopUpTransactionService {

    private final Logger logger = LoggerFactory.getLogger(CustomerTopUpTransactionService.class);

    private final CustomerTopUpTransactionRepository repository;

    @Transactional
    public void createTransfer(UUID customer, UUID account, BigDecimal amount) {
        logger.info(MessageFormat.format("Creating transfer of {0} for customer {1} and account {2}",
                amount, customer, account));
        CustomerTopUpTransaction transaction = new CustomerTopUpTransaction();
        transaction.setCustomer(account);
        transaction.setAccount(account);
        transaction.setAmount(amount);
        repository.save(transaction);
    }

    @Transactional(readOnly = true)
    public List<TransactionItem> getTransfers(UUID account) {
        return new ArrayList<>();
    }
}
