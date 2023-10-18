package Tingeso.TopEducation.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import Tingeso.TopEducation.entities.CuotaEntity;
import Tingeso.TopEducation.repositories.CuotaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CuotaService {

	@Autowired
	private CuotaRepository cuotaRepository;


	//----------------------------------------------------------------------------------------------------------
	//Busqueda
	public ArrayList<CuotaEntity> obtenerCuotas() {
		return (ArrayList<CuotaEntity>) cuotaRepository.findAll();
	}

	public ArrayList<CuotaEntity> findByRutAlumno(String rut) {
		return cuotaRepository.findByRutAlumno(rut);
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
