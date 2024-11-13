package com.is.servidor_barrio.business.domain.dto.persona;

import com.is.servidor_barrio.business.domain.dto.BaseDto;
import com.is.servidor_barrio.business.domain.dto.unidadDeNegocio.UnidadDeNegocioDto;
import com.is.servidor_barrio.business.domain.entity.Inmueble;
import com.is.servidor_barrio.business.domain.enumeration.TipoEmpleado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDto extends BaseDto {
  private String nombre;
  private String apellido;
  // private Long usuarioId;
  private String legajo;
  private TipoEmpleado tipoEmpleado;
  private UnidadDeNegocioDto[] negocios;
  private Inmueble inmueble;
}
