package Tingeso.TopEducation.services;

import Tingeso.TopEducation.entities.AlumnoEntity;
import Tingeso.TopEducation.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
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
	public ArrayList<AlumnoEntity> obtenerAlumnos() {
		return (ArrayList<AlumnoEntity>) alumnoRepository.findAll();
	}

	public Optional<AlumnoEntity> obtenerAlumnoPorRut(String rut){
		return alumnoRepository.findByRut(rut);
	}

	//----------------------------------------------------------------------------------------------------------
	//Crear
	public void crearAlumno(String rut, String apellidos, String nombres, LocalDate fechaNacimiento, String tipoColegio, String nombreColegio, LocalDate fechaEgreso){
		AlumnoEntity alumnoEntity = new AlumnoEntity();
		alumnoEntity.setRut(rut);
		alumnoEntity.setApellidos(apellidos);
		alumnoEntity.setNombres(nombres);
		alumnoEntity.setFechaNacimiento(fechaNacimiento);
		alumnoEntity.setTipoColegio(tipoColegio);
		alumnoEntity.setNombreColegio(nombreColegio);
		alumnoEntity.setFechaEgreso(fechaEgreso);
		alumnoRepository.save(alumnoEntity);
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
	public Integer obtenerDescuentoPorTipoColegio(String alumno){
		return switch (alumno) {
			case "Municipal" -> 20;
			case "Subvencionado" -> 10;
			case "Particular" -> 0;
			default -> -1;
		};
	}

	//----------------------------------------------------------------------------------------------------------
	//Obtener descuento por tiempo de salida del colegio

	public Integer obtenerDescuentoPorTiempoSalidaColegio(LocalDate fechaEgreso){
		int descuento = 0;
		LocalDate fechaActual = LocalDate.of(2024, 1, 1);

		Period periodo = Period.between(fechaEgreso, fechaActual);

		Integer diferencia = periodo.getYears();

		if(diferencia < 1){
			descuento = 15;
		} else if (diferencia < 3){
			descuento = 8;
		} else if (diferencia < 5){
			descuento = 4;
		} else {
			descuento = 0;
		}

		return descuento;
	}

	//----------------------------------------------------------------------------------------------------------
	//Obtener cantidad de cuotas que puede tener el alumno
	public Integer obtenerCantidadCuotas(String alumno){
		return switch (alumno) {
			case "Municipal" -> 10;
			case "Subvencionado" -> 7;
			case "Particular" -> 4;
			default -> -1;
		};
	}

}
