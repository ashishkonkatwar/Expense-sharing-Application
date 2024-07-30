package com.Expenses_sharing_Application.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Expenses_sharing_Application.model.Expenses;
import com.Expenses_sharing_Application.repository.ExpensesRepository;

@Service
public class ExpensesService {

	@Autowired
    private ExpensesRepository expenseRepository;

    public List<Expenses> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expenses getExpenseById(Long id) {
        return expenseRepository.findById(id).orElse(null);
    }

    public Expenses saveExpense(Expenses expense) {
        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public Expenses createExpense(String description, BigDecimal amount, String splitMethod, List<Long> userIds, Map<Long, BigDecimal> exactAmounts, Map<Long, BigDecimal> percentages) {
    	Expenses expense = new Expenses();
        expense.setDescription(description);
        expense.setAmount(amount);
        expense.setSplitMethod(splitMethod);

        Map<Long, BigDecimal> userShares;

        switch (splitMethod) {
            case "EQUAL":
                BigDecimal equalShare = amount.divide(BigDecimal.valueOf(userIds.size()));
                userShares = userIds.stream().collect(Collectors.toMap(id -> id, id -> equalShare));
                break;
            case "EXACT":
                userShares = exactAmounts;
                break;
            case "PERCENTAGE":
                userShares = percentages.entrySet().stream().collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> amount.multiply(entry.getValue()).divide(BigDecimal.valueOf(100))
                ));
                break;
            default:
                throw new IllegalArgumentException("Invalid split method");
        }

        expense.setUserShares(userShares);
        return expenseRepository.save(expense);
    }
}
