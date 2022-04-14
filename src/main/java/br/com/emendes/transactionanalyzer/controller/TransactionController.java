package br.com.emendes.transactionanalyzer.controller;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

  @GetMapping
  public String transactionForm() {
    return "transactionForm.html";
  }

  @PostMapping
  public String submitForm(@RequestParam("file") MultipartFile file) throws IOException {
    System.out.println("===================================================");
    System.out.println("File name: " + file.getOriginalFilename());
    System.out.println("File size: " + file.getSize() + " bytes");
    System.out.println("===================================================");

    Scanner input = new Scanner(file.getInputStream());

    System.out.println("---------------------------------------------------------");
    while (input.hasNextLine()) {
      System.out.println(input.nextLine());
    }
    System.out.println("---------------------------------------------------------");

    input.close();

    return "redirect:/home";
  }

}
