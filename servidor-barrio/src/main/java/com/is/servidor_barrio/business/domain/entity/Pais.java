package com.is.servidor_barrio.business.domain.entity;

import jakarta.persistence.Entity;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pais extends Base {

    private String nombre;
    private boolean eliminado = false;
}
