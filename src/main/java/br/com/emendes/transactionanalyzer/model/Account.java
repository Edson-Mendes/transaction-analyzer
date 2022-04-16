package br.com.emendes.transactionanalyzer.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Account {

  @Id
  private Integer id;

  @ManyToOne
  private Branch branch;

}
