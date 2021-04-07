package com.bhca.transaction.service;

import com.bhca.transaction.db.CustomerTransaction;
import com.bhca.transaction.repository.CustomerTransactionRepository;
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
public class CustomerTransactionService {

    private final Logger logger = LoggerFactory.getLogger(CustomerTransactionService.class);

    private final CustomerTransactionRepository repository;

    @Transactional
    public void createTransfer(UUID fromAccount, UUID toAccount, BigDecimal amount) {
        logger.info(MessageFormat.format("Creating transfer of {0} from {1} to {2} ",
                amount, fromAccount, toAccount));
        CustomerTransaction transaction = new CustomerTransaction();
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transaction.setAmount(amount);
        repository.save(transaction);
    }

    @Transactional(readOnly = true)
    public List<TransactionItem> getTransfers(UUID account) {
        return new ArrayList<>();
    }
}
