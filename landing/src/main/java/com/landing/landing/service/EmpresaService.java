package com.landing.landing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.landing.landing.dto.EmpresaDTO;
import com.landing.landing.error.ErrorServiceException;
import com.landing.landing.rest.EmpresaDAORest;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaDAORest dao;

    public EmpresaDTO buscarEmpresa() throws ErrorServiceException {

        try {
            var empresas = dao.listar();
            if (empresas.size() == 0)
                throw new ErrorServiceException("No hay empresa creada");

            return empresas.get(0);
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
}
