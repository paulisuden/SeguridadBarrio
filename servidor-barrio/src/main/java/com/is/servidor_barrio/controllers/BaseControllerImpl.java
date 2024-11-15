package com.is.servidor_barrio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.is.servidor_barrio.business.domain.dto.BaseDto;
import com.is.servidor_barrio.business.domain.entity.Base;
import com.is.servidor_barrio.business.facade.BaseFacadeImpl;

import jakarta.validation.Valid;

public abstract class BaseControllerImpl<E extends Base, D extends BaseDto, DC, DE, F extends BaseFacadeImpl<E, D, DC, DE, String>>
    implements BaseController<E, D, DC, DE, String> {
  @Autowired
  protected F facade;

  @GetMapping("") // solicitudes HTTP GET

  // metodo que se ejecutará cuando se haga una solicitud GET a la ruta
  // especificada.
  public ResponseEntity<?> getAll() {
    try {
      // ResponseEntity contiene el status de la respuesta
      return ResponseEntity.status(HttpStatus.OK).body(facade.findAll());
    } catch (Exception e) {
      // formato de respuesta json
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente màs tarde.\"}");

    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getOne(@PathVariable String id) {
    // PathVariable: extraer valores de los parámetros de la URL en las solicitudes
    // HTTP
    try {
      // ResponseEntity contiene el status de la respuesta
      return ResponseEntity.status(HttpStatus.OK).body(facade.findById(id));
    } catch (Exception e) {
      // formato de respuesta json
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente màs tarde.\"}");

    }

  }

  @PostMapping("")
  //probar valid
  public ResponseEntity<?> save(@Valid @RequestBody DC dto) {
    try {
      System.out.println("ENTRO A CONTOLLER SERVIDOR " + dto.getClass());
      return ResponseEntity.status(HttpStatus.OK).body(facade.save(dto));
    } catch (Exception e) {
      // formato de respuesta json
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente màs tarde.\"}");
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable String id, @RequestBody DE dto) {
    try {
      // ResponseEntity contiene el status de la respuesta
      return ResponseEntity.status(HttpStatus.OK).body(facade.update(id, dto));
    } catch (Exception e) {
      // formato de respuesta json
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente màs tarde.\"}");
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable String id) {
    try {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(facade.delete(id));
    } catch (Exception e) {
      // formato de respuesta json
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente màs tarde.\"}");
    }
  }
}
