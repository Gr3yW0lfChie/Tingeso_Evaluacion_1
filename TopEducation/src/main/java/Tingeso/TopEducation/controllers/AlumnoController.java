package Tingeso.TopEducation.controllers;


import Tingeso.TopEducation.entities.AlumnoEntity;
import Tingeso.TopEducation.services.AlumnoService;
import Tingeso.TopEducation.services.ArancelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping
public class AlumnoController {
	@Autowired
	private AlumnoService alumnoService;

	@Autowired
	private ArancelService arancelService;


	@GetMapping("/listaAlumnos")
	public String listar(Model model) {
		ArrayList<AlumnoEntity> alumnos = alumnoService.obtenerAlumnos();
		model.addAttribute("alumnos", alumnos);
		return "index";
	}

	@GetMapping("/nuevoAlumno")
	public String alumno(){
		return "nuevoAlumno";
	}
	@PostMapping("/nuevoAlumno")
	public String nuevoAlumno(@RequestParam("rut") String rut,
								 @RequestParam("apellidos") String apellidos,
								 @RequestParam("nombres") String nombres,
								 @RequestParam("fechaNacimiento") @DateTimeFormat(pattern = "MM/dd/yyyy") LocalDate fechaNacimiento,
								 @RequestParam("tipoColegio") String tipoColegio,
								 @RequestParam("nombreColegio") String nombreColegio,
								 @RequestParam("fechaEgreso") @DateTimeFormat(pattern = "MM/dd/yyyy") LocalDate fechaEgreso){
		alumnoService.crearAlumno(rut, apellidos, nombres, fechaNacimiento, tipoColegio, nombreColegio, fechaEgreso);
		int cantidadMaximaCuotas = alumnoService.obtenerCantidadCuotas(rut);
		arancelService.crearArancel(rut, 70000, false, 1500000, cantidadMaximaCuotas);
		return "redirect:/nuevoAlumno";
	}
	/*
	@DeleteMapping("/{rut}")
	public void eliminarAlumno(@PathVariable String rut){
		alumnoService.eliminarAlumno(rut);
	}
	*/

}
