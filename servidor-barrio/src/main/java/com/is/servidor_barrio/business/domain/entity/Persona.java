package com.is.servidor_barrio.business.domain.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
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
public class Persona extends Base implements Serializable {

  private String nombre;
  private String apellido;
  @ManyToOne
  private Usuario usuario;
  @ManyToMany
  private List<Contacto> contactos;
}

