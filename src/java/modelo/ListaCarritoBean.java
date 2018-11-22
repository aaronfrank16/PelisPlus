package modelo;

import controlador.CarritoProductoFacade;
import controlador.ProductosFacade;
import entidad.CarritoProducto;
import entidad.Productos;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@Named(value = "listaCarritoBean")
@RequestScoped
public class ListaCarritoBean {

    private List<CarritoProducto> lista_compras;
    private List<CarritoProducto> lista_rentas;
    private double total_pago;

    private CarritoProductoFacade carritoPFacade;
    private ProductosFacade productFacade;
    
    private HttpSession session;
    private ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

    public ListaCarritoBean() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        //LoginBean neededBean = (LoginBean) facesContext.getApplication().createValueBinding("#{loginBean}").getValue(facesContext);
        FacesContext context = FacesContext.getCurrentInstance();
        session = (HttpSession) context.getExternalContext().getSession(false);
        System.out.println(session.getAttribute("email")+" MI CORREOsjdhfdskd");
        System.out.println(session.getId());
        carritoPFacade = new CarritoProductoFacade();
        this.lista_compras = (carritoPFacade.filtrar(carritoPFacade.getCarrito(Integer.parseInt(session.getAttribute("idCarrito").toString()))));
        this.lista_rentas = (carritoPFacade.filtrarR(carritoPFacade.getCarrito(Integer.parseInt(session.getAttribute("idCarrito").toString()))));
        double aux = 0;
        try {
            aux = carritoPFacade.getCarrito(Integer.parseInt(session.getAttribute("idCarrito").toString())).getTotal();
        } catch (Exception e) {
            aux = 0;
        }
        this.total_pago = (double) Math.round((aux) * 100d) / 100d;
    }

    public List<CarritoProducto> getLista_compras() {
        return lista_compras;
    }

    public void setLista_compras(List<CarritoProducto> lista_compras) {
        this.lista_compras = lista_compras;
    }

    public double getTotal_pago() {
        return total_pago;
    }

    public void setTotal_pago(double total_pago) {
        this.total_pago = total_pago;
    }

    public List<CarritoProducto> getLista_rentas() {
        return lista_rentas;
    }

    public void setLista_rentas(List<CarritoProducto> lista_rentas) {
        this.lista_rentas = lista_rentas;
    }

    public String getTipo(int tipo) {
        if (tipo == 1) {
            return "Pelicula";
        } else {
            return "Serie";
        }
    }

    public double getPrecioCompra(Productos idProducto) {
        double precio = 0;
        productFacade = new ProductosFacade();
        if (idProducto.getTipo() == 1) {
            precio = productFacade.buscarPelicula(idProducto.getIdProducto()).getPelicula().getPrecioCompra();
        } else {
            precio = productFacade.buscarSerie(idProducto.getIdProducto()).getSerie().getPrecioCompra();
        }
        return precio;
    }

    public double getPrecioRent(Productos idProducto) {
        double precio = 0;
        productFacade = new ProductosFacade();
        precio = productFacade.buscarPelicula(idProducto.getIdProducto()).getPelicula().getPrecioRenta();
        return precio;
    }

    public String getFecha() {
        Calendar fecha = Calendar.getInstance();
        Date now = fecha.getTime();
        fecha.add(Calendar.DAY_OF_YEAR, 30);
        Date agregado = fecha.getTime();
        return now + "";
    }

    public String getFechaEntrega() {
        Calendar fecha = Calendar.getInstance();
        Date now = fecha.getTime();
        fecha.add(Calendar.DAY_OF_YEAR, 30);
        Date agregado = fecha.getTime();
        return agregado + "";
    }
}
