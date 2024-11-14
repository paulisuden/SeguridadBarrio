package com.is2.seguridad_barrio_cliente.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.is2.seguridad_barrio_cliente.dto.UsuarioDTO;
import com.is2.seguridad_barrio_cliente.error.ErrorServiceException;
import com.is2.seguridad_barrio_cliente.rest.AuthDAORest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthDAORest authDAO;


    public String authenticateWithApi(UsuarioDTO usuario) throws ErrorServiceException {
        try {
            String token = authDAO.authenticate(usuario);
            JSONObject jsonObject = new JSONObject(token);

            token = jsonObject.getString("accessToken");
            return token;
        } catch (ErrorServiceException e) {
            throw new ErrorServiceException("Error en la autenticación");
        }
    }
}
