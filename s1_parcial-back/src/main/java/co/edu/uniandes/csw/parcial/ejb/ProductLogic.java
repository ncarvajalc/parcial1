/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.parcial.ejb;

import co.edu.uniandes.csw.parcial.entities.ProductEntity;
import co.edu.uniandes.csw.parcial.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.parcial.persistence.ProductPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author n.carvajalc
 */
@Stateless
public class ProductLogic {
    @Inject
    private ProductPersistence persistence;
    
    public ProductEntity createProduct( ProductEntity product ) throws BusinessLogicException {
        if( product.getName() == null || product.getName().isEmpty() )
            throw new BusinessLogicException("El nombre no puede ser vacío o nulo");
        if( product.getUnits() > 0 && product.getProductValue() == 0 )
            throw new BusinessLogicException("El valor del producto no puede ser cero si tiene unidades");
        if( product.getUnits() < 0 )
            throw new BusinessLogicException("Las unidades no pueden ser negativas");
        if( product.getProductValue()< 0 )
            throw new BusinessLogicException("El valor no puede ser negativo");
        if( product.getUnique() && product.getSport().getName().equals("Futbol") )
            throw new BusinessLogicException("No se permiten productos únicos para el deporte 'Futbol'");
        if( product.getSport() == null)
            throw new BusinessLogicException("El deporte no puede ser nulo");
        product = persistence.create(product);
        return product;
    }
}
