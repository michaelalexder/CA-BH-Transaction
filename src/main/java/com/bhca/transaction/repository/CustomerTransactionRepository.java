package com.bhca.transaction.repository;

import com.bhca.transaction.db.CustomerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, UUID> {
}
