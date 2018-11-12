
package controlador;

import entidad.Categorias;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@Stateless
public class CategoriasFacade{

    
    public CategoriasFacade() {
        
    }
     EntityManagerFactory emf = Persistence.createEntityManagerFactory("BlockbusterPU");
private CategoriasJpaController catjpa= new CategoriasJpaController(emf);
    public Categorias buscar_cat(int a){

Categorias b= catjpa.findCategorias(a);
return b;

}
}
