package com.is.servidor_barrio.business.domain.dto.usuario;

import com.is.servidor_barrio.business.domain.enumeration.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioCreateDTO {
    private String email;
    private String clave;
    private Rol rol;
    private String imagenId;
}
