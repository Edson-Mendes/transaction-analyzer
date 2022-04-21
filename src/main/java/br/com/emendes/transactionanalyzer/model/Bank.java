package br.com.emendes.transactionanalyzer.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

// TODO: Ainda não usado
@Entity
@Data
public class Bank {

  @Id
  private Integer id;

  private String name;

}
