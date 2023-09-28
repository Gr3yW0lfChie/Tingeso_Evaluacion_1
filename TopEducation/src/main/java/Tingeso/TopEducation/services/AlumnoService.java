package Tingeso.TopEducation.services;

import Tingeso.TopEducation.entities.AlumnoEntity;
import Tingeso.TopEducation.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoService {

	private final AlumnoRepository alumnoRepository;
	@Autowired
	public AlumnoService(AlumnoRepository alumnoRepository){
		this.alumnoRepository = alumnoRepository;
	}

	//----------------------------------------------------------------------------------------------------------
	//Busqueda
	public List<AlumnoEntity> obtenerAlumnos() {
		return alumnoRepository.findAll();
	}

	public AlumnoEntity obtenerAlumnoPorRut(String rut){
		return alumnoRepository.findByRut(rut);
	}

	//----------------------------------------------------------------------------------------------------------
	//Crear
	public AlumnoEntity crearAlumno(AlumnoEntity alumnoEntity){
		return alumnoRepository.save(alumnoEntity);
	}

	//----------------------------------------------------------------------------------------------------------
	//Eliminar

	public boolean eliminarAlumno(String rut){
		try{
			alumnoRepository.deleteByRut(rut);
			return true;
		}catch (Exception err){
			return false;
		}
	}

	//----------------------------------------------------------------------------------------------------------
	//Modificar



}
