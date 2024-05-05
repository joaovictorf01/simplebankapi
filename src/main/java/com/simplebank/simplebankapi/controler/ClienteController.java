package com.simplebank.simplebankapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.persistence.EntityNotFoundException;

import com.simplebank.simplebankapi.model.Customer;
import com.simplebank.simplebankapi.model.Transaction;
import com.simplebank.simplebankapi.service.ClienteService;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable Long id) {
        try {
            Customer customer = clienteService.getCustomerDetails(id);
            return ResponseEntity.ok(customer);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/transacoes")
    public ResponseEntity<?> getCustomerTransactions(@PathVariable Long id) {
        try {
            List<Transaction> transactions = clienteService.getCustomerTransactions(id);
            return ResponseEntity.ok(transactions);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/transacoes")
    public ResponseEntity<?> addTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        try {
            transaction.setCustomer(new Customer(id)); // Configuramos o cliente apenas com o ID
            Transaction createdTransaction = clienteService.saveTransaction(transaction);
            return ResponseEntity.ok(createdTransaction);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error processing transaction: " + e.getMessage());
        }
    }
}
