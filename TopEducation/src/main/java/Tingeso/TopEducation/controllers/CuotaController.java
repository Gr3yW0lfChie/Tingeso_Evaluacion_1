package Tingeso.TopEducation.controllers;

import Tingeso.TopEducation.entities.AlumnoEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import Tingeso.TopEducation.services.CuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import Tingeso.TopEducation.entities.CuotaEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cuotas")
public class CuotaController {

	@Autowired
	private CuotaService cuotaService;



	@GetMapping
	public List<CuotaEntity> obtenerCuotas(){
		return cuotaService.obtenerCuotas();
	}

	@GetMapping("/{id}")
	public Optional<CuotaEntity> obtenerCuotaPorId(@PathVariable Long id){
		return cuotaService.obtenerCuotaPorId(id);
	}

	@PostMapping
	public CuotaEntity crearCuota(@RequestBody CuotaEntity cuotaEntity){
		return cuotaService.crearCuota(cuotaEntity);
	}

	/*
	@DeleteMapping("/{id}")
	public void eliminarCuota(@PathVariable Long id){
		cuotaService.eliminarCuota(id);
	}
	*/

	@GetMapping("/listaCuotas")
	public String listar(Model model) {
		ArrayList<CuotaEntity> cuotas = cuotaService.obtenerCuotas();
		model.addAttribute("cuotas", cuotas);
		return "indexCuotas";
	}

	@GetMapping("/buscarCuotasPorRut")
	public String buscarCuotasPorRut(@RequestParam("rut") String rut, Model model) {
		ArrayList<CuotaEntity> cuotas = cuotaService.findByRutAlumno(rut);
		model.addAttribute("cuotas", cuotas);
		return "verCuotaAlumno";
	}


}
