package br.com.emendes.transactionanalyzer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.emendes.transactionanalyzer.model.Authority;
import br.com.emendes.transactionanalyzer.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByEmail(String email);

  Optional<User> findByEmail(String email);

  @Modifying
  @Query("DELETE FROM User u WHERE u.id = :id AND u.email != :email")
  void deleteByIdWhereEmailNotEquals(@Param("id") Long id, @Param("email") String email);

  @Query("SELECT u FROM User u WHERE :authority NOT MEMBER OF u.authorities")
  List<User> findByAuthority(@Param("authority") Authority authority);

}
