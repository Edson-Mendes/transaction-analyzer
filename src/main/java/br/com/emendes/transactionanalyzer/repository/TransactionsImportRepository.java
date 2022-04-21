package br.com.emendes.transactionanalyzer.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.emendes.transactionanalyzer.model.TransactionsImport;

@Repository
public interface TransactionsImportRepository extends JpaRepository<TransactionsImport, Long> {

  boolean existsByTransactionsDate(LocalDate transactionsDate);

}
