package com.is.servidor_barrio.business.domain.dto.planillaHoraria;

import java.time.LocalDateTime;

import com.is.servidor_barrio.business.domain.enumeration.EstadoAsistencia;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanillaHorariaCreateDto {
  //@NotEmpty(message = "La entrada no puede ser null.")
  //@FutureOrPresent(message = "La entrada debe ser en el presente o futuro.")
  private LocalDateTime entrada;

  //@NotEmpty(message = "La salida no puede ser null.")
  //@PastOrPresent(message = "La salida debe ser en el presente o pasado.")
  private LocalDateTime salida;
  //@NotEmpty(message = "El estado de asistencia no puede ser vacio.")
  private EstadoAsistencia estadoAsistencia;
  private String observacionAsistencia;
  private String empleadoId;

}
