package com.vaccin.vaccin.errors;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.common.exceptions.OAuth2ExceptionJackson2Serializer;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;

public class CustomSerializer2 extends OAuth2ExceptionJackson2Serializer {

    @Override
    public void serialize(OAuth2Exception value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        String errorMessage = value.getMessage();
        if (errorMessage != null) {
            errorMessage = HtmlUtils.htmlEscape(errorMessage);
        }

        jgen.writeString(errorMessage);
    }

}
