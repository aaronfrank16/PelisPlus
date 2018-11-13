package controlador;

import entidad.CarritoProducto;
import entidad.Carritos;
import entidad.Peliculas;
import entidad.Productos;
import entidad.Series;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Stateless
public class CarritoProductoFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("BlockbusterPU");
    private CarritoProductoJpaController carritoPJpa = new CarritoProductoJpaController(emf);
    private CarritosJpaController carritoJpa = new CarritosJpaController(emf);
    private ProductosJpaController productoJpa = new ProductosJpaController(emf);
    private PeliculasJpaController peliculaJpa = new PeliculasJpaController(emf);
    private SeriesJpaController serieJpa = new SeriesJpaController(emf);

    public CarritoProductoFacade() {

    }

    public void create(CarritoProductoPojo compra) throws Exception {
        CarritoProducto c = new CarritoProducto();
        c.setCantidad(compra.getCantidad());
        c.setIdCarrito(compra.getIdCarrito());
        c.setIdCarritoProducto(compra.getIdCarritoProducto());
        c.setIdProducto(compra.getIdProducto());
        c.setSubtotal(compra.getSubtotal());
        c.setTipoCompra(compra.getTipo_compra());
        c.setTipoProducto(compra.getTipo_producto());
        carritoPJpa.create(c);
    }

    public Carritos getCarrito(int idCar) {
        return carritoJpa.findCarritos(idCar);
    }

    public Productos getProducto(int idProducto) {
        return productoJpa.findProductos(idProducto);
    }

    public Peliculas getPelicula(int idProducto) {
        Productos product = null;
        product = productoJpa.findProductos(idProducto);
        return peliculaJpa.findPeliculasByProduct(product);
    }

    public Series getSerie(int idProducto) {
        Productos product = null;
        product = productoJpa.findProductos(idProducto);
        return serieJpa.findSeriesByProduct(product);
    }

    public List<CarritoProducto> filtrar(Carritos carrito) {
        return carritoPJpa.obtenerCarrito(carrito);
    }
    
    public List<CarritoProducto> filtrarR(Carritos carrito) {
        return carritoPJpa.obtenerCarritoR(carrito);
    }

    public void remove(Carritos carrito) throws Exception {
        List<CarritoProducto> registros = carritoPJpa.obtenerCompra(carrito);
        for (int i = 0; i < registros.size(); i++) {
            carritoPJpa.destroy(registros.get(i).getIdCarritoProducto());
        }
    }

}
