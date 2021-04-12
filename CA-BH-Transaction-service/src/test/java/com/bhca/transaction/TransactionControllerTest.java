package com.bhca.transaction;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.openapitools.client.model.AccountsTransactionRequest;
import org.openapitools.client.model.CreateTransactionRequest;
import org.openapitools.client.model.TransactionItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static class Api {

        private static final String CREATE = "/api/v1/transaction";
        private static final String ACCOUNT_TRANSACTIONS = "/api/v1/transaction/list";
    }

    @Test
    public void testTransactionCreate() throws Exception {
        List<UUID> accountTransactions = Arrays.asList(UUID.randomUUID(), UUID.randomUUID());
        BigDecimal amountFirst = BigDecimal.ONE;
        BigDecimal amountSecond = BigDecimal.TEN;
        this.mockMvc.perform(post(Api.CREATE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        new CreateTransactionRequest().account(accountTransactions.get(0)).amount(amountFirst)))
        ).andExpect(status().isOk());
        Thread.sleep(1);
        this.mockMvc.perform(post(Api.CREATE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        new CreateTransactionRequest().account(accountTransactions.get(1)).amount(amountSecond)))
        ).andExpect(status().isOk());

        List<TransactionItem> result = objectMapper.readValue(this.mockMvc.perform(post(Api.ACCOUNT_TRANSACTIONS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new AccountsTransactionRequest().accountIds(accountTransactions)))
                ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(),
                new TypeReference<List<TransactionItem>>() {
                }).stream()
                .sorted(Comparator.comparing(TransactionItem::getCreatedAt)).collect(Collectors.toList());

        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getAmount().compareTo(amountFirst), 0);
        assertEquals(result.get(1).getAmount().compareTo(amountSecond), 0);
        assertEquals(result.get(0).getAccount(), accountTransactions.get(0));
        assertEquals(result.get(1).getAccount(), accountTransactions.get(1));
        assertNotNull(result.get(1).getCreatedAt());
        assertNotNull(result.get(1).getUpdatedAt());
    }

    @Test
    public void testZeroAmount() throws Exception {
        this.mockMvc.perform(post(Api.CREATE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        new CreateTransactionRequest().account(UUID.randomUUID()).amount(BigDecimal.ZERO)))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void testNegativeAmount() throws Exception {
        this.mockMvc.perform(post(Api.CREATE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        new CreateTransactionRequest().account(UUID.randomUUID()).amount(new BigDecimal(-10))))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void testUnknownAccountTransactions() throws Exception {
        List<TransactionItem> result = objectMapper.readValue(this.mockMvc.perform(post(Api.ACCOUNT_TRANSACTIONS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new AccountsTransactionRequest().accountIds(Collections.singletonList(UUID.randomUUID()))))
                ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(),
                new TypeReference<List<TransactionItem>>() {});
        assertTrue(result.isEmpty());
    }
}
