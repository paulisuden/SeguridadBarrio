package com.is.servidor_barrio.business.domain.entity;

import java.time.LocalDateTime;

import com.is.servidor_barrio.business.domain.enumeration.EstadoAsistencia;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanillaHoraria extends Base {

  private LocalDateTime entrada;
  private LocalDateTime salida;
  private EstadoAsistencia estadoAsistencia;
  private String observacionAsistencia;

  //@ManyToOne
  //private Empleado empleado;

}
