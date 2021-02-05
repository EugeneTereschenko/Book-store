package com.shop.entity;

import java.util.Date;

public class Cart {


    public int id;
    public int order_total_price;
    public Date created_at;
    public Date update_at;
    public int user_id;
    public int item_total_price;
    public int coupon;
    public String checkout_step;
    public int delivery_id;

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_total_price() {
        return order_total_price;
    }

    public void setOrder_total_price(int order_total_price) {
        this.order_total_price = order_total_price;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getItem_total_price() {
        return item_total_price;
    }

    public void setItem_total_price(int item_total_price) {
        this.item_total_price = item_total_price;
    }

    public int getCoupon() {
        return coupon;
    }

    public void setCoupon(int coupon) {
        this.coupon = coupon;
    }

    public String getCheckout_step() {
        return checkout_step;
    }

    public void setCheckout_step(String checkout_step) {
        this.checkout_step = checkout_step;
    }

    public int getDelivery_id() {
        return delivery_id;
    }

    public void setDelivery_id(int delivery_id) {
        this.delivery_id = delivery_id;
    }
}
