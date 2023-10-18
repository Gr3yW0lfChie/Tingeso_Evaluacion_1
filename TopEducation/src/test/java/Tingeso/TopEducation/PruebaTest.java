package Tingeso.TopEducation;

import Tingeso.TopEducation.entities.PruebaEntity;
import Tingeso.TopEducation.services.PruebaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PruebaTest {

	PruebaEntity prueba = new PruebaEntity();

	@Autowired
	PruebaService pruebaService;

	//----------------------------------------------------------------------------------------------------------
	//guardarPruebaDB

	@Test
	@Transactional
	void guardarPruebaDB(){
		pruebaService.guardarPruebaDB("12.345.678-2", LocalDate.of(2020,10,10), 700);

		Optional<PruebaEntity> pruebaGuardada = pruebaService.obtenerPruebaPorId(1L);
		pruebaGuardada.ifPresent(pruebaEntity -> assertEquals("12.345.678-2", pruebaEntity.getAlumno()));
	}

	//----------------------------------------------------------------------------------------------------------
	//ListarPruebas

	@Test
	@Transactional
	void buscarListaPruebas(){
		pruebaService.guardarPruebaDB("12.345.678-2", LocalDate.of(2020,10,10), 700);
		pruebaService.guardarPruebaDB("75.123.636-7", LocalDate.of(2020,10,10), 950);

		ArrayList<PruebaEntity> esperados = new ArrayList<>();
		PruebaEntity aux1 = new PruebaEntity();
		aux1.setIdPruebas(2L);
		aux1.setAlumno("12.345.678-2");
		aux1.setFechaExamen(LocalDate.of(2020,10,10));
		aux1.setPuntaje(700);

		PruebaEntity aux2 = new PruebaEntity();
		aux2.setIdPruebas(3L);
		aux2.setAlumno("75.123.636-7");
		aux2.setFechaExamen(LocalDate.of(2020,10,10));
		aux2.setPuntaje(950);

		esperados.add(aux1);
		esperados.add(aux2);

		ArrayList<PruebaEntity> obtenidos = pruebaService.obtenerPruebas();
		assertEquals(esperados, obtenidos);
	}
}
