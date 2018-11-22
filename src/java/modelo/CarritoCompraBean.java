package modelo;

import controlador.CarritoPojo;
import controlador.CarritoProductoFacade;
import controlador.CarritoProductoPojo;
import controlador.CarritosFacade;
import controlador.PeliculasFacade;
import controlador.ProductosFacade;
import controlador.SeriesFacade;
import entidad.Carritos;
import entidad.Peliculas;
import entidad.Series;
import entidad.Usuarios;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Named(value = "carritoCompraBean")
@RequestScoped
public class CarritoCompraBean {

    private int idCarrito;
    private Usuarios idUsuario;
    private double total;

    private CarritosFacade carritoFacade;
    private CarritoProductoFacade carProductFacade;
    private ProductosFacade productFacade;
    private PeliculasFacade peliculaFacade = new PeliculasFacade();
    private SeriesFacade serieFacade = new SeriesFacade();

    private HttpSession session;
    private ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

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

    public String crearCompra(int tipo, int tipo_compra) throws Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        session = (HttpSession) context.getExternalContext().getSession(false);
        System.out.println(session.getAttribute("email")+" MI CORREOsjdhfdskd");
        System.out.println(session.getId());
        if (session.getAttribute("email") != null) {
//if (!correo.equals("")) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            //LoginBean neededBean = (LoginBean) facesContext.getApplication().createValueBinding("#{loginBean}").getValue(facesContext);
            if (session.getAttribute("idCarrito").equals(0)) {
//if (neededBean.getIdCarrito() == 0) {
                Calendar fecha = Calendar.getInstance();
                Date now = fecha.getTime();
                fecha.add(Calendar.DAY_OF_YEAR, 30);
                Date agregado = fecha.getTime();
                CarritoPojo carrito = new CarritoPojo();
                carrito.setIdCarrito(idCarrito);
                carrito.setIdUsuario(idUsuario);
                carrito.setTotal(0);

                carritoFacade = new CarritosFacade();
                CarritoPojo car = carritoFacade.crearCarrito(carrito, session.getAttribute("email").toString());
                setIdCarrito(car.getIdCarrito());
                setIdUsuario(car.getIdUsuario());
                setTotal(car.getTotal());
                session.setAttribute("idCarrito", car.getIdCarrito());
                //neededBean.setIdCarrito(car.getIdCarrito());
            } else {
                setIdCarrito(Integer.parseInt(session.getAttribute("idCarrito").toString()));
            }
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String txtProperty = request.getParameter("myForm:movie");
            int idProduct = Integer.parseInt(txtProperty);
            agregarCarrito(idProduct, tipo, tipo_compra);//1.-Pelicula 2.-Serie || 1.-Compra 2.-Renta
            return "carrito";
        }
        return "Login";
    }

    public void agregarCarrito(int idProduct, int tipo, int tipo_compra) {
        try {
            System.out.println("El producto que se agregara al carrito----- " + idProduct);
            productFacade = new ProductosFacade();
            carProductFacade = new CarritoProductoFacade();
            int cantAC = 0;
            if (tipo == 1) {
                if (tipo_compra == 1) {
                    cantAC = productFacade.buscarPelicula(carProductFacade.getProducto(idProduct).getIdProducto()).getPelicula().getCantidadAlmacen();
                } else {
                    cantAC = productFacade.buscarPelicula(carProductFacade.getProducto(idProduct).getIdProducto()).getPelicula().getCantidadRenta();
                }
            } else if (tipo == 2) {
                cantAC = productFacade.buscarSerie(carProductFacade.getProducto(idProduct).getIdProducto()).getSerie().getCantidadAlmacen();
            }
            if (cantAC == 0) {
                System.out.println("No hay productos en existencia");
            } else {
                CarritoProductoPojo car_product = new CarritoProductoPojo();
                car_product.setIdCarritoProducto(idCarrito);
                car_product.setCantidad(1);
                car_product.setIdCarrito(carProductFacade.getCarrito(Integer.parseInt(session.getAttribute("idCarrito").toString())));
                car_product.setIdProducto(carProductFacade.getProducto(idProduct));
                if (tipo == 1) {
                    if (tipo_compra == 1) {
                        car_product.setSubtotal(carProductFacade.getPelicula(carProductFacade.getProducto(idProduct).getIdProducto()).getPrecioCompra());
                    }else{
                        car_product.setSubtotal(carProductFacade.getPelicula(carProductFacade.getProducto(idProduct).getIdProducto()).getPrecioRenta());
                    }
                } else {
                    car_product.setSubtotal(carProductFacade.getSerie(carProductFacade.getProducto(idProduct).getIdProducto()).getPrecioCompra());
                }
                car_product.setTipo_producto(tipo);
                car_product.setTipo_compra(tipo_compra);
                carProductFacade.create(car_product);

                Carritos editable = carProductFacade.getCarrito(idCarrito);
                editable.setTotal((double) Math.round((editable.getTotal() + car_product.getSubtotal()) * 100d) / 100d);
                System.out.println(editable.getTotal() + "Total de compraXDXDXDXD");
                carritoFacade = new CarritosFacade();
                carritoFacade.update(editable);

                if (tipo == 1) {
                    Peliculas peli = carProductFacade.getPelicula(carProductFacade.getProducto(idProduct).getIdProducto());
                    if (tipo_compra == 1) {
                        peli.setCantidadAlmacen(peli.getCantidadAlmacen() - 1);
                    } else {
                        peli.setCantidadRenta(peli.getCantidadRenta() - 1);
                    }
                    peliculaFacade.update(peli);
                } else {
                    Series serie = carProductFacade.getSerie(carProductFacade.getProducto(idProduct).getIdProducto());
                    serie.setCantidadAlmacen(serie.getCantidadAlmacen() - 1);
                    serieFacade.update(serie);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
