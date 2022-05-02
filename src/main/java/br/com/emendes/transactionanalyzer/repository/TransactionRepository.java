package br.com.emendes.transactionanalyzer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.emendes.transactionanalyzer.model.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  @Query("SELECT t FROM Transaction t WHERE MONTH(t.dateTime) = :month AND YEAR(t.dateTime) = :year AND t.value >= 100000")
  List<Transaction> findByMonthAndYearBiggerOrEquals(Integer month, Integer year);

}
