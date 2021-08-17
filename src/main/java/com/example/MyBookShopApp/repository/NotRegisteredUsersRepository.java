package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.user.NotRegisteredUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotRegisteredUsersRepository extends JpaRepository<NotRegisteredUsersEntity, Integer> {
}