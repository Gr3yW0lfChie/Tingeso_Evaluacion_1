package Tingeso.TopEducation.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import Tingeso.TopEducation.entities.ArancelEntity;
import Tingeso.TopEducation.repositories.ArancelRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ArancelService {
	private final ArancelRepository arancelRepository;

	@Autowired
	public ArancelService(ArancelRepository arancelRepository){
		this.arancelRepository = arancelRepository;
	}

	//----------------------------------------------------------------------------------------------------------
	//Busqueda
	public List<ArancelEntity> obtenerAranceles() {
		return arancelRepository.findAll();
	}

	public Optional<ArancelEntity> obtenerArancelPorId(Long id){
		return arancelRepository.findById(id);
	}

	//----------------------------------------------------------------------------------------------------------
	//Crear
	public ArancelEntity crearArancel(ArancelEntity arancelEntity){
		return arancelRepository.save(arancelEntity);
	}

	//----------------------------------------------------------------------------------------------------------
	//Eliminar

	public void eliminarArancel(Long id){
		arancelRepository.deleteById(id);
	}

	//----------------------------------------------------------------------------------------------------------
	//Actualizar
	public ArancelEntity actualizarArancel(Long idArancel, ArancelEntity arancelActualizado){
		return arancelRepository.findById(idArancel)
				.map(arancel -> {
					arancel.setAlumno(arancelActualizado.getAlumno());
					arancel.setMatricula(arancelActualizado.getMatricula());
					arancel.setMatriculaPagada(arancelActualizado.getMatriculaPagada());
					arancel.setArancelBase(arancelActualizado.getArancelBase());
					arancel.setCantidadCuotas(arancelActualizado.getCantidadCuotas());
					return arancelRepository.save(arancel);
				})
				.orElseThrow(() -> new RuntimeException("Arancel de alumno no encontrado"));
	}

	//----------------------------------------------------------------------------------------------------------
}
