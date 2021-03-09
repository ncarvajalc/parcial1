/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.parcial.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamDoubleValue;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamIntValue;

/**
 *
 * @author n.carvajalc
 */
@Entity
public class ProductEntity extends BaseEntity {
    
    private String name;
    private String description;
    @PodamDoubleValue(minValue = 0.1)
    private Double productValue;
    
    @PodamIntValue(minValue = 1)
    private Integer units;
    
    private Boolean uniqueProduct;
    
    @PodamExclude
    @ManyToOne
    private SportEntity sport;

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

    public Boolean getUnique() {
        return uniqueProduct;
    }

    public void setUnique(Boolean unique) {
        this.uniqueProduct = unique;
    }

    public SportEntity getSport() {
        return sport;
    }

    public void setSport(SportEntity sport) {
        this.sport = sport;
    }
    
    
}
