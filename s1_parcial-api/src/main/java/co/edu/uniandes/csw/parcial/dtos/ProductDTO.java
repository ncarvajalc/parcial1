/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.parcial.dtos;

import co.edu.uniandes.csw.parcial.entities.ProductEntity;
import java.io.Serializable;

/**
 *
 * @author elProfe
 */
public class ProductDTO implements Serializable{
    private Long id;
    private String name;
    private String description;
    private Double productValue;
    private Integer units;
    private Boolean uniqueProduct;
    private SportDTO sport;
    
    public ProductDTO(){
    }
    
    public ProductDTO(ProductEntity productEntity){
        if(productEntity != null) {
            this.id = productEntity.getId();
            this.description = productEntity.getDescription();
            this.name = productEntity.getName();
            this.productValue = productEntity.getProductValue();
            this.sport = new SportDTO(productEntity.getSport());
            this.uniqueProduct = productEntity.getUnique();
            this.units = productEntity.getUnits();
        }
    }
    
    public ProductEntity toEntity() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(this.id);
        productEntity.setDescription(this.description);
        productEntity.setName(this.name);
        productEntity.setProductValue(this.productValue);
        productEntity.setUnique(this.uniqueProduct);
        productEntity.setUnits(this.units);
        if(this.sport != null)
            productEntity.setSport(this.sport.toEntity());
        return productEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getProductValue() {
        return productValue;
    }

    public void setProductValue(Double productValue) {
        this.productValue = productValue;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

    public Boolean getUniqueProduct() {
        return uniqueProduct;
    }

    public void setUniqueProduct(Boolean uniqueProduct) {
        this.uniqueProduct = uniqueProduct;
    }

    public SportDTO getSport() {
        return sport;
    }

    public void setSport(SportDTO sport) {
        this.sport = sport;
    }
}
