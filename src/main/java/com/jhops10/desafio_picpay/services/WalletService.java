package com.jhops10.desafio_picpay.services;

import com.jhops10.desafio_picpay.entities.Wallet;
import com.jhops10.desafio_picpay.repositories.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public void save(Wallet wallet) {
        walletRepository.save(wallet);
    }
}
