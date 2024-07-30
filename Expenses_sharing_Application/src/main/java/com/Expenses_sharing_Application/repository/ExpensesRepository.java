package com.Expenses_sharing_Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Expenses_sharing_Application.model.Expenses;

public interface ExpensesRepository extends JpaRepository<Expenses, Long>{

}
