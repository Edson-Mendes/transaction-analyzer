package br.com.emendes.transactionanalyzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.emendes.transactionanalyzer.model.User;

@Service
public class EmailService {

  @Autowired
  private JavaMailSender mailSender;

  @Value("${spring.mail.username}")
  private String sender;

  public void send(User user, String data) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(user.getEmail());
    message.setFrom(sender);
    message.setSubject("Welcome to Transaction Analyzer");
    message
        .setText("This is your password: " + data);

    try {
      mailSender.send(message);
    } catch (Exception e) {
      System.out.println("==========================================================");
      System.out.println("Something went wrong!!!");
      System.out.println(e.getMessage());
      System.out.println("==========================================================");
    }
  }

}
