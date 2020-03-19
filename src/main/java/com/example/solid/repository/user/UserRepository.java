package com.example.solid.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * UserRepository
 */
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {


}
