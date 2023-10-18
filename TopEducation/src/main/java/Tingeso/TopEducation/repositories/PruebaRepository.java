package Tingeso.TopEducation.repositories;

import Tingeso.TopEducation.entities.PruebaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PruebaRepository extends JpaRepository<PruebaEntity, Long> {


	@Query("SELECT prueba FROM PruebaEntity prueba WHERE prueba.alumno = :rut")
	ArrayList<PruebaEntity> findByAlumno(@Param("rut") String rut);
}
