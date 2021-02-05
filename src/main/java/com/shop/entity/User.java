package com.shop.entity;

import java.util.Date;

public class User {

    public int id;
    public String email;
    public String encrypted_password;
    public Date reset_password_token;
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

    public Date getReset_password_token() {
        return reset_password_token;
    }

    public void setReset_password_token(Date reset_password_token) {
        this.reset_password_token = reset_password_token;
    }

    public Date getReset_password_sent_at() {
        return reset_password_sent_at;
    }

    public void setReset_password_sent_at(Date reset_password_sent_at) {
        this.reset_password_sent_at = reset_password_sent_at;
    }

    public Date getRemember_created_at() {
        return remember_created_at;
    }

    public void setRemember_created_at(Date remember_created_at) {
        this.remember_created_at = remember_created_at;
    }

    public int getSign_in_count() {
        return sign_in_count;
    }

    public void setSign_in_count(int sign_in_count) {
        this.sign_in_count = sign_in_count;
    }

    public Date getCurrent_sign_in_at() {
        return current_sign_in_at;
    }

    public void setCurrent_sign_in_at(Date current_sign_in_at) {
        this.current_sign_in_at = current_sign_in_at;
    }

    public Date getLast_sign_in_at() {
        return last_sign_in_at;
    }

    public void setLast_sign_in_at(Date last_sign_in_at) {
        this.last_sign_in_at = last_sign_in_at;
    }

    public String getCurrent_sign_in_ip() {
        return current_sign_in_ip;
    }

    public void setCurrent_sign_in_ip(String current_sign_in_ip) {
        this.current_sign_in_ip = current_sign_in_ip;
    }

    public String getLast_sign_in_ip() {
        return last_sign_in_ip;
    }

    public void setLast_sign_in_ip(String last_sign_in_ip) {
        this.last_sign_in_ip = last_sign_in_ip;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
