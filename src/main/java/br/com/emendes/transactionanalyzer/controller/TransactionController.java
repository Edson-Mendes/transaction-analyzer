package br.com.emendes.transactionanalyzer.controller;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.emendes.transactionanalyzer.service.TransactionService;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

  @Autowired
  private TransactionService transactionService;

  @GetMapping
  public String transactionForm() {
    return "transactionForm.html";
  }

  @PostMapping
  @Transactional
  public String submitForm(@RequestParam("file") MultipartFile file) throws IOException {

    transactionService.saveAll(file);

    return "redirect:/home";
  }

}
