/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.parcial.persistence;

import co.edu.uniandes.csw.parcial.entities.ProductEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author n.carvajalc
 */
@Stateless
public class ProductPersistence {

    @PersistenceContext( unitName = "parcialPU" )
    protected EntityManager em;
    
    public ProductEntity create( ProductEntity product) {
        em.persist(product);
        return product;
    }
}
