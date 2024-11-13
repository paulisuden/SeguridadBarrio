package com.is.servidor_barrio.business.domain.dto.unidadDeNegocio;

import java.util.List;

import com.is.servidor_barrio.business.domain.dto.BaseDto;
import com.is.servidor_barrio.business.domain.dto.direccion.DireccionDto;
import com.is.servidor_barrio.business.domain.dto.imagen.ImagenDto;
import com.is.servidor_barrio.business.domain.dto.servicio.ServicioDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UnidadDeNegocioDto extends BaseDto {
  private String nombre;
  private DireccionDto direccion;
  private List<ServicioDto> servicios;
  private Long imagenId;
  private ImagenDto imagen;
}
