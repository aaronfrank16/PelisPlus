
package controlador;

import controlador.exceptions.RollbackFailureException;
import entidad.Peliculas;
import entidad.Series;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@Stateless
public class SeriesFacade{

    
    public SeriesFacade() {
        
    }
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("BlockbusterPU");
     private SeriesJpaController userJpa = new SeriesJpaController(emf);

    public void CrearSerie(Series Serie) {
        try {
            userJpa.create(Serie);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(SeriesFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SeriesFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
