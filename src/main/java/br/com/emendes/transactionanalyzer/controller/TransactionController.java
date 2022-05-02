package br.com.emendes.transactionanalyzer.controller;

import java.security.Principal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.emendes.transactionanalyzer.model.dto.ImportDetailsDto;
import br.com.emendes.transactionanalyzer.model.dto.TransactionsImportDto;
import br.com.emendes.transactionanalyzer.model.util.AlertType;
import br.com.emendes.transactionanalyzer.model.util.Message;
import br.com.emendes.transactionanalyzer.service.ImportService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
// TODO: Mudar nome para mapeamento para /imports
@RequestMapping("/transactions")
public class TransactionController {

  private final ImportService importService;

  @GetMapping
  public ModelAndView transactionsPage() {
    ModelAndView modelAndView = new ModelAndView("page/transactionsPage.html");
    // TODO: Paginar busca de transações
    List<TransactionsImportDto> transactionsImport = importService.findAll();
    modelAndView.addObject("transactionsImport", transactionsImport);

    return modelAndView;
  }

  @GetMapping("/{id}")
  public ModelAndView details(@PathVariable Long id) {
    ModelAndView modelAndView = new ModelAndView("page/importDetailsPage");
    // TODO: fix details aqui
    ImportDetailsDto importDetailsDto = importService.findById(id);

    modelAndView.addObject("importDetails", importDetailsDto);

    return modelAndView;
  }

  @PostMapping
  @Transactional
  public String submitForm(@RequestParam("file") MultipartFile file, RedirectAttributes attributes,
      Principal principal) {
    importService.processImport(file, principal.getName());

    final Message message = Message.builder()
        .type(AlertType.SUCCESS)
        .message("Arquivo processado com sucesso")
        .build();
    attributes.addFlashAttribute("message", message);
    return "redirect:/transactions";
  }

}
