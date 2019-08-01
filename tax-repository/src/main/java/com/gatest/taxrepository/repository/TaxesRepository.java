package com.gatest.taxrepository.repository;

import com.gatest.taxrepository.models.Tax;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaxesRepository extends JpaRepository<Tax, String> {
    Optional<Tax> findTaxByCategory(String category);
}
