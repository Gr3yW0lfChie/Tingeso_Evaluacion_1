package Tingeso.TopEducation.services;

import Tingeso.TopEducation.entities.CuotaEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import Tingeso.TopEducation.entities.ArancelEntity;
import Tingeso.TopEducation.repositories.ArancelRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ArancelService {
	private final ArancelRepository arancelRepository;
	private final CuotaService cuotaService;


	@Autowired
	public ArancelService(ArancelRepository arancelRepository, CuotaService cuotaService){
		this.arancelRepository = arancelRepository;
		this.cuotaService = cuotaService;
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
	public void crearArancel(String rutAlumno, int matricula, boolean matriculaPagada, int arancelBase, int cantidadCuotas){
		ArancelEntity arancelEntity = new ArancelEntity();
		arancelEntity.setRutAlumno(rutAlumno);
		arancelEntity.setMatricula(matricula);
		arancelEntity.setMatriculaPagada(matriculaPagada);
		arancelEntity.setArancelBase(arancelBase);
		arancelEntity.setCantidadCuotas(cantidadCuotas);
		arancelRepository.save(arancelEntity);
		crearCuotas(arancelEntity);
	}



	//----------------------------------------------------------------------------------------------------------
	//Eliminar

	public void eliminarArancel(Long id){
		arancelRepository.deleteById(id);
	}

	//----------------------------------------------------------------------------------------------------------
	//Actualizar
	public void modificarArancel(String rutAlumno, int matricula, boolean matriculaPagada, int arancelBase, int cantidadCuotas){
		Optional<ArancelEntity> arancel = arancelRepository.findByRutAlumno(rutAlumno);
		if (arancel.isPresent()){
			ArancelEntity arancelActualizado = arancel.get();
			arancelActualizado.setMatricula(matricula);
			arancelActualizado.setMatriculaPagada(matriculaPagada);
			arancelActualizado.setArancelBase(arancelBase);
			arancelActualizado.setCantidadCuotas(cantidadCuotas);
			arancelRepository.save(arancelActualizado);
		}
	}


	//----------------------------------------------------------------------------------------------------------
	//Crear cuotas que se deben pagar
	public void crearCuotas(ArancelEntity arancel){
		LocalDate fechaActual = LocalDate.of(2024, 1, 10);
		if(arancel.getCantidadCuotas() == 1){
			CuotaEntity cuota = new CuotaEntity();
			cuota.setIdArancel(arancel.getIdArancel());
			cuota.setFechaVencimiento(fechaActual);
			cuota.setCuotaPagada(true);
			cuota.setPrecioBase(arancel.getArancelBase());
			cuota.setPorcentajeInteres(0);
			cuota.setPorcentajeDescuento(0);
			cuota.setPrecioAPagar(arancel.getArancelBase()/2); //Se hace el descuento del 50%

			cuotaService.crearCuota(cuota);
		}else{
			for(int i = 0; i < arancel.getCantidadCuotas(); i++){
				CuotaEntity cuota = new CuotaEntity();
				cuota.setIdArancel(arancel.getIdArancel());
				cuota.setFechaVencimiento(fechaActual);
				cuota.setCuotaPagada(false);
				cuota.setPrecioBase(arancel.getArancelBase()/arancel.getCantidadCuotas());
				cuota.setPorcentajeInteres(0);
				cuota.setPorcentajeDescuento(0);
				cuota.setPrecioAPagar(arancel.getArancelBase()/arancel.getCantidadCuotas());

				cuotaService.crearCuota(cuota);
				fechaActual = fechaActual.plusMonths(1);
			}
		}

	}
}
