package com.site.autosite.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AcccountRepository extends JpaRepository<Account, Long>{
    Optional<Account> findByLogin(String login);
}
