package com.is.servidor_barrio.business.domain.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Persona extends Base {
  @NotEmpty(message = "Debe indicar el nombre")
  private String nombre;
  @NotEmpty(message = "Debe indicar el apellido")
  private String apellido;
  @ManyToOne
  private Usuario usuario;
  @ManyToMany
  private List<Contacto> contactos;
}
