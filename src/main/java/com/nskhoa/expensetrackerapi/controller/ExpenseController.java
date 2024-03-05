package com.nskhoa.expensetrackerapi.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nskhoa.expensetrackerapi.entity.Expense;
import com.nskhoa.expensetrackerapi.service.ExpenseService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @GetMapping(path = "/expenses")
    public List<Expense> getAllExpenses(Pageable page) {
        return expenseService.getAllExpenses(page).toList();
    }

    @GetMapping(path = "/expenses/{id}")
    public Expense getExpensesById(@PathVariable("id") Long id) {
        return expenseService.getExpenseById(id);
    }

    @GetMapping(path = "/expense")
    public String getExpenseById(@RequestParam Long id) {
        return "The expense id is " + id;
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/expenses")
    public void DeleteExpenseById(@RequestParam Long id) {
        expenseService.deleteExpenseById(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "/expenses")
    public Expense saveExpenseDetails(@Valid @RequestBody Expense expense) {
        return expenseService.saveExpenseDetails(expense);
    }

    @PutMapping(path = "/expenses/{id}")
    public Expense updateExpenseDetails(@PathVariable Long id, @RequestBody Expense expense) {
        return expenseService.updateExpenseDetails(id, expense);
    }

    @GetMapping(path = "/expenses/category")
    public List<Expense> getExpensesByCategory(@RequestParam String category, Pageable page) {
        return expenseService.readByCategory(category, page);
    }

    @GetMapping(path = "/expenses/name")
    public List<Expense> getExpensesByName(@RequestParam String name, Pageable page) {
        return expenseService.readByName(name, page);
    }

    @GetMapping(path = "/expenses/date")
    public List<Expense> getAllExpensesByDate(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate, Pageable page) {
        return expenseService.readByDate(startDate, endDate, page);
    }

}
