package Tingeso.TopEducation;

import Tingeso.TopEducation.entities.AlumnoEntity;
import Tingeso.TopEducation.entities.ArancelEntity;
import Tingeso.TopEducation.services.ArancelService;
import Tingeso.TopEducation.services.CuotaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.util.ClassUtils.isPresent;

@SpringBootTest
public class ArancelTest {


	@Autowired
	private ArancelService arancelService;

	@Autowired
	private CuotaService cuotaService;


	//----------------------------------------------------------------------------------------------------------
	//modificarArancel
	@Test
	void modificarArancel() {

		String rut = "12.345.678-2";

		arancelService.crearArancel(rut, 70000, false, 1500000, 0);

		arancelService.modificarArancel(rut, 70000, true, 1500000, 4);

		assertEquals(4, arancelService.obtenerArancelPorRut(rut).getCantidadCuotas());
	}

	//----------------------------------------------------------------------------------------------------------
	//listarCuotas

	@Test
	@Transactional
	void buscarlistaCuotas(){
		arancelService.crearArancel("12.345.678-2", 70000, true, 1500000, 1);
		arancelService.crearArancel("12.123.123-4", 70000, false, 1500000, 0);

		ArrayList<ArancelEntity> esperados = new ArrayList<>();

		ArancelEntity arancel = new ArancelEntity();
		arancel.setIdArancel(1L);
		arancel.setRutAlumno("12.345.678-2");
		arancel.setMatricula(70000);
		arancel.setMatriculaPagada(true);
		arancel.setArancelBase(1500000);
		arancel.setCantidadCuotas(1);

		ArancelEntity arancel2 = new ArancelEntity();
		arancel2.setIdArancel(2L);
		arancel2.setRutAlumno("12.123.123-4");
		arancel2.setMatricula(70000);
		arancel2.setMatriculaPagada(false);
		arancel2.setArancelBase(1500000);
		arancel2.setCantidadCuotas(0);

		esperados.add(arancel);
		esperados.add(arancel2);

		ArrayList<ArancelEntity> aranceles = arancelService.obtenerAranceles();

		assertEquals(esperados, aranceles);
	}

	@Test
	@Transactional
	void buscarCuotaPorId(){
		arancelService.crearArancel("12.345.678-2", 70000, true, 1500000, 1);

		Long id = arancelService.obtenerArancelPorRut("12.345.678-2").getIdArancel();

		Optional<ArancelEntity> arancel2 = arancelService.obtenerArancelPorId(id);

		if (arancel2.isPresent()) {
			Long idArancel2 = arancel2.get().getIdArancel();

			assertEquals(id, idArancel2);
		} else {
			fail();
		}

	}
	//----------------------------------------------------------------------------------------------------------
	//crearCuotas
	@Test
	@Transactional
	void crearCuotas1(){
		ArancelEntity arancel = new ArancelEntity();

		arancel.setRutAlumno("12.345.678-2");
		arancel.setMatricula(70000);
		arancel.setMatriculaPagada(true);
		arancel.setArancelBase(1500000);
		arancel.setCantidadCuotas(1);



		arancelService.crearCuotas(arancel);

		int cuotas = cuotaService.findByRutAlumno("12.345.678-2").size();

		assertEquals(1, cuotas);

	}

	@Test
	@Transactional
	void crearCuotas10(){
		ArancelEntity arancel = new ArancelEntity();

		arancel.setRutAlumno("12.345.678-2");
		arancel.setMatricula(70000);
		arancel.setMatriculaPagada(true);
		arancel.setArancelBase(1500000);
		arancel.setCantidadCuotas(10);

		arancelService.crearCuotas(arancel);

		int cuotas = cuotaService.findByRutAlumno("12.345.678-2").size();

		assertEquals(10, cuotas);
	}


}
