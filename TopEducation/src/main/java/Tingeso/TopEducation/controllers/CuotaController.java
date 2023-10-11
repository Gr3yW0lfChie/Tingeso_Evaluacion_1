package Tingeso.TopEducation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import Tingeso.TopEducation.services.CuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import Tingeso.TopEducation.entities.CuotaEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cuotas")
public class CuotaController {

	private final CuotaService cuotaService;

	@Autowired
	public CuotaController(CuotaService cuotaService){
		this.cuotaService = cuotaService;
	}

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

	@DeleteMapping("/{id}")
	public void eliminarCuota(@PathVariable Long id){
		cuotaService.eliminarCuota(id);
	}


}
