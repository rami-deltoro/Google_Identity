package com.example.demo;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class Config {


    @Bean
    public GoogleIdTokenVerifier googleIdTokenVerifier() {
        return new GoogleIdTokenVerifier.Builder(new NetHttpTransport(),  JacksonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList("830309700264-oq0gkjtdiedfrikgbmucq4r4rjtjff9e.apps.googleusercontent.com"))
                .build();
    }
}
