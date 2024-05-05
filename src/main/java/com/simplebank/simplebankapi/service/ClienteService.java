package com.simplebank.simplebankapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import com.simplebank.simplebankapi.model.Customer;
import com.simplebank.simplebankapi.model.Transaction;
import com.simplebank.simplebankapi.repository.CustomerRepository;
import com.simplebank.simplebankapi.repository.TransactionRepository;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Customer getCustomerDetails(Long id) {
        return customerRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + id));
    }

    public List<Transaction> getCustomerTransactions(Long customerId) {
        return transactionRepository.findByCustomerId(customerId);
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
