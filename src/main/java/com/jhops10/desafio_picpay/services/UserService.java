package com.jhops10.desafio_picpay.services;

import com.jhops10.desafio_picpay.entities.User;
import com.jhops10.desafio_picpay.exceptions.UserNotFoundException;
import com.jhops10.desafio_picpay.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException("Usuário não encontrado."));
    }
}
