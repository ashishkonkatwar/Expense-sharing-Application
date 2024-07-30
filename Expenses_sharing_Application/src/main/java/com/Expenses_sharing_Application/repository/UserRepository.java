package com.Expenses_sharing_Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Expenses_sharing_Application.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
