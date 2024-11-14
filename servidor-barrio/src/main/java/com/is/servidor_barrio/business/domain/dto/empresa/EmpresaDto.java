package com.is.servidor_barrio.business.domain.dto.empresa;

import com.is.servidor_barrio.business.domain.dto.BaseDto;
import com.is.servidor_barrio.business.domain.dto.direccion.DireccionDto;
import com.is.servidor_barrio.business.domain.dto.imagen.ImagenDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmpresaDto extends BaseDto {
  private String nombre;
  private String descripcion;
  private ImagenDto imagen;
  private DireccionDto direccion;
}
