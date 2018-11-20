
package controlador;

import entidad.Compras;
import entidad.Rentas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@Stateless
public class RentasFacade{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("BlockbusterPU");
    private RentasJpaController rentaJpa = new RentasJpaController(emf);
    
    public RentasFacade() {
        
    }

    public void create(RentaPojo rentaPojo) throws Exception {
        Rentas renta = new Rentas();
        renta.setFechaDevolucion(rentaPojo.getFecha_devolucion());
        renta.setFechaEntrega(rentaPojo.getFecha_entrega());
        renta.setFechaRenta(rentaPojo.getFecha_renta());        
        renta.setIdDatosPago(rentaPojo.getIdDatosPago());
        renta.setIdRenta(rentaPojo.getIdRenta());
        renta.setIdUsuario(rentaPojo.getIdUsuario());
        renta.setTotalRenta(rentaPojo.getTotal_renta());
        
        rentaJpa.create(renta);
    }
    
}
