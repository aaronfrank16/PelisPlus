
package controlador;

import controlador.exceptions.RollbackFailureException;
import entidad.Peliculas;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Stateless
public class PeliculasFacade{

    

    public PeliculasFacade() {
        
    }
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("BlockbusterPU");
     private PeliculasJpaController userJpa = new PeliculasJpaController( emf);

    public void crearPelicula(Peliculas pelicula) {
        try {
            userJpa.create(pelicula);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(PeliculasFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PeliculasFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
}
