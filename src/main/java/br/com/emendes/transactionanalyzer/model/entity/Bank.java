package br.com.emendes.transactionanalyzer.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Bank {

  @Id
  private Integer id;

  @Column(unique = true, nullable = false)
  private String name;

}
