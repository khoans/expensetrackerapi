package com.nskhoa.expensetrackerapi.service;

import java.util.List;

import com.nskhoa.expensetrackerapi.entity.Expense;

public interface ExpenseService {

    List<Expense> getAllExpenses();
}
