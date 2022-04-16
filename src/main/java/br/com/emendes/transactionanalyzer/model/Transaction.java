package br.com.emendes.transactionanalyzer.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Transaction {

  @Id
  private Long id;

  @ManyToOne
  private Account originAccount;
  @ManyToOne
  private Account destinationAccount;
  private BigDecimal value;
  private LocalDateTime dateTime;

}
