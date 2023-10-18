package Tingeso.TopEducation;

import Tingeso.TopEducation.entities.AlumnoEntity;
import Tingeso.TopEducation.services.AlumnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootTest
public class AlumnoTest {

	AlumnoEntity alumnoEntity = new AlumnoEntity();


	@Autowired
	private AlumnoService alumnoService;


	//----------------------------------------------------------------------------------------------------------
	//testModificarAlumno

	@Test
	@Transactional
	void testModificarAlumno() {
		String rut = "27.134.678-6";
		alumnoService.crearAlumno(rut, "Perez", "Juan", LocalDate.of(1998, 10, 10), "Municipal", "Colegio 1", LocalDate.of(2016, 10, 10));

		AlumnoEntity aux = new AlumnoEntity();
		aux.setRut("27.134.678-6");
		aux.setApellidos("Rodriguez");
		aux.setNombres("Alfonso");
		aux.setFechaNacimiento(LocalDate.of(2000, 5, 15));
		aux.setTipoColegio("Subvencionado");
		aux.setNombreColegio("Colegio 2");
		aux.setFechaEgreso(LocalDate.of(2022, 10, 20));
		alumnoService.actualizarAlumno("27.134.678-6", aux);

		assertEquals(aux, alumnoService.obtenerAlumnoPorRut(rut));
	}

	//----------------------------------------------------------------------------------------------------------
	//buscarListaAlumnos
	@Test
	@Transactional
	void buscarListaAlumnos() {
		alumnoService.crearAlumno("27.134.678-6", "Perez", "Juan", LocalDate.of(1998, 10, 10), "Municipal", "Colegio 1", LocalDate.of(2016, 10, 10));
		alumnoService.crearAlumno("12.345.678-9", "Rodriguez", "Alfonso", LocalDate.of(2000, 5, 15), "Subvencionado", "Colegio 2", LocalDate.of(2022, 10, 20));

		ArrayList<AlumnoEntity> esperados = new ArrayList<>();

		AlumnoEntity aux1 = new AlumnoEntity();
		aux1.setRut("27.134.678-6");
		aux1.setApellidos("Perez");
		aux1.setNombres("Juan");
		aux1.setFechaNacimiento(LocalDate.of(1998, 10, 10));
		aux1.setTipoColegio("Municipal");
		aux1.setNombreColegio("Colegio 1");
		aux1.setFechaEgreso(LocalDate.of(2016, 10, 10));

		AlumnoEntity aux2 = new AlumnoEntity();
		aux2.setRut("12.345.678-9");
		aux2.setApellidos("Rodriguez");
		aux2.setNombres("Alfonso");
		aux2.setFechaNacimiento(LocalDate.of(2000, 5, 15));
		aux2.setTipoColegio("Subvencionado");
		aux2.setNombreColegio("Colegio 2");
		aux2.setFechaEgreso(LocalDate.of(2022, 10, 20));

		esperados.add(aux1);
		esperados.add(aux2);

		ArrayList<AlumnoEntity> alumnos = alumnoService.obtenerAlumnos();
		assertEquals(esperados, alumnos);
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

	@Test
	void obtenerDescuentoPorTipoColegioError(){
		alumnoEntity.setRut("27.134.678-6");
		alumnoEntity.setApellidos("Perez");
		alumnoEntity.setNombres("Juan");
		alumnoEntity.setFechaNacimiento(LocalDate.of(1998, 10, 10));
		alumnoEntity.setTipoColegio("Otro");
		alumnoEntity.setNombreColegio("Colegio 1");
		alumnoEntity.setFechaEgreso(LocalDate.of(2016, 10, 10));

		int descuento = alumnoService.obtenerDescuentoPorTipoColegio(alumnoEntity.getTipoColegio());
		assertEquals(-1, descuento, 0.0);
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

