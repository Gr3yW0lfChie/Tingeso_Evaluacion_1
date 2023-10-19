package Tingeso.TopEducation.controllers;


import Tingeso.TopEducation.services.PruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import Tingeso.TopEducation.entities.PruebaEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping
public class PruebaController {

	@Autowired
	private PruebaService pruebaService;


	@GetMapping("/subirArchivo")
	public String main() {
		return "subirArchivo";
	}

	@PostMapping("/subirArchivo")
	public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		pruebaService.guardar(file);
		redirectAttributes.addFlashAttribute("mensaje", "Archivo cargado correctamente");
		pruebaService.leerCsv("Prueba.csv");
		return "redirect:/subirArchivo";
	}

	@GetMapping("/informacionPrueba")
	public String listar(Model model) {
		ArrayList<PruebaEntity> pruebas = pruebaService.obtenerPruebas();
		model.addAttribute("pruebas", pruebas);
		return "indexPrueba";
	}


}
