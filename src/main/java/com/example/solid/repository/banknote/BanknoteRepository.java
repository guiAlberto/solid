package com.example.solid.repository.banknote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * BanknoteRepository
 */
@RepositoryRestResource
public interface BanknoteRepository extends JpaRepository<Banknote, Long> {


}
