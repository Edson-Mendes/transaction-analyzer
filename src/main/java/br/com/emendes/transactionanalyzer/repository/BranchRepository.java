package br.com.emendes.transactionanalyzer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.emendes.transactionanalyzer.model.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch, Integer> {

  @Query("SELECT b FROM Branch b " +
      "WHERE b.number = :branchNumber " +
      "AND b.bank.name = :bankName")
  Optional<Branch> findByBranchNumberAndBankName(
      @Param("branchNumber") String branchNumber,
      @Param("bankName") String bankName);
}
