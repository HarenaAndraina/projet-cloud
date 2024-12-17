package com.cloud.model;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JWTUtil {
    // Clé secrète pour signer les tokens (à sécuriser en production)
    private static final String SECRET_KEY = "mySecretKey123";

    // Durée de validité du token (ex : 1 heure)
    private static final long EXPIRATION_TIME = 3600000;

    // Générer un JWT
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // Données de l'utilisateur
                .setIssuedAt(new Date()) // Date d'émission
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Expiration
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // Signature avec clé secrète
                .compact();
    }

    // Valider un JWT
    public static Claims validateToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY) // Vérification avec la même clé secrète
                .parseClaimsJws(token)
                .getBody();
    }
}

