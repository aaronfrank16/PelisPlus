package modelo;

import controlador.CarritoProductoFacade;
import controlador.CarritosFacade;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "opcionesCarritoBean")
@RequestScoped
public class OpcionesCarritoBean {

    private CarritosFacade carFacade;
    private CarritoProductoFacade carritoProductoFacade;
    
    public OpcionesCarritoBean() {

    }

    public String vaciar() throws Exception {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        LoginBean neededBean = (LoginBean) facesContext.getApplication().createValueBinding("#{loginBean}").getValue(facesContext);
        if (neededBean.getIdCarrito() == 0) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No tienes nada en el carrito", "Advertencia"));
        } else {
            carFacade = new CarritosFacade();
            carritoProductoFacade = new CarritoProductoFacade();
            carritoProductoFacade.remove(carritoProductoFacade.getCarrito(neededBean.getIdCarrito()));
            carFacade.remove(neededBean.getIdCarrito());
            neededBean.setIdCarrito(0);
        }
        return "Home";
    }

    public String confirmar() {
        return "confirmar";
    }

}
