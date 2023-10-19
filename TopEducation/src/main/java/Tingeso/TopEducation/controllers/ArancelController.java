package Tingeso.TopEducation.controllers;


import Tingeso.TopEducation.entities.CuotaEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import Tingeso.TopEducation.services.ArancelService;
import org.springframework.beans.factory.annotation.Autowired;
import Tingeso.TopEducation.entities.ArancelEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class ArancelController {

	@Autowired
	private ArancelService arancelService;

	@GetMapping("/buscarArancel")
	public String buscarArancelForm() {
		return "buscarArancel";
	}

	@PostMapping("/buscarArancel")
	public String buscarArancel(@RequestParam("rutAlumno") String rutAlumno, Model model) {
		// Realiza la búsqueda del arancel por el RUT del alumno
		ArancelEntity arancel = arancelService.obtenerArancelPorRut(rutAlumno);
		// Si el arancel existe, se muestra la información del arancel
		if (arancel != null) {
			model.addAttribute("arancel", arancel);
			return "creacionCuotas";
		}else{
			return "redirect:/buscarArancel";
		}
	}



	@PostMapping("/creacionArancelCuotas")
	public String creacionArancelCuotas(@RequestParam("rutAlumno") String rutAlumno,
										@RequestParam("matricula") int matricula,
										@RequestParam("matriculaPagada") boolean matriculaPagada,
										@RequestParam("arancelBase") int arancelBase,
										@RequestParam("cantidadCuotas") int cantidadCuotas) {
		// Lógica para modificar el arancel
		arancelService.modificarArancel(rutAlumno, matricula, matriculaPagada, arancelBase, cantidadCuotas);
		return "main";
	}

	/*
	@DeleteMapping("/{id}")
	public void eliminarArancel(@PathVariable Long id){
		arancelService.eliminarArancel(id);
	}
	*/

}
