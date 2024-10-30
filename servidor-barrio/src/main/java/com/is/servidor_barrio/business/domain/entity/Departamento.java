package com.is.servidor_barrio.business.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Entity
public class Departamento implements Serializable {
    @Id
    private String id;
    private String nombre;
    private boolean eliminado;
    @ManyToOne
    private Provincia provincia;
    
}

