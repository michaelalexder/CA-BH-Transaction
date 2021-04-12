package com.bhca.transaction.service;

import com.bhca.transaction.db.CustomerTopUpTransaction;
import com.bhca.transaction.repository.CustomerTopUpTransactionRepository;
import lombok.AllArgsConstructor;
import org.openapitools.client.model.TransactionItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CustomerTopUpTransactionService {

    private final Logger logger = LoggerFactory.getLogger(CustomerTopUpTransactionService.class);

    private final CustomerTopUpTransactionRepository repository;

    @Transactional
    public void createTransfer(UUID account, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        logger.info(MessageFormat.format("Creating transfer of {0} for account {1}",
                amount, account));
        CustomerTopUpTransaction transaction = new CustomerTopUpTransaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        repository.save(transaction);
    }

    @Transactional(readOnly = true)
    public List<TransactionItem> getTransfers(List<UUID> accountIds) {
        return repository.findByAccountIn(accountIds, Sort.by(Sort.Direction.DESC, "createdAt")).stream().map(
                (transaction) ->
                    new TransactionItem().amount(transaction.getAmount())
                            .account(transaction.getAccount())
                            .createdAt(transaction.getCreatedAt().atOffset(ZoneOffset.UTC))
                            .updatedAt(transaction.getUpdatedAt().atOffset(ZoneOffset.UTC))
                ).collect(Collectors.toList());
    }
}
