package com.is2.seguridad_barrio_cliente.dto;

import com.is2.seguridad_barrio_cliente.enumeration.TipoContacto;
import com.is2.seguridad_barrio_cliente.enumeration.TipoTelefono;

import lombok.Data;

import java.io.Serializable;

@Data
public class ContactoDTO implements Serializable {
    private String id;

    private TipoContacto tipoContacto;
    private String observacion;
    private String email;
    private String telefono;
    private TipoTelefono tipoTelefono;
}
