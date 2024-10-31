package com.is.servidor_barrio.business.domain.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
  private String nombre;
  private String apellido;
  private String correo;
  private String telefono;

  @ManyToOne
  private Usuario usuario;

  @OneToMany
  private List<Contacto> contactos;

}
