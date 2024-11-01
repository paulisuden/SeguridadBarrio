package com.is.servidor_barrio.business.domain.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Servicio extends Base {
  private String nombre;
  @OneToMany
  private List<Imagen> imagenes;
}