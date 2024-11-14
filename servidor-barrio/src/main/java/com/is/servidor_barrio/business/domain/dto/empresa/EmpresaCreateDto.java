package com.is.servidor_barrio.business.domain.dto.empresa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaCreateDto {
  private String nombre;
  private String descripcion;
  private String imagenId;
  private String direccionId;
}
