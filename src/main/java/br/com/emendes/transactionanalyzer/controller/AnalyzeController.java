package br.com.emendes.transactionanalyzer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/analysis")
public class AnalyzeController {

  @GetMapping
  public ModelAndView analyzePage() {
    ModelAndView modelAndView = new ModelAndView("page/analysisPage");
    return modelAndView;
  }

  @GetMapping("/oi")
  public String analyze(@RequestParam("monthField") String month, @RequestParam("yearField") String year) {

    return "redirect:/analysis";
  }

}
