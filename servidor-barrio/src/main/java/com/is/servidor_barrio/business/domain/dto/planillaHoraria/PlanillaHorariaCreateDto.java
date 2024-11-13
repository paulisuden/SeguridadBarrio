package com.is.servidor_barrio.business.domain.dto.planillaHoraria;

import java.time.LocalDateTime;

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
  private LocalDateTime entrada;
  private LocalDateTime salida;
  private EstadoAsistencia estadoAsistencia;
  private String observacionAsistencia;
  private String empleadoId;

}
