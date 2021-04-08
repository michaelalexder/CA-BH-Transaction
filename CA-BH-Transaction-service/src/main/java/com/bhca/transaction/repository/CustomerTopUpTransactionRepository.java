package com.bhca.transaction.repository;

import com.bhca.transaction.db.CustomerTopUpTransaction;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerTopUpTransactionRepository extends JpaRepository<CustomerTopUpTransaction, UUID> {

    List<CustomerTopUpTransaction> findByCustomer(UUID customer, Sort sort);
}
