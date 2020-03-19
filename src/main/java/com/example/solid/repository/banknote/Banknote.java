package com.example.solid.repository.banknote;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

/**
 * Banknote
 */
@Entity
@Data
public class Banknote {

    @Id
    @GeneratedValue
    private Long Id;

    private BanknoteValue value;
}
