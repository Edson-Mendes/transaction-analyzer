package br.com.emendes.transactionanalyzer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.emendes.transactionanalyzer.controller.dto.SuspiciousAccountDto;
import br.com.emendes.transactionanalyzer.controller.dto.SuspiciousBranchDto;
import br.com.emendes.transactionanalyzer.controller.dto.TransactionDto;
import br.com.emendes.transactionanalyzer.controller.form.AnalysisDateForm;
import br.com.emendes.transactionanalyzer.model.util.AlertType;
import br.com.emendes.transactionanalyzer.model.util.Message;
import br.com.emendes.transactionanalyzer.service.AnalyzeService;
import br.com.emendes.transactionanalyzer.service.TransactionService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/analysis")
@RequiredArgsConstructor
public class AnalyzeController {

  private final AnalyzeService analyzeService;
  private final TransactionService transactionService;

  @GetMapping
  public String analyzePage(AnalysisDateForm analysisDateForm) {
    return "page/analysisPage";
  }

  @GetMapping("/analyzing")
  public String analyze(@Valid AnalysisDateForm analysisDateForm, BindingResult bindingResult,
      RedirectAttributes attributes) {
    // TODO: Refatorar esse método.
    attributes.addFlashAttribute("analysisDateForm", analysisDateForm);

    if (bindingResult.hasErrors()) {
      attributes.addFlashAttribute("message", new Message(AlertType.ERROR, "Invalid fields"));
      return "redirect:/analysis";
    }
    if (!transactionService.existsByMonthAndYear(analysisDateForm.getMonthAsInteger(),
        analysisDateForm.getYearAsInteger())) {
      attributes.addFlashAttribute("message",
          new Message(AlertType.WARNING, "This month hasn't transactions!"));
      return "redirect:/analysis";
    }
    // Buscando transações suspeitas
    List<TransactionDto> transactionsDto = analyzeService.findSuspiciousTransactions(
        analysisDateForm.getMonthAsInteger(),
        analysisDateForm.getYearAsInteger());

    // Buscando contas suspeitas
    List<SuspiciousAccountDto> suspiciousAccounts = analyzeService.findSuspiciousAccounts(
        analysisDateForm.getMonthAsInteger(),
        analysisDateForm.getYearAsInteger());

    // Buscando agências suspeitas
    List<SuspiciousBranchDto> suspiciousBranches = analyzeService.findSuspiciousBranch(
        analysisDateForm.getMonthAsInteger(),
        analysisDateForm.getYearAsInteger());

    attributes.addFlashAttribute("transactionsDto", transactionsDto);
    attributes.addFlashAttribute("suspiciousAccounts", suspiciousAccounts);
    attributes.addFlashAttribute("suspiciousBranches", suspiciousBranches);

    if (transactionsDto.isEmpty() && suspiciousAccounts.isEmpty() && suspiciousBranches.isEmpty()) {
      attributes.addFlashAttribute("message",
          new Message(AlertType.SUCCESS, "Nothing suspicious"));
    }

    return "redirect:/analysis";

  }

}
