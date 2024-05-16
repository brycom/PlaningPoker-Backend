package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Security.Utilitys;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class KeyGeneratorUtility {

    public static KeyPair generateRsaKeyPair() {

        KeyPair keyPair;

        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();

        } catch (Exception e) {
            throw new IllegalStateException("Failed to generate key pair");

        }

        return keyPair;
    }

}
