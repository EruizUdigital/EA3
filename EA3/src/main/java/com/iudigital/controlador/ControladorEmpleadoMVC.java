package com.iudigital.controlador;

import com.iudigital.modelo.EntidadEmpleado;
import com.iudigital.servicio.ServicioEmpleado;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class ControladorEmpleadoMVC {

    @Autowired
    private ServicioEmpleado servicioEmpleado;

    // Mostrar lista de todos los empleados
    @GetMapping
    public String mostrarTodosLosEmpleados(Model modelo) {
        List<EntidadEmpleado> lista = servicioEmpleado.obtenerTodosLosEmpleados();
        modelo.addAttribute("empleados", lista);
        return "lista-empleados";
    }

    // Mostrar formulario para nuevo empleado
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoEmpleado(Model modelo) {
        EntidadEmpleado empleado = new EntidadEmpleado();
        modelo.addAttribute("empleado", empleado);
        return "formulario-empleado";
    }

    // Mostrar formulario para editar empleado existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarEmpleado(@PathVariable Long id, Model modelo) {
        EntidadEmpleado empleado = servicioEmpleado.obtenerEmpleadoPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de empleado inválido: " + id));
        modelo.addAttribute("empleado", empleado);
        return "formulario-empleado";
    }

    // Guardar o actualizar empleado (POST) CON VALIDACIONES
    @PostMapping("/guardar")
    public String guardarOActualizarEmpleado(@Valid @ModelAttribute("empleado") EntidadEmpleado empleado,
                                             BindingResult resultado, Model modelo) {
        if (resultado.hasErrors()) {
            // Si hay errores de validación, regresar al formulario
            return "formulario-empleado";
        }
        servicioEmpleado.guardarOActualizarEmpleado(empleado);
        return "redirect:/?exito=true";
    }

    // Eliminar empleado
    @GetMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable Long id) {
        servicioEmpleado.eliminarEmpleado(id);
        return "redirect:/?eliminado=true";
    }
}