package com.is.servidor_barrio.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.is.servidor_barrio.business.domain.dto.BaseDto;
import com.is.servidor_barrio.business.domain.entity.Base;

import java.io.Serializable;

public interface BaseController<E extends Base, D extends BaseDto, DC, DE, ID extends Serializable> {
  public ResponseEntity<?> getAll();

  // el ? es lo mismo que extend object es un comodin
  public ResponseEntity<?> getOne(@PathVariable ID id);

  public ResponseEntity<?> save(@RequestBody D dtoD);

  public ResponseEntity<?> update(@PathVariable ID id, @RequestBody DE dtoDE);

  public ResponseEntity<?> delete(@PathVariable ID id);
}
