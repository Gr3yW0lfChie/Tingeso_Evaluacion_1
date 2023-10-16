package Tingeso.TopEducation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "cuota")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuotaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long idCuota;

	//Hace la relacion con la entidad arancel
	@Column(name = "id_arancel")
	private Long idArancel;

	@Column(name = "fecha_vencimiento")
	private LocalDate fechaVencimiento;

	@Column(name = "cuota_pagada")
	private Boolean cuotaPagada;

	@Column(name = "precio_base")
	private Integer precioBase;

	@Column(name = "porcentaje_interes")
	private Integer porcentajeInteres;

	@Column(name = "porcentaje_descuento")
	private Integer porcentajeDescuento;

	@Column(name = "precio_a_pagar")
	private Integer precioAPagar;


}
