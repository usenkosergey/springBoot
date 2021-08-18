package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.user.UsersEntityNR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersNRRepository extends JpaRepository<UsersEntityNR, Integer> {

    Optional<UsersEntityNR> findByCodeUser(Integer codeUser);

}