package com.Expenses_sharing_Application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Expenses_sharing_Application.model.Expenses;
import com.Expenses_sharing_Application.service.ExpensesService;

@RestController
public class ExpensesController {

	@Autowired
    private ExpensesService expenseService;

    @GetMapping("/getallexpenses")
    public List<Expenses> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/getexpensesbyid/{id}")
    public Expenses getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id);
    }

    @PostMapping("/saveexpense")
    public Expenses createExpense(@RequestBody Expenses expenseRequest) {
        return expenseService.createExpense(
                expenseRequest.getDescription(),
                expenseRequest.getAmount(),
                expenseRequest.getSplitMethod(),
                expenseRequest.getUserIds(),
                expenseRequest.getExactAmounts(),
                expenseRequest.getPercentages()
                );
    }

    @DeleteMapping("/deleteexpensesbyid/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }
}
