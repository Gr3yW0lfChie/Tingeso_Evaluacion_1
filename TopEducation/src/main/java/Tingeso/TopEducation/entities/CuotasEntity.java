package Tingeso.TopEducation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "cuotas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuotasEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long idCuota;

	@ManyToOne
	@JoinColumn(name = "id_Arancel", referencedColumnName = "id_arancel")
	private ArancelEntity arancel;

	@Column(name = "fecha_vencimiento")
	private LocalDate fechaVencimiento;

	@Column(name = "cuota_pagada")
	private Boolean cuotaPagada;

	@Column(name = "precio_base")
	private Integer precioBase;

	@Column(name = "porcentaje_interes")
	private Integer porcentajeInteres;

	@Column(name = "porcentaje_descuento")
	private Integer porcentajeDescuento
}
