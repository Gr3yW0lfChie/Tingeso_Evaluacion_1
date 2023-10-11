package Tingeso.TopEducation.services;

import Tingeso.TopEducation.entities.AlumnoEntity;
import Tingeso.TopEducation.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

	public Optional<AlumnoEntity> obtenerAlumnoPorRut(String rut){
		return alumnoRepository.findByRut(rut);
	}

	//----------------------------------------------------------------------------------------------------------
	//Crear
	public AlumnoEntity crearAlumno(AlumnoEntity alumnoEntity){
		return alumnoRepository.save(alumnoEntity);
	}

	//----------------------------------------------------------------------------------------------------------
	//Eliminar

	public void eliminarAlumno(String rut){
		try{
			alumnoRepository.deleteByRut(rut);
		}catch (Exception ignored){
		}
	}

	//----------------------------------------------------------------------------------------------------------
	//Modificar
	public AlumnoEntity actualizarAlumno(String rut, AlumnoEntity alumnoActualizado){
		return alumnoRepository.findByRut(rut)
				.map(alumno -> {
					alumno.setApellidos(alumnoActualizado.getApellidos());
					alumno.setNombres(alumnoActualizado.getNombres());
					alumno.setFechaNacimiento(alumnoActualizado.getFechaNacimiento());
					alumno.setTipoColegio(alumnoActualizado.getTipoColegio());
					alumno.setNombreColegio(alumnoActualizado.getNombreColegio());
					alumno.setFechaEgreso(alumnoActualizado.getFechaEgreso());
					return alumnoRepository.save(alumno);
				})
				.orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
	}

	//----------------------------------------------------------------------------------------------------------
	//Obtener descuento por tipo de colegio (Municipal, Subvencionado, Particular)
	public Integer obtenerDescuentoPorTipoColegio(AlumnoEntity alumno){
		return switch (alumno.getTipoColegio()) {
			case "Municipal" -> 20;
			case "Subvencionado" -> 10;
			case "Particular" -> 0;
			default -> -1;
		};
	}

	//----------------------------------------------------------------------------------------------------------
	//Obtener descuento por tiempo de salida del colegio

	public Integer obtenerDescuentoPorTiempoSalidaColegio(AlumnoEntity alumno){
		Integer descuento = 0;

		if(alumno.getFechaEgreso() != null){
			Integer anioEgreso = alumno.getFechaEgreso().getYear();
			Integer anioActual = 2021;
			Integer diferencia = anioActual - anioEgreso;
			if(diferencia >= 5){
				descuento = 10;
			}
		}
	}
}
