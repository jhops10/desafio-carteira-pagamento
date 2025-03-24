package com.jhops10.desafio_picpay.repositories;

import com.jhops10.desafio_picpay.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
