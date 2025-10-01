package com.iudigital.repositorio;

import com.iudigital.modelo.EntidadEmpleado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioEmpleado extends CrudRepository<EntidadEmpleado, Long> {
    // Spring Data JPA provee automáticamente los métodos CRUD
}