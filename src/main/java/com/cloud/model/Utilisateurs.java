package com.cloud.model;

import org.joda.time.DateTime;
import utilitaire.UtilDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Utilisateurs {


    private int id;
    private String email;
    private String mot_de_passe;
    private String nom;
    private String prenom;
    private String role;
    private DateTime cree_le;
    private DateTime modifie_le;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public DateTime getCree_le() {
        return cree_le;
    }

    public void setCree_le(DateTime cree_le) {
        this.cree_le = cree_le;
    }

    public DateTime getModifie_le() {
        return modifie_le;
    }

    public void setModifie_le(DateTime modifie_le) {
        this.modifie_le = modifie_le;
    }

/*    public Utilisateurs findByEmail(String email, Connection connection) throws SQLException {

        connection = new UtilDB().GetConn("postgres", "postgres");
        String query = "SELECT * FROM UTILISATEURS WHERE email = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, email);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Utilisateurs user = new Utilisateurs();
            user.setEmail(resultSet.getString("nom"));
            user.setPassword(resultSet.getString("mot_de_passe"));
            user.setRole(resultSet.getString("role"));
            return user;
        } else {
            return null; // L'utilisateur n'existe pas
        }
    }*/
}

