package com.is.servidor_barrio.business.domain.entity;

import java.util.List;

import com.is.servidor_barrio.business.domain.enumeration.TipoEmpleado;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
  @NotBlank
  @NotEmpty
  @Digits(integer = 8, fraction = 0, message = "El campo debe contener hasta 8 dígitos enteros.")
  private String legajo;
  @NotNull
  private TipoEmpleado tipoEmpleado;
  @ManyToMany
  private List<UnidadDeNegocio> negocios;
}
