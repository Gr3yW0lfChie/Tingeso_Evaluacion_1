package Tingeso.TopEducation.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import Tingeso.TopEducation.entities.CuotaEntity;
import Tingeso.TopEducation.repositories.CuotaRepository;

import java.util.List;
import java.util.Optional;


@Service
public class CuotaService {

	private final CuotaRepository cuotaRepository;

	@Autowired
	public CuotaService(CuotaRepository cuotaRepository){
		this.cuotaRepository = cuotaRepository;
	}

	//----------------------------------------------------------------------------------------------------------
	//Busqueda
	public List<CuotaEntity> obtenerCuotas() {
		return cuotaRepository.findAll();
	}

	public Optional<CuotaEntity> obtenerCuotaPorId(Long id){
		return cuotaRepository.findById(id);
	}

	//----------------------------------------------------------------------------------------------------------
	//Crear
	public CuotaEntity crearCuota(CuotaEntity cuotaEntity){
		return cuotaRepository.save(cuotaEntity);
	}

	//----------------------------------------------------------------------------------------------------------
	//Eliminar
	public void eliminarCuota(Long id){
		cuotaRepository.deleteById(id);
	}

	//----------------------------------------------------------------------------------------------------------
	//Modificar


}
