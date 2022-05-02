package br.com.emendes.transactionanalyzer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.emendes.transactionanalyzer.model.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

  @Query("SELECT a FROM Account a " +
      "WHERE a.number = :accountNumber " +
      "AND a.branch.number = :branchNumber " +
      "AND a.branch.bank.name = :bankName")
  Optional<Account> findByNumberAndNumberBranchAndBankName(
      @Param("accountNumber") String accountNumber,
      @Param("branchNumber") String branchNumber,
      @Param("bankName") String bankName);

}
