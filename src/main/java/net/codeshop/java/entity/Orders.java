package net.codeshop.java.entity;

import java.util.Date;

public class Orders {

    public int id;
    public String invoice;
    public int item_total_price;
    public int order_total_price;
    public int coupon;
    public int user_id;
    public String state;
    public int delivery_id;
    public Date created_at;
    public Date update_at;
}
