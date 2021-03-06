package br.com.emendes.transactionanalyzer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.emendes.transactionanalyzer.controller.dto.SuspiciousAccountDto;
import br.com.emendes.transactionanalyzer.controller.dto.SuspiciousBranchDto;
import br.com.emendes.transactionanalyzer.model.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  @Query("SELECT t FROM Transaction t WHERE MONTH(t.dateTime) = :month AND YEAR(t.dateTime) = :year AND t.value >= 100000")
  List<Transaction> findByMonthAndYearBiggerOrEquals(
      @Param("month") Integer month,
      @Param("year") Integer year);

  @Query("SELECT new br.com.emendes.transactionanalyzer.controller.dto" +
      ".SuspiciousAccountDto(t.originAccount.branch.bank.name, " +
      "t.originAccount.branch.number, " +
      "t.originAccount.number, " +
      "SUM(t.value), 'output') " +
      "FROM Transaction t " +
      "WHERE MONTH(t.dateTime) = :month AND YEAR(t.dateTime) = :year " +
      "GROUP BY t.originAccount")
  List<SuspiciousAccountDto> findSuspiciousOriginAccounts(
      @Param("month") Integer month,
      @Param("year") Integer year);

  @Query("SELECT new br.com.emendes.transactionanalyzer.controller.dto" +
      ".SuspiciousAccountDto(t.destinationAccount.branch.bank.name, " +
      "t.destinationAccount.branch.number, " +
      "t.destinationAccount.number, " +
      "SUM(t.value), 'input') " +
      "FROM Transaction t " +
      "WHERE MONTH(t.dateTime) = :month AND YEAR(t.dateTime) = :year " +
      "GROUP BY t.destinationAccount")
  List<SuspiciousAccountDto> findSuspiciousDestinationAccounts(
      @Param("month") Integer month,
      @Param("year") Integer year);

  @Query("SELECT new br.com.emendes.transactionanalyzer.controller.dto" +
      ".SuspiciousBranchDto(t.destinationAccount.branch.bank.name, " +
      "t.destinationAccount.branch.number, " +
      "SUM(t.value), 'input') " +
      "FROM Transaction t " +
      "WHERE MONTH(t.dateTime) = :month AND YEAR(t.dateTime) = :year " +
      "GROUP BY t.destinationAccount.branch")
  List<SuspiciousBranchDto> findSuspiciousDestinationBranch(
      @Param("month") Integer month,
      @Param("year") Integer year);

  @Query("SELECT new br.com.emendes.transactionanalyzer.controller.dto" +
      ".SuspiciousBranchDto(t.originAccount.branch.bank.name, " +
      "t.originAccount.branch.number, " +
      "SUM(t.value), 'output') " +
      "FROM Transaction t " +
      "WHERE MONTH(t.dateTime) = :month AND YEAR(t.dateTime) = :year " +
      "GROUP BY t.originAccount.branch")
  List<SuspiciousBranchDto> findSuspiciousOriginBranch(
      @Param("month") Integer month,
      @Param("year") Integer year);

  @Query("SELECT count(t) > 0 FROM Transaction t WHERE MONTH(t.dateTime) = :month AND YEAR(t.dateTime) = :year")
  boolean existsByMonthAndYear(@Param("month") Integer month, @Param("year") Integer year);
}
