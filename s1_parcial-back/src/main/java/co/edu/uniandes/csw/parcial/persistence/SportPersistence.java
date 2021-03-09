/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.parcial.persistence;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.uniandes.csw.parcial.entities.SportEntity;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author elProfe
 */
@Stateless
public class SportPersistence {
    
    private static final Logger LOGGER = Logger.getLogger( SportPersistence.class.getName() );

    @PodamExclude
    @PersistenceContext( unitName = "parcialPU" )
    protected EntityManager em;
    
    public SportEntity find( Long sportId ) {
        LOGGER.log( Level.INFO, "Consultando el deporte con id={0}", sportId );
        return em.find( SportEntity.class, sportId );
    }
}
