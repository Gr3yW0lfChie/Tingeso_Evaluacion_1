package Tingeso.TopEducation.controllers;


import Tingeso.TopEducation.entities.AlumnoEntity;
import Tingeso.TopEducation.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


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
	public AlumnoEntity obtenerAlumnoPorRut(@PathVariable String rut){
		return alumnoService.obtenerAlumnoPorRut(rut);
	}

	@DeleteMapping("/{rut}")
	public void eliminarAlumno(@PathVariable String rut){
		alumnoService.eliminarAlumno(rut);
	}
}
