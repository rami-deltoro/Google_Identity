package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.security.GeneralSecurityException;

@RequiredArgsConstructor
@Slf4j
@Component
public class GoogleAuthenticationVerifier {

    private final GoogleIdTokenVerifier verifier;


    public boolean verify(JsonNode jsonNode) {
        JsonNode qc = jsonNode.get("qc");
        String idTokenString = qc.get("id_token").toString();

        GoogleIdToken idToken = null;
        boolean isVerified = false;
        try {
            idToken = verifier.verify(idTokenString.replace("\"",""));
            if (idToken != null) {

                GoogleIdToken.Payload payload = idToken.getPayload();
                isVerified = payload.getEmailVerified();
                // Print user identifier
                String userId = payload.getSubject();

                for (Field field : GoogleIdToken.Payload.class.getDeclaredFields()) {
                    field.setAccessible(true);
                    String name = field.getName();
                    Object value = field.get(payload);
                    System.out.printf("Field name: %s, Field value: %s%n", name, value);
                }
            } else {
                log.info("Invalid ID token.");
            }
        } catch (GeneralSecurityException e) {
            log.error("GeneralSecurityException",e);
        } catch (IOException e) {
            log.error("IOException",e);
        } catch (IllegalAccessException e) {
            log.error("IllegalAccessException",e);
        }


        return isVerified;

    }
}
