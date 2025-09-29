package com.programacion.javacrud.repositories;

import com.programacion.javacrud.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
