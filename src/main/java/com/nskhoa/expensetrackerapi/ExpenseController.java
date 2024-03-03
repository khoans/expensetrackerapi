package com.nskhoa.expensetrackerapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseController {

    @GetMapping(path = "/expenses")
    public String getAllExpenses() {
        return "List of expenses";
    }
}
