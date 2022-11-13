package com.example.spring_test.repository;

import com.example.spring_test.models.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<Collection, Long> {
}
