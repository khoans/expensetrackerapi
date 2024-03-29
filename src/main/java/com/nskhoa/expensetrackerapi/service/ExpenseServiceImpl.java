package com.nskhoa.expensetrackerapi.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nskhoa.expensetrackerapi.entity.Expense;
import com.nskhoa.expensetrackerapi.exceptions.ResourceNotFoundException;
import com.nskhoa.expensetrackerapi.repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepo;

    @Override
    public Page<Expense> getAllExpenses(Pageable page) {
        if (page == null) {
            page = Pageable.unpaged();
        }
        return expenseRepo.findAll(page);
    }

    @Override
    public Expense getExpenseById(Long id) {
        if (id == null) {
            throw new ResourceNotFoundException("Expense id can not be null");
        }
        Optional<Expense> expense = expenseRepo.findById(id);
        if (expense.isPresent()) {
            return expense.get();
        }
        throw new ResourceNotFoundException("Expense is not found for the id " + id);
    }

    @Override
    public void deleteExpenseById(Long id) {
        Expense expense = getExpenseById(id);
        if (expense != null) {
            expenseRepo.delete(expense);
        }

    }

    @Override
    public Expense saveExpenseDetails(Expense expense) {
        if (expense != null) {
            return expenseRepo.save(expense);
        }
        return null;

    }

    @Override
    public Expense updateExpenseDetails(Long id, Expense expense) {
        Expense existingExpense = getExpenseById(id);
        existingExpense.setName(expense.getName() != null ? expense.getName() : existingExpense.getName());
        existingExpense.setDescription(
                expense.getDescription() != null ? expense.getDescription() : existingExpense.getDescription());
        existingExpense
                .setDate(expense.getDate() != null ? expense.getDate() : existingExpense.getDate());
        existingExpense
                .setAmount(expense.getAmount() != null ? expense.getAmount() : existingExpense.getAmount());
        existingExpense
                .setCategory(expense.getCategory() != null ? expense.getCategory() : existingExpense.getCategory());

        return expenseRepo.save(existingExpense);
    }

    @Override
    public List<Expense> readByCategory(String category, Pageable page) {
        return expenseRepo.findByCategory(category, page).toList();
    }

    @Override
    public List<Expense> readByName(String name, Pageable page) {
        return expenseRepo.findByNameContaining(name, page).toList();
    }

    @Override
    public List<Expense> readByDate(Date startDate, Date endDate, Pageable page) {
        if (startDate == null) {
            startDate = new Date(0);

        }
        if (endDate == null) {
            endDate = new Date(System.currentTimeMillis());
        }

        Page<Expense> pages = expenseRepo.findByDateBetween(startDate, endDate, page);
        return pages.toList();
    }
}
