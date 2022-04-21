package br.com.emendes.transactionanalyzer.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "importation")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionsImport {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate transactionsDate;
  private LocalDateTime importDateTime;

  @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
  private List<Transaction> transactions;
}
