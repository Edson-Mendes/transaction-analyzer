package br.com.emendes.transactionanalyzer.controller.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SuspiciousBranchDto {

  private String bankName;
  private String branchNumber;
  private BigDecimal value;
  private String type;

}
