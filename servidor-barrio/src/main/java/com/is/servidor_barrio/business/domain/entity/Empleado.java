package com.is.servidor_barrio.business.domain.entity;

import java.util.List;

import com.is.servidor_barrio.business.domain.enumeration.TipoEmpleado;

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
public class Empleado extends Persona {
  private String legajo;
  private TipoEmpleado tipoEmpleado;
  @OneToMany
  private List<UnidadDeNegocio> negocios;
}
