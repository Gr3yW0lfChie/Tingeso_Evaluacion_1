package Tingeso.TopEducation.repositories;

import Tingeso.TopEducation.entities.CuotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CuotaRepository extends JpaRepository<CuotaEntity, Long> {

	@Query("SELECT cuota FROM CuotaEntity cuota WHERE cuota.rutAlumno = :rut")
	ArrayList<CuotaEntity> findByRutAlumno(@Param("rut") String rut);
}
