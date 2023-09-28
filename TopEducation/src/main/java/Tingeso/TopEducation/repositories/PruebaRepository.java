package Tingeso.TopEducation.repositories;

import Tingeso.TopEducation.entities.PruebaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PruebaRepository extends JpaRepository<PruebaEntity, Long> {
}
