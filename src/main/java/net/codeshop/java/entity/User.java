package net.codeshop.java.entity;

import java.util.Date;

public class User {

    public int id;
    public String email;
    public String encrypted_password;
    public String reset_password_token;
    public Date reset_password_sent_at;
    public Date remember_created_at;
    public int sign_in_count;
    public Date current_sign_in_at;
    public Date last_sign_in_at;
    public String current_sign_in_ip;
    public String last_sign_in_ip;
    public Date created_at;
    public Date update_at;
    public int role;
    public String name;
    public String provider;
    public String uid;
    public String image;

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

    public String getEncrypted_password() {
        return encrypted_password;
    }

    public void setEncrypted_password(String encrypted_password) {
        this.encrypted_password = encrypted_password;
    }
}
