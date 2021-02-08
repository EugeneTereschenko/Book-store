package com.shop.entity;

import java.util.Date;

public class Card {

    public String card_number;
    public String name;
    public String image;
    public int cvv;
    public String expiration_month_year;
    public int user_id;
    public Date created_at;
    public Date update_at;

    public String getExpiration_month_year() {
        return expiration_month_year;
    }

    public void setExpiration_month_year(String expiration_month_year) {
        this.expiration_month_year = expiration_month_year;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
}
