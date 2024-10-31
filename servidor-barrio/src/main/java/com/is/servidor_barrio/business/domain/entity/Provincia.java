package com.is.servidor_barrio.business.domain.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Provincia extends Base {
    private String nombre;
    @ManyToOne
    private Pais pais;
}
