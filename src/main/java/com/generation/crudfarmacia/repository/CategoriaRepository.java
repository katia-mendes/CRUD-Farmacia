package com.generation.crudfarmacia.repository;
import com.generation.crudfarmacia.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
List<Categoria> findAllByNomeContainingIgnoreCase(String nome);
}

