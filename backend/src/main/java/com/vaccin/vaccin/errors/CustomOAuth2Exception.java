package com.vaccin.vaccin.errors;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.common.exceptions.OAuth2ExceptionJackson2Serializer;

@JsonSerialize(
        using = CustomSerializer.class
)
@com.fasterxml.jackson.databind.annotation.JsonSerialize(
        using = CustomSerializer2.class
)
public class CustomOAuth2Exception extends OAuth2Exception {

    public CustomOAuth2Exception(String msg, Throwable t) {
        super(msg, t);
    }

    public CustomOAuth2Exception(String msg) {
        super(msg);
    }
}
