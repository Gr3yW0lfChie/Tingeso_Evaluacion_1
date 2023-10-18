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
@RequestMapping("/pruebas")
public class PruebaController {

	@Autowired
	private PruebaService pruebaService;


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

	@GetMapping("/subirArchivo")
	public String main() {
		return "subirPrueba";
	}

	@PostMapping("/SubirArchivo")
	public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		pruebaService.guardar(file);
		redirectAttributes.addFlashAttribute("mensaje", "Archivo cargado correctamente");
		pruebaService.leerCsv("Prueba.csv");
		return "redirect:/subirPrueba";
	}

	@GetMapping("/informacionPrueba")
	public String listar(Model model) {
		ArrayList<PruebaEntity> datas = pruebaService.obtenerPruebas();
		model.addAttribute("datas", datas);
		return "informacionPrueba";
	}
}
