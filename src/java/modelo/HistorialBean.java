package modelo;

import controlador.ComprasFacade;
import controlador.DetalleCompraFacade;
import controlador.DetalleRentaFacade;
import controlador.RentasFacade;
import controlador.UsuariosFacade;
import entidad.Compras;
import entidad.DetalleCompra;
import entidad.DetalleRenta;
import entidad.Productos;
import entidad.Rentas;
import entidad.Usuarios;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@Named(value = "historialBean")
@RequestScoped
public class HistorialBean {

    private HttpSession session;

    DetalleRentaFacade detalleRFacade;
    DetalleCompraFacade detalleCFacade;

    ComprasFacade compraFacade;
    RentasFacade rentaFacade;

    private List<Compras> lista_compras;
    private List<Rentas> lista_rentas;

    private double total_compra;
    private double total_renta;

    public HistorialBean() {
        UsuariosFacade usuarioFacade = new UsuariosFacade();
        FacesContext context = FacesContext.getCurrentInstance();
        session = (HttpSession) context.getExternalContext().getSession(false);
        Usuarios idUser = usuarioFacade.buscarPorcorreo2(session.getAttribute("email").toString());

        compraFacade = new ComprasFacade();
        rentaFacade = new RentasFacade();
        this.lista_compras = compraFacade.listaCompras(idUser);
        this.lista_rentas = rentaFacade.listaRentas(idUser);
    }

    public List<Compras> getLista_compras() {
        return lista_compras;
    }

    public void setLista_compras(List<Compras> lista_compras) {
        this.lista_compras = lista_compras;
    }

    public List<Rentas> getLista_rentas() {
        return lista_rentas;
    }

    public void setLista_rentas(List<Rentas> lista_rentas) {
        this.lista_rentas = lista_rentas;
    }

    public double getTotal_compra() {
        return total_compra;
    }

    public void setTotal_compra(double total_compra) {
        this.total_compra = total_compra;
    }

    public double getTotal_renta() {
        return total_renta;
    }

    public void setTotal_renta(double total_renta) {
        this.total_renta = total_renta;
    }

    public String convertirFecha(Date fecha) {
        DateFormat dfDateInstance = DateFormat.getDateInstance();
        System.out.println("getDateInstance()=" + dfDateInstance.format(fecha));
        return dfDateInstance.format(fecha);
    }
    
    public String convertirTarjeta(String tarjeta){
        int longitud = tarjeta.length();
        String key = "";
        for (int i = 0; i < longitud-4; i++) {
            key = key +"*";
        }
        for (int i = longitud-4; i < longitud; i++) {
            key = key+tarjeta.charAt(i);
        }
        return key;
    }
}
