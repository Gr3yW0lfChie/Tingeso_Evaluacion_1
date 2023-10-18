package Tingeso.TopEducation;

import Tingeso.TopEducation.entities.AlumnoEntity;
import Tingeso.TopEducation.repositories.AlumnoRepository;
import Tingeso.TopEducation.services.AlumnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

@SpringBootTest
public class AlumnoTest {

	AlumnoEntity alumnoEntity = new AlumnoEntity();

	@Autowired
	private AlumnoRepository alumnoRepository;

	@Autowired
	private AlumnoService alumnoService;


	@Test
	void contextLoads() {
	}
	//----------------------------------------------------------------------------------------------------------
	//crearAlumno

	@Test
	void testCrearAlumno() {
		String rut = "27.134.678-6";
		alumnoService.crearAlumno(rut, "Perez", "Juan", LocalDate.of(1998, 10, 10), "Municipal", "Colegio 1", LocalDate.of(2016, 10, 10));
		AlumnoEntity alumnoCreado = alumnoService.obtenerAlumnoPorRut(rut);
		assertEquals("27.134.678-6", alumnoCreado.getRut());
	}


	@Test
	void testBuscarAlumno() {
		alumnoEntity.setRut("27.134.678-6");
		alumnoEntity.setApellidos("Perez");
		alumnoEntity.setNombres("Juan");
		alumnoEntity.setFechaNacimiento(LocalDate.of(1998, 10, 10));
		alumnoEntity.setTipoColegio("Municipal");
		alumnoEntity.setNombreColegio("Colegio 1");
		alumnoEntity.setFechaEgreso(LocalDate.of(2016, 10, 10));
		alumnoRepository.save(alumnoEntity);
		AlumnoEntity alumnoCreado = alumnoService.obtenerAlumnoPorRut(alumnoEntity.getRut());
		assertEquals("27.134.678-6", alumnoCreado.getRut());
	}


	//----------------------------------------------------------------------------------------------------------
	//obtenerDescuentoPorTipoColegio
	@Test
	void obtenerDescuentoPorTipoColegioMunicipal(){
		alumnoEntity.setRut("27.134.678-6");
		alumnoEntity.setApellidos("Perez");
		alumnoEntity.setNombres("Juan");
		alumnoEntity.setFechaNacimiento(LocalDate.of(1998, 10, 10));
		alumnoEntity.setTipoColegio("Municipal");
		alumnoEntity.setNombreColegio("Colegio 1");
		alumnoEntity.setFechaEgreso(LocalDate.of(2016, 10, 10));

		int descuento = alumnoService.obtenerDescuentoPorTipoColegio(alumnoEntity.getTipoColegio());
		assertEquals(20, descuento, 0.0);
	}

	@Test
	void obtenerDescuentoPorTipoColegioSubvencionado(){
		alumnoEntity.setRut("27.134.678-6");
		alumnoEntity.setApellidos("Perez");
		alumnoEntity.setNombres("Juan");
		alumnoEntity.setFechaNacimiento(LocalDate.of(1998, 10, 10));
		alumnoEntity.setTipoColegio("Subvencionado");
		alumnoEntity.setNombreColegio("Colegio 1");
		alumnoEntity.setFechaEgreso(LocalDate.of(2016, 10, 10));

		int descuento = alumnoService.obtenerDescuentoPorTipoColegio(alumnoEntity.getTipoColegio());
		assertEquals(10, descuento, 0.0);
	}

	@Test
	void obtenerDescuentoPorTipoColegioParticular(){
		alumnoEntity.setRut("27.134.678-6");
		alumnoEntity.setApellidos("Perez");
		alumnoEntity.setNombres("Juan");
		alumnoEntity.setFechaNacimiento(LocalDate.of(1998, 10, 10));
		alumnoEntity.setTipoColegio("Particular");
		alumnoEntity.setNombreColegio("Colegio 1");
		alumnoEntity.setFechaEgreso(LocalDate.of(2016, 10, 10));

		int descuento = alumnoService.obtenerDescuentoPorTipoColegio(alumnoEntity.getTipoColegio());
		assertEquals(0, descuento, 0.0);
	}

	//----------------------------------------------------------------------------------------------------------
	//obtenerDescuentoPorTiempoSalidaColegio
	@Test
	void obtenerDescuentoPorTiempoSalidaColegioMenosDe1Anio(){
		alumnoEntity.setRut("27.134.678-6");
		alumnoEntity.setApellidos("Perez");
		alumnoEntity.setNombres("Juan");
		alumnoEntity.setFechaNacimiento(LocalDate.of(1998, 10, 10));
		alumnoEntity.setTipoColegio("Particular");
		alumnoEntity.setNombreColegio("Colegio 1");
		alumnoEntity.setFechaEgreso(LocalDate.of(2023, 10, 10));

		int descuento = alumnoService.obtenerDescuentoPorTiempoSalidaColegio(alumnoEntity.getFechaEgreso());
		assertEquals(15, descuento, 0.0);
	}

	@Test
	void obtenerDescuentoPorTiempoSalidaColegioMenosDe3Anios(){
		alumnoEntity.setRut("27.134.678-6");
		alumnoEntity.setApellidos("Perez");
		alumnoEntity.setNombres("Juan");
		alumnoEntity.setFechaNacimiento(LocalDate.of(1998, 10, 10));
		alumnoEntity.setTipoColegio("Particular");
		alumnoEntity.setNombreColegio("Colegio 1");
		alumnoEntity.setFechaEgreso(LocalDate.of(2021, 10, 10));

		int descuento = alumnoService.obtenerDescuentoPorTiempoSalidaColegio(alumnoEntity.getFechaEgreso());
		assertEquals(8, descuento, 0.0);
	}

	@Test
	void obtenerDescuentoPorTiempoSalidaColegioMenosDe5Anios(){
		alumnoEntity.setRut("27.134.678-6");
		alumnoEntity.setApellidos("Perez");
		alumnoEntity.setNombres("Juan");
		alumnoEntity.setFechaNacimiento(LocalDate.of(1998, 10, 10));
		alumnoEntity.setTipoColegio("Particular");
		alumnoEntity.setNombreColegio("Colegio 1");
		alumnoEntity.setFechaEgreso(LocalDate.of(2019, 10, 10));

		int descuento = alumnoService.obtenerDescuentoPorTiempoSalidaColegio(alumnoEntity.getFechaEgreso());
		assertEquals(4, descuento, 0.0);
	}

	@Test
	void obtenerDescuentoPorTiempoSalidaColegioMasDe5Anios(){
		alumnoEntity.setRut("27.134.678-6");
		alumnoEntity.setApellidos("Perez");
		alumnoEntity.setNombres("Juan");
		alumnoEntity.setFechaNacimiento(LocalDate.of(1998, 10, 10));
		alumnoEntity.setTipoColegio("Particular");
		alumnoEntity.setNombreColegio("Colegio 1");
		alumnoEntity.setFechaEgreso(LocalDate.of(2016, 10, 10));

		int descuento = alumnoService.obtenerDescuentoPorTiempoSalidaColegio(alumnoEntity.getFechaEgreso());
		assertEquals(0, descuento, 0.0);
	}

	//----------------------------------------------------------------------------------------------------------
	//obtenerCantidadCuotas

	@Test
	void obtenerCantidadCuotasMunicipal(){
		alumnoEntity.setRut("27.134.678-6");
		alumnoEntity.setApellidos("Perez");
		alumnoEntity.setNombres("Juan");
		alumnoEntity.setFechaNacimiento(LocalDate.of(1998, 10, 10));
		alumnoEntity.setTipoColegio("Municipal");
		alumnoEntity.setNombreColegio("Colegio 1");
		alumnoEntity.setFechaEgreso(LocalDate.of(2016, 10, 10));

		int cuotas = alumnoService.obtenerCantidadCuotas(alumnoEntity.getTipoColegio());
		assertEquals(10, cuotas, 0.0);
	}

	@Test
	void obtenerCantidadCuotasSubvencionado(){
		alumnoEntity.setRut("27.134.678-6");
		alumnoEntity.setApellidos("Perez");
		alumnoEntity.setNombres("Juan");
		alumnoEntity.setFechaNacimiento(LocalDate.of(1998, 10, 10));
		alumnoEntity.setTipoColegio("Subvencionado");
		alumnoEntity.setNombreColegio("Colegio 1");
		alumnoEntity.setFechaEgreso(LocalDate.of(2016, 10, 10));

		int cuotas = alumnoService.obtenerCantidadCuotas(alumnoEntity.getTipoColegio());
		assertEquals(7, cuotas, 0.0);
	}

	@Test
	void obtenerCantidadCuotasParticular(){
		alumnoEntity.setRut("27.134.678-6");
		alumnoEntity.setApellidos("Perez");
		alumnoEntity.setNombres("Juan");
		alumnoEntity.setFechaNacimiento(LocalDate.of(1998, 10, 10));
		alumnoEntity.setTipoColegio("Particular");
		alumnoEntity.setNombreColegio("Colegio 1");
		alumnoEntity.setFechaEgreso(LocalDate.of(2016, 10, 10));

		int cuotas = alumnoService.obtenerCantidadCuotas(alumnoEntity.getTipoColegio());
		assertEquals(4, cuotas, 0.0);
	}

	@Test
	void obtenerCantidadCuotasError(){
		alumnoEntity.setRut("27.134.678-6");
		alumnoEntity.setApellidos("Perez");
		alumnoEntity.setNombres("Juan");
		alumnoEntity.setFechaNacimiento(LocalDate.of(1998, 10, 10));
		alumnoEntity.setTipoColegio("Otro");
		alumnoEntity.setNombreColegio("Colegio 1");
		alumnoEntity.setFechaEgreso(LocalDate.of(2016, 10, 10));

		int cuotas = alumnoService.obtenerCantidadCuotas(alumnoEntity.getTipoColegio());
		assertEquals(-1, cuotas, 0.0);
	}
}

