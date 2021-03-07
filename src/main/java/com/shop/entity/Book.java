package com.shop.entity;

import java.util.Date;

public class Book {

    private int id;
    private String title;
    private String description;
    private String image;
    private String materials;
    private int price;
    private int height;
    private int width;
    private int depth;
    private String year;
    private int in_stock;
    private String author;
    private Date created_at;
    private Date update_at;


    public static Builder newBuilder(){
        return new Book.Builder();
    }

    public static class Builder {

        private Book book;

        public Builder(){
            book = new Book();
        }


        public Builder addId(int val){
            book.id = val;
            return this;
        }

        public Builder addTitle(String val){
            book.title = val;
            return this;
        }

        public Builder addDescription(String val){
            book.description = val;
            return this;
        }

        public Builder addImage(String val){
            book.image = val;
            return this;
        }

        public Builder addMaterials(String val){
            book.materials = val;
            return this;
        }

        public Builder addPrice(int val){
            book.price = val;
            return this;
        }

        public Builder addHeight(int val){
            book.height = val;
            return this;
        }

        public Builder addWidth(int val){
            book.width = val;
            return this;
        }

        public Builder addDepth(int val){
            book.depth = val;
            return this;
        }

        public Builder addYear(String val){
            book.year = val;
            return this;
        }

        public Builder addInstock(int val){
            book.in_stock = val;
            return this;
        }

        public Builder addAuthor(String val){
            book.author = val;
            return this;
        }

        public Builder addCreatedat(Date val){
            book.created_at = val;
            return this;
        }

        public Builder addUpdateat(Date val){
            book.update_at = val;
            return this;
        }

        public Book build() {
            return book;
        }

    }


    public Book(){

    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", materials='" + materials + '\'' +
                ", price=" + price +
                ", height=" + height +
                ", width=" + width +
                ", depth=" + depth +
                ", year='" + year + '\'' +
                ", in_stock=" + in_stock +
                ", author='" + author + '\'' +
                ", created_at=" + created_at +
                ", update_at=" + update_at +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getIn_stock() {
        return in_stock;
    }

    public void setIn_stock(int in_stock) {
        this.in_stock = in_stock;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
