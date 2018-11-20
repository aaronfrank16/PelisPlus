package modelo;

import controlador.CarritoProductoFacade;
import controlador.CarritosFacade;
import controlador.ComprasFacade;
import controlador.ComprasPojo;
import controlador.DatosPagoFacade;
import controlador.DatosPagoPojo;
import controlador.RentaPojo;
import controlador.RentasFacade;
import controlador.UsuariosFacade;
import entidad.CarritoProducto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@Named(value = "datosPagoBean")
@RequestScoped
public class DatosPagoBean {

    private int idDatosPago;
    private String tarjeta;
    private Date fecha_expiracion;
    private String codigo_seguridad;
    private String contraseña;

    private HttpSession session;
    private ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

    private DatosPagoFacade datosFacade;
    private ComprasFacade comprasFacade;
    private RentasFacade rentasFacade;

    private CarritosFacade carritoFacade;
    private CarritoProductoFacade carritoProductoFacade;

    public DatosPagoBean() {

    }

    public int getIdDatosPago() {
        return idDatosPago;
    }

    public void setIdDatosPago(int idDatosPago) {
        this.idDatosPago = idDatosPago;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public Date getFecha_expiracion() {
        return fecha_expiracion;
    }

    public void setFecha_expiracion(Date fecha_expiracion) {
        this.fecha_expiracion = fecha_expiracion;
    }

    public String getCodigo_seguridad() {
        return codigo_seguridad;
    }

    public void setCodigo_seguridad(String codigo_seguridad) {
        this.codigo_seguridad = codigo_seguridad;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String confirmation() throws Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        session = (HttpSession) context.getExternalContext().getSession(false);
        UsuariosFacade usuarioFacade = new UsuariosFacade();
        System.out.println("Entre en confirmation------------------------");
        if (contraseña.equals(usuarioFacade.buscarPorcorreo(session.getAttribute("email").toString()).getContraseña())) {
            System.out.println("Entre a esta madre");
            DatosPagoPojo datosPojo = new DatosPagoPojo();
            datosFacade = new DatosPagoFacade();
            datosPojo.setCodigo_seguridad(codigo_seguridad);
            datosPojo.setFecha_expiracion(fecha_expiracion);
            datosPojo.setIdDatosPago(idDatosPago);
            datosPojo.setTarjeta(tarjeta);
            int idCar = datosFacade.registro(datosPojo);

            //Vaciar carrito en detalle-compras y detalle-rentas
            comprasFacade = new ComprasFacade();
            rentasFacade = new RentasFacade();

            carritoFacade = new CarritosFacade();
            carritoProductoFacade = new CarritoProductoFacade();

            ComprasPojo compraPojo = new ComprasPojo();
            RentaPojo rentaPojo = new RentaPojo();

            List<CarritoProducto> lista_compras = carritoProductoFacade.filtrar(carritoProductoFacade.getCarrito((int) session.getAttribute("idCarrito")));

            List<CarritoProducto> lista_rentas = carritoProductoFacade.filtrarR(carritoProductoFacade.getCarrito((int) session.getAttribute("idCarrito")));

            if (!lista_rentas.isEmpty()) {
                System.out.println("rentas vacio");
                Calendar fecha = Calendar.getInstance();
                Date now = fecha.getTime();
                fecha.add(Calendar.DAY_OF_YEAR, 30);
                Date agregado = fecha.getTime();
                fecha.add(Calendar.DAY_OF_YEAR, 10);
                Date agregado2 = fecha.getTime();
                rentaPojo.setIdRenta(1);
                rentaPojo.setFecha_renta(now);
                rentaPojo.setFecha_devolucion(agregado2);
                rentaPojo.setFecha_entrega(agregado);
                rentaPojo.setIdDatosPago(datosFacade.getDatosPago(idCar));
                rentaPojo.setIdUsuario(usuarioFacade.buscarPorcorreo2(session.getAttribute("email").toString()));
                rentaPojo.setTotal_renta(carritoProductoFacade.getCarrito((int) session.getAttribute("idCarrito")).getTotal());
                rentasFacade.create(rentaPojo);
            }
            if (!lista_compras.isEmpty()) {
                System.out.println("Compras vacio");
                Calendar fecha = Calendar.getInstance();
                Date now = fecha.getTime();
                fecha.add(Calendar.DAY_OF_YEAR, 30);
                Date agregado = fecha.getTime();
                compraPojo.setIdCompras(1);
                compraPojo.setFecha_compra(now);
                compraPojo.setFecha_entrega(agregado);
                compraPojo.setIdDatosPago(datosFacade.getDatosPago(idCar));
                compraPojo.setIdUsuario(usuarioFacade.buscarPorcorreo2(session.getAttribute("email").toString()));
                compraPojo.setTotal_compra(carritoProductoFacade.getCarrito((int) session.getAttribute("idCarrito")).getTotal());
                comprasFacade.create(compraPojo);
            }
            return "Historial-Usuario";
        } else {
            System.out.println("la contraseña no coincide");
            return "carrito";
        }
    }

}
