package br.com.emendes.transactionanalyzer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.emendes.transactionanalyzer.model.entity.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {

  Optional<Bank> findByName(String name);

  boolean existsByName(String bankName);

}
