package Tingeso.TopEducation;


import Tingeso.TopEducation.entities.CuotaEntity;
import Tingeso.TopEducation.services.CuotaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CuotaTest {

	CuotaEntity cuota = new CuotaEntity();

	@Autowired
	private CuotaService cuotaService;


	//----------------------------------------------------------------------------------------------------------
	//modificarCuota
	@Test
	@Transactional
	void modificarCuota(){
		int precioBase = 1500000/4;
		cuota.setRutAlumno("12.345.678-2");
		cuota.setFechaVencimiento(LocalDate.of(2020,10,10));
		cuota.setCuotaPagada(false);
		cuota.setPrecioBase(precioBase);
		cuota.setPorcentajeInteres(0);
		cuota.setPorcentajeDescuento(0);
		cuota.setPrecioAPagar(precioBase);

		cuotaService.crearCuota(cuota);

		cuotaService.modificarCuota(cuota.getIdCuota(), "12.345.678-2",true, precioBase, 0, 0, precioBase);

		Optional<CuotaEntity> cuotaModificada = cuotaService.obtenerCuotaPorId(cuota.getIdCuota());
		cuotaModificada.ifPresent(cuotaEntity -> assertEquals(true, cuotaEntity.getCuotaPagada()));
	}


	//----------------------------------------------------------------------------------------------------------
	//listarCuotas
	@Test
	@Transactional
	void buscarlistaCuotas(){
		int precioBase = 1500000/4;
		cuota.setRutAlumno("12.345.678-2");
		cuota.setFechaVencimiento(LocalDate.of(2020,10,10));
		cuota.setCuotaPagada(false);
		cuota.setPrecioBase(precioBase);
		cuota.setPorcentajeInteres(0);
		cuota.setPorcentajeDescuento(0);
		cuota.setPrecioAPagar(precioBase);
		cuotaService.crearCuota(cuota);

		CuotaEntity cuota2 = new CuotaEntity();
		cuota2.setRutAlumno("12.345.678-2");
		cuota2.setFechaVencimiento(LocalDate.of(2020,11,10));
		cuota2.setCuotaPagada(false);
		cuota2.setPrecioBase(precioBase);
		cuota2.setPorcentajeInteres(0);
		cuota2.setPorcentajeDescuento(0);
		cuota2.setPrecioAPagar(precioBase);
		cuotaService.crearCuota(cuota2);

		ArrayList<CuotaEntity> esperados = new ArrayList<>();

		CuotaEntity aux1 = new CuotaEntity();
		aux1.setIdCuota(1L);
		aux1.setRutAlumno("12.345.678-2");
		aux1.setFechaVencimiento(LocalDate.of(2020,10,10));
		aux1.setCuotaPagada(false);
		aux1.setPrecioBase(precioBase);
		aux1.setPorcentajeInteres(0);
		aux1.setPorcentajeDescuento(0);
		aux1.setPrecioAPagar(precioBase);

		CuotaEntity aux2 = new CuotaEntity();
		aux2.setIdCuota(2L);
		aux2.setRutAlumno("12.345.678-2");
		aux2.setFechaVencimiento(LocalDate.of(2020,11,10));
		aux2.setCuotaPagada(false);
		aux2.setPrecioBase(precioBase);
		aux2.setPorcentajeInteres(0);
		aux2.setPorcentajeDescuento(0);
		aux2.setPrecioAPagar(precioBase);

		esperados.add(aux1);
		esperados.add(aux2);

		ArrayList<CuotaEntity> cuotas = cuotaService.obtenerCuotas();
		assertEquals(esperados, cuotas);
	}

	//----------------------------------------------------------------------------------------------------------
	//buscarCuotaPorRut

	@Test
	@Transactional
	void buscarCuotaPorRut(){
		int precioBase = 1500000/4;
		cuota.setRutAlumno("12.345.678-2");
		cuota.setFechaVencimiento(LocalDate.of(2020,10,10));
		cuota.setCuotaPagada(false);
		cuota.setPrecioBase(precioBase);
		cuota.setPorcentajeInteres(0);
		cuota.setPorcentajeDescuento(0);
		cuota.setPrecioAPagar(precioBase);
		cuotaService.crearCuota(cuota);

		CuotaEntity cuota2 = new CuotaEntity();
		cuota2.setRutAlumno("12.345.678-2");
		cuota2.setFechaVencimiento(LocalDate.of(2020,11,10));
		cuota2.setCuotaPagada(false);
		cuota2.setPrecioBase(precioBase);
		cuota2.setPorcentajeInteres(0);
		cuota2.setPorcentajeDescuento(0);
		cuota2.setPrecioAPagar(precioBase);
		cuotaService.crearCuota(cuota2);

		ArrayList<CuotaEntity> esperados = new ArrayList<>();

		CuotaEntity aux1 = new CuotaEntity();
		aux1.setIdCuota(4L);
		aux1.setRutAlumno("12.345.678-2");
		aux1.setFechaVencimiento(LocalDate.of(2020,10,10));
		aux1.setCuotaPagada(false);
		aux1.setPrecioBase(precioBase);
		aux1.setPorcentajeInteres(0);
		aux1.setPorcentajeDescuento(0);
		aux1.setPrecioAPagar(precioBase);

		CuotaEntity aux2 = new CuotaEntity();
		aux2.setIdCuota(5L);
		aux2.setRutAlumno("12.345.678-2");
		aux2.setFechaVencimiento(LocalDate.of(2020,11,10));
		aux2.setCuotaPagada(false);
		aux2.setPrecioBase(precioBase);
		aux2.setPorcentajeInteres(0);
		aux2.setPorcentajeDescuento(0);
		aux2.setPrecioAPagar(precioBase);

		esperados.add(aux1);
		esperados.add(aux2);

		ArrayList<CuotaEntity> cuotas = cuotaService.findByRutAlumno("12.345.678-2");
		assertEquals(esperados, cuotas);

	}
}
