package com.cloud.model;

import org.joda.time.DateTime;

public class Sessions {

    private int id;
    private String token;
    private DateTime expire_le;
    private DateTime cree_le;
    private int id_utilisateur;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public DateTime getExpire_le() {
        return expire_le;
    }

    public void setExpire_le(DateTime expire_le) {
        this.expire_le = expire_le;
    }

    public DateTime getCree_le() {
        return cree_le;
    }

    public void setCree_le(DateTime cree_le) {
        this.cree_le = cree_le;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }
}
