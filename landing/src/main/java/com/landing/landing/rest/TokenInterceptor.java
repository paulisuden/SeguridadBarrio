package com.landing.landing.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;

public class TokenInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException, IOException {
        if (request.getHeaders().containsKey("No-Token") && request.getHeaders().get("No-Token").contains("true")) {
            return execution.execute(request, body);
        }
        String token;
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpSession session = req.getSession(false);

        if (session != null) {
            token = (String) session.getAttribute("token");
        } else {
            token = null;
        }
        request.getHeaders().add("Authorization", "Bearer " + token);
        return execution.execute(request, body);
    }
}
