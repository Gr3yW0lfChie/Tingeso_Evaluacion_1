package Tingeso.TopEducation.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import Tingeso.TopEducation.entities.CuotaEntity;
import Tingeso.TopEducation.repositories.CuotaRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
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
	/*
	public void eliminarCuota(Long id){
		cuotaRepository.deleteById(id);
	}
	*/
	//----------------------------------------------------------------------------------------------------------
	//Modificar

	public void modificarCuota(Long id, String rutAlumno, Boolean cuotaPagada, Integer precioBase, Integer porcentajeInteres, Integer porcentajeDescuento, Integer precioAPagar){
		Optional<CuotaEntity> cuota = cuotaRepository.findById(id);
		if (cuota.isPresent()){
			CuotaEntity cuotaModificada = cuota.get();
			cuotaModificada.setRutAlumno(rutAlumno);
			cuotaModificada.setCuotaPagada(cuotaPagada);
			cuotaModificada.setPrecioBase(precioBase);
			cuotaModificada.setPorcentajeInteres(porcentajeInteres);
			cuotaModificada.setPorcentajeDescuento(porcentajeDescuento);
			cuotaModificada.setPrecioAPagar(precioAPagar);
			cuotaRepository.save(cuotaModificada);
		}
	}


	public void modificarCuotasVencidas(LocalDate fechaNueva){
		ArrayList<CuotaEntity> cuotas = obtenerCuotas();
		for (CuotaEntity cuota : cuotas){
			if (cuota.getFechaVencimiento().isBefore(fechaNueva) && !cuota.getCuotaPagada()){
				Period period = Period.between(cuota.getFechaVencimiento(), fechaNueva);
				if (period.getMonths() == 1) {
					cuota.setPorcentajeInteres(3);
				} else if (period.getMonths() == 2) {
					cuota.setPorcentajeInteres(6);
				} else if (period.getMonths() == 3) {
					cuota.setPorcentajeInteres(9);
				} else {
					cuota.setPorcentajeInteres(15);
				}
				cuota.setPrecioAPagar(cuota.getPrecioBase() + (cuota.getPrecioBase() * cuota.getPorcentajeInteres() / 100) - (cuota.getPrecioBase() * cuota.getPorcentajeDescuento() / 100));
				cuotaRepository.save(cuota);
			}
		}
	}
}
