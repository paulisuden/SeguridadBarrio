package com.is.servidor_barrio.business.domain.entity.authentication;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private final String accessToken;
}
