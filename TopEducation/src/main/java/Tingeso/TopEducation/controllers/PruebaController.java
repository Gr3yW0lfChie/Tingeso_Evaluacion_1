package Tingeso.TopEducation.controllers;


import Tingeso.TopEducation.services.PruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import Tingeso.TopEducation.entities.PruebaEntity;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/pruebas")
public class PruebaController {

	private final PruebaService pruebaService;

	@Autowired
	public PruebaController(PruebaService pruebaService){
		this.pruebaService = pruebaService;
	}

	@GetMapping
	public List<PruebaEntity> obtenerPruebas(){
		return pruebaService.obtenerPruebas();
	}

	@GetMapping("/{id}")
	public Optional<PruebaEntity> obtenerPruebaPorId(@PathVariable Long id){
		return pruebaService.obtenerPruebaPorId(id);
	}

	@PostMapping
	public PruebaEntity crearPrueba(@RequestBody PruebaEntity pruebaEntity){
		return pruebaService.crearPrueba(pruebaEntity);
	}

	@DeleteMapping("/{id}")
	public void eliminarPrueba(@PathVariable Long id){
		pruebaService.eliminarPrueba(id);
	}

	
}
