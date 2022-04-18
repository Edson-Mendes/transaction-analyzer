package br.com.emendes.transactionanalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.emendes.transactionanalyzer.model.TransactionsImport;

@Repository
public interface TransactionImportRepository extends JpaRepository<TransactionsImport, Long> {

}
