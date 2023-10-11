package Tingeso.TopEducation.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import Tingeso.TopEducation.entities.PruebaEntity;
import Tingeso.TopEducation.repositories.PruebaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PruebaService {

	private final PruebaRepository pruebaRepository;

	@Autowired
	public PruebaService(PruebaRepository pruebaRepository){
		this.pruebaRepository = pruebaRepository;
	}

	//----------------------------------------------------------------------------------------------------------
	//Busqueda
	public List<PruebaEntity> obtenerPruebas() {
		return pruebaRepository.findAll();
	}

	public Optional<PruebaEntity> obtenerPruebaPorId(Long id){
		return pruebaRepository.findById(id);
	}

	//----------------------------------------------------------------------------------------------------------
	//Crear
	public PruebaEntity crearPrueba(PruebaEntity pruebaEntity){
		return pruebaRepository.save(pruebaEntity);
	}

	//----------------------------------------------------------------------------------------------------------
	//Eliminar
	public void eliminarPrueba(Long id){
		pruebaRepository.deleteById(id);
	}


}
