
package controlador;

import entidad.Productos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@Stateless
public class ProductosFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("BlockbusterPU");
    private ProductosJpaController productoJpa = new ProductosJpaController(emf);
    private PeliculasJpaController peliculaJpa = new PeliculasJpaController(emf);
    private SeriesJpaController serieJpa = new SeriesJpaController(emf);
    
    public ProductosFacade() {
        
    }
    
    public ProductoPojo buscarPelicula(int id) {
        ProductoPojo productoPojo = new ProductoPojo();
        Productos product =  null;
        product = productoJpa.findProductos(id);
        System.out.println("Producto hallado " + product.getTitulo());
        productoPojo.setAño(product.getAño());
        productoPojo.setIdCategoria(product.getIdCategoria());
        productoPojo.setIdProducto(product.getIdProducto());
        productoPojo.setRating(product.getRating());
        productoPojo.setSinopsis(product.getSinopsis());
        productoPojo.setTipo(product.getTipo());
        productoPojo.setTitulo(product.getTitulo());
        productoPojo.setPelicula(peliculaJpa.findPeliculasByProduct(product));
        return productoPojo;
    }
    
    public List<Productos> listaCategoria1(){
        return productoJpa.getCategorias(1);
    }
    
    public List<Productos> listaCategoria2(){
        return productoJpa.getCategorias(2);
    }
    
    public List<Productos> listaCategoria3(){
        return productoJpa.getCategorias(3);
    }
    
    public List<Productos> listaCategoria4(){
        return productoJpa.getCategorias(4);
    }
    
    public List<Productos> listaCategoria5(){
        return productoJpa.getCategorias(5);
    }
    
    public ProductoPojo buscarSerie(int id) {
        ProductoPojo productoPojo = new ProductoPojo();
        Productos product =  null;
        product = productoJpa.findProductos(id);;
        System.out.println("Producto hallado " + product.getTitulo());
        productoPojo.setAño(product.getAño());
        productoPojo.setIdCategoria(product.getIdCategoria());
        productoPojo.setIdProducto(product.getIdProducto());
        productoPojo.setRating(product.getRating());
        productoPojo.setSinopsis(product.getSinopsis());
        productoPojo.setTipo(product.getTipo());
        productoPojo.setTitulo(product.getTitulo());
        productoPojo.setSerie(serieJpa.findSeriesByProduct(product));
        return productoPojo;
    }
    
    public List<Productos> listaCategoriaSerie1(){
        return productoJpa.getCategoriasSerie(1);
    }
    
    public List<Productos> listaCategoriaSerie2(){
        return productoJpa.getCategoriasSerie(2);
    }
    
    public List<Productos> listaCategoriaSerie3(){
        return productoJpa.getCategoriasSerie(3);
    }
    
    public List<Productos> listaCategoriaSerie4(){
        return productoJpa.getCategoriasSerie(4);
    }
    
    public List<Productos> listaCategoriaSerie5(){
        return productoJpa.getCategoriasSerie(5);
    }
    
    public List<Productos> listaPelis(){
        return productoJpa.getMejoresPelis();
    }
    
    public List<Productos> listaSeries(){
        return productoJpa.getMejoresSeries();
    }
    
}
