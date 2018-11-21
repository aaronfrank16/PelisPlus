package controlador;

import entidad.Compras;
import entidad.Usuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@Stateless
public class ComprasFacade{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("BlockbusterPU");
    private ComprasJpaController comprasJpa = new ComprasJpaController(emf);
    
    public ComprasFacade() {
        
    }

    public int create(ComprasPojo compraPojo) throws Exception {
        Compras compra = new Compras();
        compra.setFechaCompra(compraPojo.getFecha_compra());
        compra.setFechaEntrega(compraPojo.getFecha_entrega());
        compra.setIdCompra(compraPojo.getIdCompras());
        compra.setIdDatosPago(compraPojo.getIdDatosPago());
        compra.setIdUsuario(compraPojo.getIdUsuario());
        compra.setTotalCompra(compraPojo.getTotal_compra());
        
        comprasJpa.create(compra);
        return compra.getIdCompra();
    }
    
    public Compras getCompra(int idCompra){
        return comprasJpa.findCompras(idCompra);
    }

    public void edit(Compras c) throws Exception {
        comprasJpa.edit(c);
    }
    
    public List<Compras> listaCompras(Usuarios id){
        return comprasJpa.getComprasbyUser(id);
    }

}
