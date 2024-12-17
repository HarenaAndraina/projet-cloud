package com.cloud.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.UUID;

@WebServlet("/api/register")
public class RegistrationServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/user_registration";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1. Lire les données JSON de la requête
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"message\":\"Email et mot de passe sont obligatoires.\"}");
            return;
        }

        // 2. Générer un token de vérification
        String token = UUID.randomUUID().toString();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // 3. Insérer l'utilisateur dans la base de données
            String insertQuery = "INSERT INTO users (email, password, verification_token) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery);
            stmt.setString(1, email);
            stmt.setString(2, password); // Ajoutez un hashage pour sécuriser le mot de passe
            stmt.setString(3, token);

            stmt.executeUpdate();

            // 4. Envoyer l'email de validation
            sendVerificationEmail(email, token);

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"message\":\"Inscription réussie. Vérifiez votre email pour valider votre compte.\"}");
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"message\":\"Erreur lors de l'inscription.\"}");
        }
    }

    private void sendVerificationEmail(String email, String token) {
        final String senderEmail = "votre-email@gmail.com"; // Remplacez par votre email
        final String senderPassword = "votre-mot-de-passe";

        String host = "smtp.gmail.com";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Validation de votre inscription");
            message.setText("Cliquez sur le lien suivant pour valider votre compte : " +
                    "http://localhost:8080/api/validate?token=" + token);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
