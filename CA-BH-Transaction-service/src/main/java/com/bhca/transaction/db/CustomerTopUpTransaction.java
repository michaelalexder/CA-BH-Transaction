package com.bhca.transaction.db;

import com.bhca.common.AbstractBaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
public class CustomerTopUpTransaction extends AbstractBaseEntity {

    @Column(nullable = false)
    private UUID customer;

    @Column(nullable = false)
    private UUID account;

    @Column(nullable = false)
    private BigDecimal amount;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
