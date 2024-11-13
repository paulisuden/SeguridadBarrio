package com.is.servidor_barrio.business.domain.dto.unidadDeNegocio;

import java.util.List;

import com.is.servidor_barrio.business.domain.dto.imagen.ImagenDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnidadDeNegocioCreateDto {
  private String nombre;
  private Long direccionId;
  private List<Long> serviciosId;
  private Long imagenId;
  private ImagenDto imagen;
}
