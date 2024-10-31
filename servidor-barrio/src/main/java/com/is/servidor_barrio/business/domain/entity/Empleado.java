package com.is.servidor_barrio.business.domain.entity;

import com.is.servidor_barrio.business.enumeration.TipoEmpleado;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empleado extends Persona {
  private String legajo;
  private TipoEmpleado tipoEmpleado;
}
