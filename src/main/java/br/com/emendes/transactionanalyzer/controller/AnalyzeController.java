package br.com.emendes.transactionanalyzer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.emendes.transactionanalyzer.model.dto.SuspiciousAccountDto;
import br.com.emendes.transactionanalyzer.model.dto.SuspiciousBranchDto;
import br.com.emendes.transactionanalyzer.model.dto.TransactionDto;
import br.com.emendes.transactionanalyzer.model.form.AnalysisDateForm;
import br.com.emendes.transactionanalyzer.service.AnalyzeService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/analysis")
@RequiredArgsConstructor
public class AnalyzeController {

  private final AnalyzeService analyzeService;

  @GetMapping
  public String analyzePage(AnalysisDateForm analysisDateForm) {

    return "page/analysisPage";
  }

  @GetMapping("/analyzing")
  public String analyze(@Valid AnalysisDateForm analysisDateForm, RedirectAttributes attributes) {

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

    attributes.addFlashAttribute("analysisDateForm", analysisDateForm);
    attributes.addFlashAttribute("transactionsDto", transactionsDto);
    attributes.addFlashAttribute("suspiciousAccounts", suspiciousAccounts);
    attributes.addFlashAttribute("suspiciousBranches", suspiciousBranches);

    return "redirect:/analysis";

  }

}
