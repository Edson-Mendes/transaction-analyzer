package br.com.emendes.transactionanalyzer.controller;

import java.security.Principal;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.emendes.transactionanalyzer.controller.form.FileForm;
import br.com.emendes.transactionanalyzer.model.dto.ImportDetailsDto;
import br.com.emendes.transactionanalyzer.model.dto.TransactionsImportDto;
import br.com.emendes.transactionanalyzer.model.util.AlertType;
import br.com.emendes.transactionanalyzer.model.util.Message;
import br.com.emendes.transactionanalyzer.service.ImportService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
// TODO: Mudar mapeamento para /imports
@RequestMapping("/transactions")
public class TransactionController {

  private final ImportService importService;

  @GetMapping
  public ModelAndView transactionsPage(FileForm fileForm) {
    ModelAndView modelAndView = new ModelAndView("page/transactionsPage.html");
    // TODO: Paginar busca de transações
    List<TransactionsImportDto> transactionsImport = importService.findAll();
    modelAndView.addObject("transactionsImport", transactionsImport);

    return modelAndView;
  }

  @GetMapping("/{id}")
  public ModelAndView details(@PathVariable Long id) {
    ModelAndView modelAndView = new ModelAndView("page/importDetailsPage");
    ImportDetailsDto importDetailsDto = importService.findById(id);

    modelAndView.addObject("importDetails", importDetailsDto);

    return modelAndView;
  }

  @PostMapping
  @Transactional
  public String submitForm(@Valid FileForm fileForm, BindingResult bindingResult, RedirectAttributes attributes,
      Principal principal) {
    if (bindingResult.hasErrors()) {
      attributes.addFlashAttribute("message",
          new Message(AlertType.ERROR, bindingResult.getAllErrors().get(0).getDefaultMessage()));
      return "redirect:/transactions";
    }
    importService.processImport(fileForm.getFile(), principal.getName());

    final Message message = Message.builder()
        .type(AlertType.SUCCESS)
        .message("File processed successfully")
        .build();
    attributes.addFlashAttribute("message", message);
    return "redirect:/transactions";
  }

}
