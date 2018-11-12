package modelo;

import controlador.CarritoPojo;
import controlador.CarritoProductoFacade;
import controlador.CarritoProductoPojo;
import controlador.CarritosFacade;
import entidad.Carritos;
import entidad.Productos;
import entidad.Usuarios;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@Named(value = "carritoCompraBean")
@RequestScoped
public class CarritoCompraBean {

    private int idCarrito;
    private Usuarios idUsuario;
    private double total;

    private CarritosFacade carritoFacade;
    private CarritoProductoFacade carProductFacade;

    public CarritoCompraBean() {

    }

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String crearCompra(String correo) throws Exception {
        if (!correo.equals("")) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            LoginBean neededBean = (LoginBean) facesContext.getApplication().createValueBinding("#{loginBean}").getValue(facesContext);
            if (neededBean.getIdCarrito() == 0) {
                Calendar fecha = Calendar.getInstance();
                Date now = fecha.getTime();
                fecha.add(Calendar.DAY_OF_YEAR, 30);
                Date agregado = fecha.getTime();
                FacesContext context = FacesContext.getCurrentInstance();
                CarritoPojo carrito = new CarritoPojo();
                carrito.setIdCarrito(idCarrito);
                carrito.setIdUsuario(idUsuario);
                carrito.setTotal(0);
                
                carritoFacade = new CarritosFacade();
                carritoFacade.crearCarrito(carrito,correo);
                
                CarritoPojo car = carritoFacade.crearCarrito(carrito,correo);
                setIdCarrito(car.getIdCarrito());
                setIdUsuario(car.getIdUsuario());
                setTotal(car.getTotal());
                neededBean.setIdCarrito(car.getIdCarrito());
            } else {
                setIdCarrito(neededBean.getIdCarrito());
            }
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String txtProperty = request.getParameter("myForm:movie");
            int idProduct = Integer.parseInt(txtProperty);
            agregarCarrito(idProduct,1);//1.-Pelicula 2.-Serie
            //return "carrito";
        }
        return "Login";
    }
    
    public void agregarCarrito(int idProduct,int tipo) {
        try {
            System.out.println("El producto que se agregara al carrito----- " + idProduct);
            CarritoProductoPojo car_product = new CarritoProductoPojo();
            carProductFacade = new CarritoProductoFacade();
            car_product.setIdCarritoProducto(idCarrito);
            car_product.setCantidad(1);
            car_product.setIdCarrito(carProductFacade.getCarrito(idCarrito));
            car_product.setIdProducto(carProductFacade.getProducto(idProduct));
            car_product.setSubtotal(carProductFacade.getPelicula(carProductFacade.getProducto(idProduct).getIdProducto()).getPrecioCompra());
            car_product.setTipo_producto(tipo);
            car_product.setTipo_compra(1);
            carProductFacade.create(car_product);
            
            Carritos editable = carProductFacade.getCarrito(idCarrito);
            editable.setTotal((double) Math.round((editable.getTotal() + car_product.getSubtotal()) * 100d) / 100d);
            System.out.println(editable.getTotal() + "Total de compraXDXDXDXD");
            carritoFacade = new CarritosFacade();
            carritoFacade.update(editable);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
