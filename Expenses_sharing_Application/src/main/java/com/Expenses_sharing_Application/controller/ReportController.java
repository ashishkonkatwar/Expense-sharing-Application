package com.Expenses_sharing_Application.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Expenses_sharing_Application.model.Expenses;
import com.Expenses_sharing_Application.service.ExpensesService;

@RestController
public class ReportController {
	
	@Autowired
    private ExpensesService expenseService;

    @GetMapping("/balance-sheet")
    public ResponseEntity<InputStreamResource> getBalanceSheet() throws IOException {
        List<Expenses> expenses = expenseService.getAllExpenses();
        ByteArrayInputStream bais = generateCsvReport(expenses);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=balance-sheet.csv");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(new InputStreamResource(bais));
    }

    private ByteArrayInputStream generateCsvReport(List<Expenses> expenses) throws IOException {
        ByteArrayInputStream bais;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             PrintWriter writer = new PrintWriter(baos)) {

            writer.println("Id,Description,Amount,Split Method");

            for (Expenses expense : expenses) {
                writer.printf("%d,%s,%s,%s%n",
                        expense.getId(),
                        expense.getDescription(),
                        expense.getAmount(),
                        expense.getSplitMethod());
            }
            writer.flush();
            bais = new ByteArrayInputStream(baos.toByteArray());
        }
        return bais;
    }
}


