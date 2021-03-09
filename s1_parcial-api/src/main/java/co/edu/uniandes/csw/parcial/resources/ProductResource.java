/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.parcial.resources;

import co.edu.uniandes.csw.parcial.dtos.ProductDTO;
import co.edu.uniandes.csw.parcial.dtos.SportDTO;
import co.edu.uniandes.csw.parcial.ejb.ProductLogic;
import co.edu.uniandes.csw.parcial.ejb.SportLogic;
import co.edu.uniandes.csw.parcial.entities.ProductEntity;
import co.edu.uniandes.csw.parcial.entities.SportEntity;
import co.edu.uniandes.csw.parcial.exceptions.BusinessLogicException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author n.carvajalc
 */
@Path("sports")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ProductResource {
    
    @Inject 
    ProductLogic productLogic;
    @POST
    @Path( "{productID: \\d+}/products" )
    public ProductDTO createProduct( @PathParam( "productID" ) Long productID, ProductDTO product) throws BusinessLogicException {
        ProductEntity se = product.toEntity();
        se = productLogic.createProduct(se);
        return new ProductDTO(se);
    }
}
