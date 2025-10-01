package com.iudigital.servicio;

import com.iudigital.modelo.EntidadEmpleado;
import com.iudigital.repositorio.RepositorioEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioEmpleado {

    @Autowired
    private RepositorioEmpleado repositorioEmpleado;

    // Obtener todos los empleados
    public List<EntidadEmpleado> obtenerTodosLosEmpleados() {
        return (List<EntidadEmpleado>) repositorioEmpleado.findAll();
    }

    // Obtener un empleado por ID
    public Optional<EntidadEmpleado> obtenerEmpleadoPorId(Long id) {
        return repositorioEmpleado.findById(id);
    }

    // Crear o actualizar un empleado
    public EntidadEmpleado crearOActualizarEmpleado(EntidadEmpleado empleado) {
        return repositorioEmpleado.save(empleado);
    }

    // Eliminar un empleado por ID
    public void eliminarEmpleado(Long id) {
        repositorioEmpleado.deleteById(id);
    }

    // Método específico para la lógica de crear/actualizar como en la documentación
    public EntidadEmpleado guardarOActualizarEmpleado(EntidadEmpleado entidad) {
        if (entidad.getId() == null) {
            // Crear nuevo empleado
            entidad = repositorioEmpleado.save(entidad);
            return entidad;
        } else {
            // Actualizar empleado existente
            Optional<EntidadEmpleado> empleado = repositorioEmpleado.findById(entidad.getId());
            if (empleado.isPresent()) {
                EntidadEmpleado nuevaEntidad = empleado.get();
                nuevaEntidad.setCorreo(entidad.getCorreo());
                nuevaEntidad.setPrimerNombre(entidad.getPrimerNombre());
                nuevaEntidad.setApellidos(entidad.getApellidos());
                nuevaEntidad = repositorioEmpleado.save(nuevaEntidad);
                return nuevaEntidad;
            } else {
                // Si no existe, crear nuevo
                entidad = repositorioEmpleado.save(entidad);
                return entidad;
            }
        }
    }
}