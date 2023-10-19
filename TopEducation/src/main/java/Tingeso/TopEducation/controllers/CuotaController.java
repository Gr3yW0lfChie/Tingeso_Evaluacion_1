package Tingeso.TopEducation.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import Tingeso.TopEducation.services.CuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import Tingeso.TopEducation.entities.CuotaEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping
public class CuotaController {

	@Autowired
	private CuotaService cuotaService;


	@GetMapping("/listaCuotas")
	public String listar(Model model) {
		ArrayList<CuotaEntity> cuotas = cuotaService.obtenerCuotas();
		model.addAttribute("cuotas", cuotas);
		return "indexCuotas";
	}

	@GetMapping("/buscarCuotasPorRut")
	public String verCuotaAlumno() {
		return "verCuotaAlumno";
	}

	@PostMapping("/buscarCuotasPorRut")
	public String buscarCuotasPorRut(@RequestParam("rutAlumno") String rutAlumno, Model model) {
		ArrayList<CuotaEntity> cuotas = cuotaService.findByRutAlumno(rutAlumno);
		model.addAttribute("cuotas", cuotas);
		return "verCuotaAlumno";
	}

	@GetMapping("/actualizarCuotas")
	public String actualizarCuotas() {
		return "actualizarCuotas";
	}
	@PostMapping("/actualizarCuotas")
	public String actualizarCuotas(@RequestParam ("fechaCambiada") @DateTimeFormat(pattern = "MM/dd/yyyy") LocalDate fechaCambiada) {
		cuotaService.modificarCuotasVencidas(fechaCambiada);
		return "main";
	}


	@PostMapping("/pagarCuota/{id}")
	public String modificarCuota(@PathVariable Long id, Model model) {
		// Realiza la búsqueda del arancel por el RUT del alumno
		Optional<CuotaEntity> cuota = cuotaService.obtenerCuotaPorId(id);
		// Si el arancel existe, se muestra la información del arancel
		if (cuota.isPresent()) {
			model.addAttribute("cuota", cuota.get());
			return "pagarCuota";
		}
		return "main";
	}

	@PostMapping("/modificarCuota")
	public String modificarCuota(@RequestParam("idCuota") Long idCuota,
								 @RequestParam("rutAlumno") String rutAlumno,
								 @RequestParam("cuotaPagada") boolean cuotaPagada,
								 @RequestParam("precioBase") int precioBase,
								 @RequestParam("porcentajeInteres") int porcentajeInteres,
								 @RequestParam("porcentajeDescuento") int porcentajeDescuento,
								 @RequestParam("precioAPagar") int precioAPagar) {

		cuotaService.modificarCuota(idCuota, rutAlumno, cuotaPagada, precioBase, porcentajeInteres, porcentajeDescuento, precioAPagar);
		return "main";
	}


}
