package com.is.servidor_barrio.business.domain.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pais extends Base {
    @NotEmpty(message = "Debe indicar el nombre")
    private String nombre;
}
