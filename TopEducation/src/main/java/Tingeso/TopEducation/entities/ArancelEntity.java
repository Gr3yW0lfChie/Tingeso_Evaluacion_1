package Tingeso.TopEducation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "arancel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArancelEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_arancel", unique = true, nullable = false)
	private Long idArancel;

	//Hace la relación con la entidad alumno
	@Column(name = "rut_alumno")
	private String rutAlumno;

	@Column(name = "matricula")
	private Integer matricula;

	@Column(name = "matricula_pagada")
	private Boolean matriculaPagada;

	@Column(name = "arancel_base")
	private Integer arancelBase;

	@Column(name = "cantidad_cuotas")
	private Integer cantidadCuotas;

}
