package com.nskhoa.expensetrackerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nskhoa.expensetrackerapi.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
