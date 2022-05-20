package br.com.emendes.transactionanalyzer.service;

import org.springframework.stereotype.Service;

import br.com.emendes.transactionanalyzer.model.entity.Bank;
import br.com.emendes.transactionanalyzer.model.entity.Branch;
import br.com.emendes.transactionanalyzer.repository.BranchRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BranchService {

  private final BranchRepository branchRepository;
  private final BankService bankService;

  public Branch createIfNotExists(String branchNumber, String bankName) {

    Branch branch = findByBranchNumberAndBankName(branchNumber, bankName);

    if (branch == null) {
      Bank bank = bankService.findByName(bankName);

      branch = save(Branch.builder()
          .number(branchNumber)
          .bank(bank)
          .build());
    }

    return branch;
  }

  public Branch save(Branch branch) {
    return branchRepository.save(branch);
  }

  public Branch findByBranchNumberAndBankName(String branchNumber, String bankName) {
    return branchRepository.findByBranchNumberAndBankName(branchNumber, bankName).orElse(null);
  }

}
