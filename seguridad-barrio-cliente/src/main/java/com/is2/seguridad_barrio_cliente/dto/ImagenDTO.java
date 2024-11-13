package com.is2.seguridad_barrio_cliente.dto;

import lombok.Data;

@Data
public class ImagenDTO {
    private String id;
    private String name;
    private String mime;
    private byte[] contenido;
}
