package com.is.servidor_barrio.business.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.entity.ContactoEmail;
import com.is.servidor_barrio.business.repository.BaseRepository;

@Service
public class ContactoEmailServiceImpl extends BaseServiceImpl<ContactoEmail, String>
    implements ContactoEmailService {
  @Autowired
  public ContactoEmailServiceImpl(BaseRepository<ContactoEmail, String> baseRepository) {
    super(baseRepository);
  }

}
