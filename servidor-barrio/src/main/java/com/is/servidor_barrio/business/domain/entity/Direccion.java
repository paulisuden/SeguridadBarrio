package com.is.servidor_barrio.business.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion implements Serializable {
  
    @Id
    private String id;
    private String calle;
    private String numeracion;
    private String barrio;
    private String pisoCasa;
    private String puertaManzana;
    private String ubicacionCoordenadaX;
    private String ubicacionCoordenadaY;
    @Column(length = 500)
    private String observacion;
    @ManyToOne
    private Localidad localidad;
    private boolean eliminado;


}
