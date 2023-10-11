package Tingeso.TopEducation.controllers;


import Tingeso.TopEducation.entities.AlumnoEntity;
import Tingeso.TopEducation.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/alumnos")
public class AlumnoController {
	private final AlumnoService alumnoService;

	@Autowired
	public AlumnoController(AlumnoService alumnoService){
		this.alumnoService = alumnoService;
	}

	@GetMapping
	public List<AlumnoEntity> obtenerAlumnos(){
		return alumnoService.obtenerAlumnos();
	}

	@GetMapping("/{rut}")
	public Optional<AlumnoEntity> obtenerAlumnoPorRut(@PathVariable String rut){
		return alumnoService.obtenerAlumnoPorRut(rut);
	}

	@PostMapping
	public AlumnoEntity crearAlumno(@RequestBody AlumnoEntity alumnoEntity){
		return alumnoService.crearAlumno(alumnoEntity);
	}

	@DeleteMapping("/{rut}")
	public void eliminarAlumno(@PathVariable String rut){
		alumnoService.eliminarAlumno(rut);
	}
}
