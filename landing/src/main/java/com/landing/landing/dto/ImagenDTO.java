package com.landing.landing.dto;

import lombok.Data;

@Data
public class ImagenDTO {
    private String id;
    private String name;
    private String mime;
    private byte[] contenido;
}
