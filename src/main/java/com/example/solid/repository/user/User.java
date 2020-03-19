package com.example.solid.repository.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

/**
 * User
 */
@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String password;
}
