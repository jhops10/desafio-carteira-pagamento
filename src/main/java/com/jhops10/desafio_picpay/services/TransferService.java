package com.jhops10.desafio_picpay.services;

import com.jhops10.desafio_picpay.dtos.TransferDTO;
import com.jhops10.desafio_picpay.entities.Transaction;
import com.jhops10.desafio_picpay.entities.User;
import com.jhops10.desafio_picpay.entities.UserType;
import com.jhops10.desafio_picpay.entities.Wallet;
import com.jhops10.desafio_picpay.exceptions.InsufficientBalanceException;
import com.jhops10.desafio_picpay.exceptions.MerchantTransferException;
import com.jhops10.desafio_picpay.exceptions.NotificationErrorException;
import com.jhops10.desafio_picpay.exceptions.TransferValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

@Service
public class TransferService {

    private final UserService userService;
    private final AuthorizationService authorizationService;
    private final WalletService walletService;
    private final TransactionService transactionService;
    private final NotificationService notificationService;

    public TransferService(UserService userService, AuthorizationService authorizationService, WalletService walletService, TransactionService transactionService, NotificationService notificationService) {
        this.userService = userService;
        this.authorizationService = authorizationService;
        this.walletService = walletService;
        this.transactionService = transactionService;
        this.notificationService = notificationService;
    }

    @Transactional
    public void transferAmount(TransferDTO transferDTO) {
        User payer = userService.findUserById(transferDTO.payer());
        User payee = userService.findUserById(transferDTO.payee());

        checkIfPayerIsMerchant(payer);
        checkPayerSufficientBalance(payer, transferDTO.value());
        validateTransfer();

        payer.getWallet().setBalance(payer.getWallet().getBalance().subtract(transferDTO.value()));
        updateBalance(payer.getWallet());

        payee.getWallet().setBalance(payee.getWallet().getBalance().add(transferDTO.value()));
        updateBalance(payee.getWallet());

        Transaction transaction = new Transaction();
        transaction.setValue(transferDTO.value());
        transaction.setPayer(payer);
        transaction.setPayee(payee);
        transactionService.saveTransaction(transaction);

        sendNotification();

    }


    private void checkIfPayerIsMerchant(User user) {
        if (user.getUserType().equals(UserType.MERCHANT)) {
            throw new MerchantTransferException("Erro na Transferência! Lojistas não podem transferir.");
        }
    }

    private void checkPayerSufficientBalance(User user, BigDecimal value) {
        if (user.getWallet().getBalance().compareTo(value) < 0) {
            throw new InsufficientBalanceException("Saldo insuficiente para realizar a transferência.");
        }
    }

    private void validateTransfer() {
        if (!authorizationService.validateTransfer()) {
            throw new TransferValidationException("Erro na Transferência! Transferência não validada.");
        }
    }

    private void updateBalance(Wallet wallet) {
        walletService.save(wallet);
    }

    private void sendNotification() {
        try {
            notificationService.sendNotification();
        } catch (HttpClientErrorException e) {
            throw new NotificationErrorException("Erro ao enviar notificaçao.");
        }
    }
}
