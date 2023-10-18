package Tingeso.TopEducation.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import Tingeso.TopEducation.services.ArancelService;
import org.springframework.beans.factory.annotation.Autowired;
import Tingeso.TopEducation.entities.ArancelEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/aranceles")
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
		Optional<ArancelEntity> arancel = arancelService.obtenerArancelPorRut(rutAlumno);

		if (arancel.isPresent()) {
			model.addAttribute("arancel", arancel.get());
			return "creacionCuotas"; // Redirige a la página de modificación
		} else {
			// Manejar el caso en el que no se encuentre el arancel
			return "redirect:/buscarArancel";
		}
	}

	@GetMapping("/creacionCuotas")
	public String arancel(){
		return "creacionCuotas";
	}

	@PostMapping
	public String creacionCuotas(@RequestParam("rut") String rut,
								   @RequestParam("matricula") int matricula,
								   @RequestParam("matriculaPagada") boolean matriculaPagada,
								   @RequestParam("arancelBase") int arancelBase,
								   @RequestParam("cantidadCuotas") int cantidadCuotas){
		arancelService.modificarArancel(rut, matricula, matriculaPagada, arancelBase, cantidadCuotas);
		return "redirect:/creacionCuotas";
	}

	@DeleteMapping("/{id}")
	public void eliminarArancel(@PathVariable Long id){
		arancelService.eliminarArancel(id);
	}


}
