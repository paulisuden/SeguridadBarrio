package com.landing.landing.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.landing.landing.rest.AuthDAORest ;
import com.landing.landing.rest.ErrorDAOException;
import com.landing.landing.dto.UsuarioDTO;
import com.landing.landing.error.ErrorServiceException;

@Service
public class AuthService {
    @Autowired
    private AuthDAORest authDAO;

    @Autowired
    private UsuarioDTOService userService;

    public void authenticateWithApi() throws ErrorServiceException {
        try {
            UsuarioDTO usuario = userService.crear("test@gmail.com", "1234");
            String token = authDAO.authenticate(usuario);
            JSONObject jsonObject = new JSONObject(token);

            token = jsonObject.getString("accessToken");

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            session.setAttribute("token", token);
        } catch (Exception e) {
            throw new ErrorServiceException("Error al autenticar el usuario");
        }
    }

}
