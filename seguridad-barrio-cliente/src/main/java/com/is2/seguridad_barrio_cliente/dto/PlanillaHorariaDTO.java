package com.is2.seguridad_barrio_cliente.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.is2.seguridad_barrio_cliente.enumeration.EstadoAsistencia;

import lombok.Data;

@Data
public class PlanillaHorariaDTO {
    private String id;

    private LocalDateTime entrada;
    private LocalDateTime salida;
    private EstadoAsistencia estadoAsistencia;
    private String observacionAsistencia;
    private PersonaDTO empleado;
    private String empleadoId;



    public String getEntradaAsString() {
        return entrada != null ? entrada.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : "";
    }

    public String getSalidaAsString() {
        return salida != null ? salida.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : "";
    }


}
