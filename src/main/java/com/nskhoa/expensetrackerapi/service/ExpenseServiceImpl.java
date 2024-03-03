package com.nskhoa.expensetrackerapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nskhoa.expensetrackerapi.entity.Expense;
import com.nskhoa.expensetrackerapi.repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepo;

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepo.findAll();
    }
}
