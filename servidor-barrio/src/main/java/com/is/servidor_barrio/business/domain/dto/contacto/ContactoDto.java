package com.is.servidor_barrio.business.domain.dto.contacto;

import com.is.servidor_barrio.business.domain.dto.BaseDto;
import com.is.servidor_barrio.business.domain.enumeration.TipoContacto;
import com.is.servidor_barrio.business.domain.enumeration.TipoTelefono;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContactoDto extends BaseDto {
    private TipoContacto tipoContacto;
    private String observacion;
    private String email;
    private String telefono;
    private TipoTelefono tipoTelefono;
}
