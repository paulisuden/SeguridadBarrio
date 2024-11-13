package com.is.servidor_barrio.business.domain.dto.servicio;

import com.is.servidor_barrio.business.domain.dto.BaseDto;
import com.is.servidor_barrio.business.domain.dto.imagen.ImagenDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServicioDto extends BaseDto {
  private String nombre;
  private String imagenId;
  private ImagenDto imagen;
}
