package com.is.servidor_barrio.business.domain.entity;

import java.sql.Date;
import java.util.List;

import com.is.servidor_barrio.business.enumeration.EstadoAsistencia;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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

  private Date entrada;
  private Date salida;
  private EstadoAsistencia estadoAsistencia;
  private String observacionAsistencia;

  @OneToMany
  private List<Empleado> empleados;

}
