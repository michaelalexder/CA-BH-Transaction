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
public class CustomerTransaction extends AbstractBaseEntity {

    @Column(nullable = false)
    private UUID fromAccount;

    @Column(nullable = false)
    private UUID toAccount;

    @Column(nullable = false)
    private BigDecimal amount;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
