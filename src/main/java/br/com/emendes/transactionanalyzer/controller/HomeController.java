package br.com.emendes.transactionanalyzer.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.emendes.transactionanalyzer.model.TransactionsImport;
import br.com.emendes.transactionanalyzer.service.TransactionImportService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

  private final TransactionImportService transactionImportService;

  @GetMapping
  public ModelAndView home() {
    ModelAndView modelAndView = new ModelAndView("home.html");

    // TODO: Converter para um dto para enviar para /home
    List<TransactionsImport> transactionsImport = transactionImportService.read();
    modelAndView.addObject("transactionsImport", transactionsImport);

    return modelAndView;
  }

}
