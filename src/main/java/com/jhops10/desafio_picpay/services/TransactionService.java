package com.jhops10.desafio_picpay.services;

import com.jhops10.desafio_picpay.entities.Transaction;
import com.jhops10.desafio_picpay.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    public final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
