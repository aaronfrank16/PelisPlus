
package controlador;

import entidad.Carritos;
import entidad.Usuarios;
import javax.ejb.Stateless;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Stateless
public class CarritosFacade{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("BlockbusterPU");
    private CarritosJpaController carritoJpa = new CarritosJpaController(emf);
    private UsuariosJpaController userJpa = new UsuariosJpaController(emf);

    public CarritosFacade() {
        
    }

    public CarritoPojo crearCarrito(CarritoPojo carrito, String email) throws Exception {
        Carritos car = new Carritos();
        car.setIdCarrito(carrito.getIdCarrito());
        car.setTotal(carrito.getTotal());
        car.setIdUsuario(buscarPorcorreo(email));
        carritoJpa.create(car);
        CarritoPojo pojo = new CarritoPojo();  
        pojo.setIdCarrito(car.getIdCarrito());
        pojo.setIdUsuario(car.getIdUsuario());
        pojo.setTotal(car.getTotal());
        System.out.println("Cree el carrito nuevo, el id "+pojo.getIdCarrito()+" "+pojo.getIdUsuario().getCorreo());
        return pojo;
    }
    
    public Usuarios buscarPorcorreo(String correo_user) {
        Usuarios user;
        user = userJpa.findByCorreo(correo_user);
        return user;
    }
    
    public void update(Carritos compra) throws Exception{
        carritoJpa.edit(compra);
    }
    
    public void remove(int idCompraP) throws Exception{
        carritoJpa.destroy(idCompraP);
    }
    
}
