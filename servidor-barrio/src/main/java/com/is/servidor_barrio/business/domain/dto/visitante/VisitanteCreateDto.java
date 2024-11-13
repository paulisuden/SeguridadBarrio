package com.is.servidor_barrio.business.domain.dto.visitante;

import com.is.servidor_barrio.business.domain.enumeration.TipoVisita;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitanteCreateDto {
  private String nombre;
  private String apellido;
  private String numeroDeDocumento;
  private TipoVisita tipoVisita;
}
