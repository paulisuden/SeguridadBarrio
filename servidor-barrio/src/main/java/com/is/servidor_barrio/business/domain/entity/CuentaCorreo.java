package com.is.servidor_barrio.business.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CuentaCorreo extends Base {

    private String correo;
    private String clave;
    private String puerto;
    private String smtp; //Simple Mail Transfer Protocol 
    /*SMTP protocolo estándar utilizado para el envío de correos electrónicos a través de 
    Internet. Es responsable de la transferencia de mensajes desde el cliente de correo 
    electrónico al servidor de correo saliente y entre servidores de correo.*/
    
    private boolean tls; //Transport Layer Security
    /*TLS protocolo de seguridad que encripta la comunicación entre el cliente y el servidor 
    de correo electrónico. 
    cuando tls = true, Indica si se debe utilizar una conexión segura mediante TLS*/

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Empresa empresa;

}
