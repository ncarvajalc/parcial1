/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.parcial.test.logic;

import co.edu.uniandes.csw.parcial.ejb.ProductLogic;
import co.edu.uniandes.csw.parcial.entities.ProductEntity;
import co.edu.uniandes.csw.parcial.entities.SportEntity;
import co.edu.uniandes.csw.parcial.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.parcial.persistence.ProductPersistence;
import javax.inject.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;
import org.junit.Before;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author n.carvajalc
 */
@RunWith( Arquillian.class )
public class ProductLogicTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProductEntity.class.getPackage())
                .addPackage(ProductPersistence.class.getPackage())
                .addPackage(ProductLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private ProductLogic productLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private PodamFactory factory = new PodamFactoryImpl();

    private List<ProductEntity> data = new ArrayList<>();

    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch ( Exception e ) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch ( Exception e1 ) {
                e1.printStackTrace();
            }
        }
    }
    
    private void clearData() {
        em.createQuery( "delete from ProductEntity" ).executeUpdate();
    }
    
    private void insertData() {
        
        for ( int i = 0; i < 3; i++ ) {
            ProductEntity entity = factory.manufacturePojo( ProductEntity.class );

            em.persist( entity );
            data.add( entity );
        }
    }
    
    //Pruebas de cumplimiento de reglas de negocio
    @Test
    public void createProductTest() throws BusinessLogicException {
        
        ProductEntity newEntity = factory.manufacturePojo( ProductEntity.class );
        SportEntity sportEntity = factory.manufacturePojo(SportEntity.class);
        newEntity.setSport(sportEntity);
        ProductEntity result = productLogic.createProduct( newEntity );
        Assert.assertNotNull(result);

        ProductEntity entity = em.find( ProductEntity.class, result.getId() );
        Assert.assertNotNull( entity );
        
        Assert.assertEquals( newEntity.getId(), entity.getId() );
        Assert.assertEquals( newEntity.getDescription(), entity.getDescription());
        Assert.assertEquals( newEntity.getName(), entity.getName());
        Assert.assertEquals( newEntity.getProductValue(), entity.getProductValue());
        Assert.assertEquals( newEntity.getUnique(), entity.getUnique());
        Assert.assertEquals( newEntity.getUnits(), entity.getUnits());
    }
    
    @Test(expected = BusinessLogicException.class )
    public void createProductWithNullNameTest() throws BusinessLogicException{
        ProductEntity newEntity = factory.manufacturePojo(ProductEntity.class);
        newEntity.setName(null);
        productLogic.createProduct(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class )
    public void createProductWithNegativeUnitsTest() throws BusinessLogicException{
        ProductEntity newEntity = factory.manufacturePojo(ProductEntity.class);
        newEntity.setUnits(-4);
        productLogic.createProduct(newEntity);
    }
}