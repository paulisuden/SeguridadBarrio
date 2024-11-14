package com.is.servidor_barrio.business.domain.dto.usuario;

import com.is.servidor_barrio.business.domain.dto.BaseDto;
import com.is.servidor_barrio.business.domain.dto.imagen.ImagenDto;
import com.is.servidor_barrio.business.domain.enumeration.Rol;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioDTO extends BaseDto {
    private String email;
    private String clave;
    private Rol rol;
    private ImagenDto imagen;

}
