package com.is.servidor_barrio.business.domain.dto.planillaHoraria;

import java.time.LocalDateTime;

import com.is.servidor_barrio.business.domain.dto.BaseDto;
import com.is.servidor_barrio.business.domain.dto.persona.PersonaDto;
//import com.is.servidor_barrio.business.domain.dto.empleado.EmpleadoDto;
import com.is.servidor_barrio.business.domain.enumeration.EstadoAsistencia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlanillaHorariaDto extends BaseDto {
  private LocalDateTime entrada;
  private LocalDateTime salida;
  private EstadoAsistencia estadoAsistencia;
  private String observacionAsistencia;
  private PersonaDto empleado;
  private Long empleadoId;
}
