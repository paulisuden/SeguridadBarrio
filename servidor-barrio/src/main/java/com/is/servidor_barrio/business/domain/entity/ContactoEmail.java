package com.is.servidor_barrio.business.domain.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactoEmail extends Contacto {
  @Email(message = "Debe contener formato mail.")
  private String email;
}
