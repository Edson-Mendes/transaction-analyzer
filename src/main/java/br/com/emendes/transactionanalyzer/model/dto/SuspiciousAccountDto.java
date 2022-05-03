package br.com.emendes.transactionanalyzer.model.dto;

import java.math.BigDecimal;

import br.com.emendes.transactionanalyzer.model.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SuspiciousAccountDto {

  // TODO: Talvez substituir por um DTO.
  private Account account;
  private BigDecimal value;
  private String type;

}
