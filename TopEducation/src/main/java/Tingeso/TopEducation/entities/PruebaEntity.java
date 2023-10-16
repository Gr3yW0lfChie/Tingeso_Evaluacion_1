package Tingeso.TopEducation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "prueba")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PruebaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pruebas", unique = true, nullable = false)
	private Long idPruebas;

	//Hace la relacion con la entidad alumno
	@Column(name = "rut_alumno")
	private String alumno;

	@Column(name = "fecha_examen")
	private LocalDate fechaExamen;

	@Column(name = "puntaje")
	private Integer puntaje;
}
