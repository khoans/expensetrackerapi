package com.nskhoa.expensetrackerapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nskhoa.expensetrackerapi.entity.Expense;
import com.nskhoa.expensetrackerapi.service.ExpenseService;

@RestController
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @GetMapping(path = "/expenses")
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }
}
