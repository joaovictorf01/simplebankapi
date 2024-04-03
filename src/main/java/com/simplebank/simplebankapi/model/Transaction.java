package com.simplebank.simplebankapi.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "transacoes")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Customer customer;

    @Column(name = "valor")
    private long value;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TransactionType type;

    @Column(name = "descricao", length = 10)
    private String description;

    @Column(name = "realizada_em")
    private LocalDateTime performedAt;
}
