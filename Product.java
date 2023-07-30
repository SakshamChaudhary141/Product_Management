/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.products_manager;

/**
 *
 * @author ASUS
 */
public class Product {
    
    private Integer ID;
    private String Name;
    private String Category;
    private Integer Quantity;
    private Double Price;
    private String image_path;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer Quantity) {
        this.Quantity = Quantity;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double Price) {
        this.Price = Price;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public Product(Integer ID, String Name, String Category, Integer Quantity, Double Price, String image_path) {
        this.ID = ID;
        this.Name = Name;
        this.Category = Category;
        this.Quantity = Quantity;
        this.Price = Price;
        this.image_path = image_path;
    }
    
}
