
package controlador;

import entidad.DatosPago;
import javax.ejb.Stateless;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Stateless
public class DatosPagoFacade{
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("BlockbusterPU");
    private DatosPagoJpaController datosJpa = new DatosPagoJpaController(emf);
    
    public DatosPagoFacade() {
        
    }

    public int registro(DatosPagoPojo datosPojo) throws Exception {
        DatosPago data = new DatosPago();
        
        data.setCodigoEsguridad(datosPojo.getCodigo_seguridad());
        data.setFechaExpiracion(datosPojo.getFecha_expiracion());
        data.setIdDatosPago(datosPojo.getIdDatosPago());
        data.setTarjeta(datosPojo.getTarjeta());
        
        datosJpa.create(data);
        return data.getIdDatosPago();
    }

    public DatosPago getDatosPago(int idDatosPago) {
        return datosJpa.findDatosPago(idDatosPago);
    }
    
}
