package br.com.emendes.transactionanalyzer.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String originBank;
  private String originBranch;
  private String originAccount;

  private String destinationBank;
  private String destinationBranch;
  private String destinationAccount;

  private BigDecimal value;
  private LocalDateTime dateTime;

}
