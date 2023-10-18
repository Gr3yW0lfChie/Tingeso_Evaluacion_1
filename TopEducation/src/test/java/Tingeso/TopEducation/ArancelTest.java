package Tingeso.TopEducation;

import Tingeso.TopEducation.entities.ArancelEntity;
import Tingeso.TopEducation.services.ArancelService;
import Tingeso.TopEducation.services.CuotaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ArancelTest {


	@Autowired
	private ArancelService arancelService;

	@Autowired
	private CuotaService cuotaService;

	@Test
	public void crearCuotas1(){
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
