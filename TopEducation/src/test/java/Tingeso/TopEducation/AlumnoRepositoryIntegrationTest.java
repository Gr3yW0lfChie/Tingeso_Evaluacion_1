package Tingeso.TopEducation;

import Tingeso.TopEducation.entities.AlumnoEntity;
import Tingeso.TopEducation.repositories.AlumnoRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.Objects;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AlumnoRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private AlumnoRepository alumnoRepository;

	@Test
	public void crearAlumno(){

		AlumnoEntity alumno = new AlumnoEntity();

		alumno.setRut("27.134.678-6");
		alumno.setApellidos("Perez");
		alumno.setNombres("Juan");
		alumno.setFechaNacimiento(LocalDate.of(1998, 10, 10));
		alumno.setTipoColegio("Municipal");
		alumno.setNombreColegio("Colegio 1");
		alumno.setFechaEgreso(LocalDate.of(2016, 10, 10));

		entityManager.persistAndFlush(alumno);

		alumnoRepository.save(alumno);

		assert Objects.equals(alumno.getRut(), "27.134.678-6");
	}

	@Test
	public void eliminarAlumno(){

		AlumnoEntity alumno = new AlumnoEntity();

		alumno.setRut("27.134.678-6");
		alumno.setApellidos("Perez");
		alumno.setNombres("Juan");
		alumno.setFechaNacimiento(LocalDate.of(1998, 10, 10));
		alumno.setTipoColegio("Municipal");
		alumno.setNombreColegio("Colegio 1");
		alumno.setFechaEgreso(LocalDate.of(2016, 10, 10));

		entityManager.persistAndFlush(alumno);

		alumnoRepository.deleteByRut(alumno.getRut());

		assert alumnoRepository.findByRut(alumno.getRut()) == null;
	}


	@Test
	public void buscarAlumno(){

		AlumnoEntity alumno = new AlumnoEntity();
		alumno.setRut("27.134.678-6");
		alumno.setApellidos("Perez");
		alumno.setNombres("Juan");
		alumno.setFechaNacimiento(LocalDate.of(1998, 10, 10));
		alumno.setTipoColegio("Municipal");
		alumno.setNombreColegio("Colegio 1");
		alumno.setFechaEgreso(LocalDate.of(2016, 10, 10));

		entityManager.persistAndFlush(alumno);

		alumnoRepository.save(alumno);

		assert Objects.equals(alumnoRepository.findByRut(alumno.getRut()).getRut(), "27.134.678-6");
	}
}
