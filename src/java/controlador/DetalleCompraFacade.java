
package controlador;

import entidad.DetalleCompra;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@Stateless
public class DetalleCompraFacade{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("BlockbusterPU");
    private CarritosJpaController carritoJpa = new CarritosJpaController(emf);

    public DetalleCompraFacade() {
        
    }
    
}
