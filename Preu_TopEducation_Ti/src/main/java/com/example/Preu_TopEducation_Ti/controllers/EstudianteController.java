package com.example.Preu_TopEducation_Ti.controllers;

import com.example.Preu_TopEducation_Ti.entities.EstudianteEntity;
import com.example.Preu_TopEducation_Ti.entities.ReporteEntity;
import com.example.Preu_TopEducation_Ti.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class EstudianteController {

    @Autowired // Es una instancia de EstudianteService //
    EstudianteService estudianteService;

    @GetMapping("/Inicio") // devuelve la vista del inicio //
    public String Inicio(){
        return "index";
    }

    @GetMapping("/")
    public String Home(){
        return "redirect:/login";
    }

    @GetMapping("/Formulario") // muestra la vista del formulario y recibe los datos //
    public String IngresarEstudiante(Model model){
        model.addAttribute("estudiante", new EstudianteEntity());
        return "Formulario";
    }

    @PostMapping("/Guardar_Estudiante")// procesa y guardarlo en la base de datos //
    public String GuardarEstudiante(@ModelAttribute("estudiante") EstudianteEntity estudiante){
        estudianteService.ingresarestudiante(estudiante);
        return "index";
    }

    @GetMapping("/Reporte") // Muestra los reporte de los estudiantes //
    public String reporte(Model model) {
        ArrayList<ReporteEntity> reporte = estudianteService.crearReporte();
        model.addAttribute("reporte", reporte);
        return "Reporte";

    }
    @PostMapping("/Eliminar_Estudiante")// procesa y guardarlo en la base de datos //
    public String EliminarEstudiante(@RequestParam("rut") String rut){
        estudianteService.EliminarEstudiante(rut);
        return "redirect:/Reporte";
    }
    @GetMapping("/Modificar_Estudiante")
    public String modificarEstudiante(@RequestParam("rut") String rut, Model model) {
        EstudianteEntity reporte = estudianteService.obtenerEstudiantePorRut(rut);
        if (reporte != null) {
            model.addAttribute("reporte", reporte);
        }
        return "ModificarEstudiante";
    }

    @PostMapping("/Guardar_Cambios")
    public String guardarCambios(
            @RequestParam("rut") String rut,
            @RequestParam("nombre") String nombre,
            @RequestParam("apellidos") String apellidos,
            @RequestParam("fechaNacimiento") String fechaNacimiento,
            Model model) {

        // Busca al estudiante existente por su RUT
        EstudianteEntity estudiante = estudianteService.obtenerEstudiantePorRut(rut);

        if (estudiante != null) {
            // Actualiza solo los campos necesarios
            estudiante.setNombres(nombre);
            estudiante.setApellidos(apellidos);
            estudiante.setFechaNacimiento(fechaNacimiento);

            // Guarda los cambios en la base de datos
            estudianteService.guardar(estudiante);

            model.addAttribute("mensaje", "Estudiante modificado exitosamente");
        } else {
            model.addAttribute("error", "No se encontró el estudiante");
        }

        return "redirect:/Reporte";
    }

}

