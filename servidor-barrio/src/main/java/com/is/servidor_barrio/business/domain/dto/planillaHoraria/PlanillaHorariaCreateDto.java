package com.is.servidor_barrio.business.domain.dto.planillaHoraria;

import java.util.Date;

import com.is.servidor_barrio.business.domain.enumeration.EstadoAsistencia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanillaHorariaCreateDto {
  private Date entrada;
  private Date salida;
  private EstadoAsistencia estadoAsistencia;
  private String observacionAsistencia;
  private Long empleadoId;
}
