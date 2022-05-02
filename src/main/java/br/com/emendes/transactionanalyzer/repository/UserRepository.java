package br.com.emendes.transactionanalyzer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.emendes.transactionanalyzer.model.entity.Authority;
import br.com.emendes.transactionanalyzer.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByEmail(String email);

  Optional<User> findByEmail(String email);

  // Apenas usuários com authority 'ADMIN' não podem ser deletados.
  @Modifying
  @Query("DELETE FROM User u WHERE u.id = :id AND u.email != :email AND :authority NOT MEMBER OF u.authorities")
  void deleteByIdWhereEmailNotEqualsAndNotAdmin(@Param("id") Long id, @Param("email") String email,
      @Param("authority") Authority authority);

  @Query("SELECT u FROM User u WHERE :authority NOT MEMBER OF u.authorities")
  List<User> findByAuthority(@Param("authority") Authority authority);

  @Query("SELECT u FROM User u WHERE u.id = :id AND u.email != :email AND :authority NOT MEMBER OF u.authorities")
  Optional<User> findByIdWhereEmailNotEqualsAndNotAdmin(@Param("id") Long id, @Param("email") String email,
      @Param("authority") Authority authority);

}
