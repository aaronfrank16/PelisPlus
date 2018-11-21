
package controlador;

import entidad.Compras;
import entidad.Rentas;
import entidad.Usuarios;
import java.util.List;
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

    public int create(RentaPojo rentaPojo) throws Exception {
        Rentas renta = new Rentas();
        renta.setFechaDevolucion(rentaPojo.getFecha_devolucion());
        renta.setFechaEntrega(rentaPojo.getFecha_entrega());
        renta.setFechaRenta(rentaPojo.getFecha_renta());        
        renta.setIdDatosPago(rentaPojo.getIdDatosPago());
        renta.setIdRenta(rentaPojo.getIdRenta());
        renta.setIdUsuario(rentaPojo.getIdUsuario());
        renta.setTotalRenta(rentaPojo.getTotal_renta());
        
        rentaJpa.create(renta);
        return renta.getIdRenta();
    }
    
    public Rentas getRenta(int id){
        return rentaJpa.findRentas(id);
    }

    public void edit(Rentas rentas) throws Exception {
        rentaJpa.edit(rentas);
    }

    public List<Rentas> listaRentas(Usuarios idUsuario) {
        return rentaJpa.findRentasbyUser(idUsuario);
    }
    
}
