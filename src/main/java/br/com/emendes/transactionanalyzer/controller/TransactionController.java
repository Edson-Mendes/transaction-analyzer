package br.com.emendes.transactionanalyzer.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.emendes.transactionanalyzer.model.Message;
import br.com.emendes.transactionanalyzer.model.TransactionsImport;
import br.com.emendes.transactionanalyzer.model.AlertType;
import br.com.emendes.transactionanalyzer.service.TransactionsImportService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

  private final TransactionsImportService transactionsImportService;

  @GetMapping
  public ModelAndView home() {
    ModelAndView modelAndView = new ModelAndView("screen/transactions.html");

    // TODO: Converter para um dto para enviar para /home
    List<TransactionsImport> transactionsImport = transactionsImportService.findAll();
    modelAndView.addObject("transactionsImport", transactionsImport);

    return modelAndView;
  }

  @PostMapping
  @Transactional
  public String submitForm(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
    transactionsImportService.processImport(file);

    final Message message = Message.builder()
        .type(AlertType.SUCCESS)
        .message("Arquivo processado com sucesso")
        .build();
    attributes.addFlashAttribute("message", message);
    return "redirect:/transactions";
  }

}
