package Tingeso.TopEducation.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import Tingeso.TopEducation.services.ArancelService;
import org.springframework.beans.factory.annotation.Autowired;
import Tingeso.TopEducation.entities.ArancelEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/aranceles")
public class ArancelController {

	private final ArancelService arancelService;

	@Autowired
	public ArancelController(ArancelService arancelService){
		this.arancelService = arancelService;
	}

	@GetMapping
	public List<ArancelEntity> obtenerAranceles(){
		return arancelService.obtenerAranceles();
	}

	@GetMapping("/{id}")
	public Optional<ArancelEntity> obtenerArancelPorId(@PathVariable Long id){
		return arancelService.obtenerArancelPorId(id);
	}

	@PostMapping
	public String modificarArancel(@RequestParam("rut") String rut,
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
