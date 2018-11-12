package modelo;

import controlador.CarritoProductoFacade;
import entidad.CarritoProducto;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author aaron
 */
@Named(value = "listaCarritoBean")
@RequestScoped
public class ListaCarritoBean {

    private List<CarritoProducto> lista_compras;
    private double total_pago;
    
    private CarritoProductoFacade carritoPFacade;

    public ListaCarritoBean() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        LoginBean neededBean = (LoginBean) facesContext.getApplication().createValueBinding("#{loginBean}").getValue(facesContext);
        carritoPFacade = new CarritoProductoFacade();
        this.lista_compras = (carritoPFacade.filtrar(carritoPFacade.getCarrito(neededBean.getIdCarrito())));
        double aux = 0;
        try {
            aux = carritoPFacade.getCarrito(neededBean.getIdCarrito()).getTotal();
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
    
    public String getTipo(int tipo){
        if (tipo==1) {
            return "Pelicula";
        }else{
            return "Serie";
        }
    }
}
