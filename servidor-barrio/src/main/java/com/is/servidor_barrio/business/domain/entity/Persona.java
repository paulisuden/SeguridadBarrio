package com.is.servidor_barrio.business.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
public class Persona extends Base {
  private String nombre;
  private String apellido;
  private String correo;
  private String telefono;

  @ManyToOne
  private Usuario usuario;

  @OneToMany
  private Contacto contacto;

}
