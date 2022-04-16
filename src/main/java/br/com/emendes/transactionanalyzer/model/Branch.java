package br.com.emendes.transactionanalyzer.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Branch {

  @Id
  private Integer id;

  @ManyToOne
  private Bank bank;

}
