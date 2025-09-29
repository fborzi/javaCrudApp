package com.programacion.javacrud.repositories;

import com.programacion.javacrud.entities.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @EntityGraph(attributePaths = {"category"})
    @Query("select p from Product p")
    List<Product> findAllWithRefs();

    @EntityGraph(attributePaths = {"category"})
    Optional<Product> findById(Integer id);
}
