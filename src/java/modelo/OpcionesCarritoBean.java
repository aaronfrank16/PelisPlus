package modelo;

import controlador.CarritoProductoFacade;
import controlador.CarritosFacade;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@Named(value = "opcionesCarritoBean")
@RequestScoped
public class OpcionesCarritoBean {

    private CarritosFacade carFacade;
    private CarritoProductoFacade carritoProductoFacade;
    
    private HttpSession session;
    private ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    
    public OpcionesCarritoBean() {

    }

    public String vaciar() throws Exception {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesContext context = FacesContext.getCurrentInstance();
        session = (HttpSession) context.getExternalContext().getSession(false);
        System.out.println(session.getId());
        //LoginBean neededBean = (LoginBean) facesContext.getApplication().createValueBinding("#{loginBean}").getValue(facesContext);
        if (session.getAttribute("idCarrito").equals(0)) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No tienes nada en el carrito", "Advertencia"));
        } else {
            carFacade = new CarritosFacade();
            carritoProductoFacade = new CarritoProductoFacade();
            carritoProductoFacade.remove(carritoProductoFacade.getCarrito(Integer.parseInt(session.getAttribute("idCarrito").toString())));
            carFacade.remove(Integer.parseInt(session.getAttribute("idCarrito").toString()));
            session.setAttribute("idCarrito", 0);
        }
        return "Home";
    }

    public void confirmar() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesContext context = FacesContext.getCurrentInstance();
        session = (HttpSession) context.getExternalContext().getSession(false);
        if (session.getAttribute("idCarrito").equals(0)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No tienes nada en el carrito", "Información"));
        }else{
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect(ec.getRequestContextPath() + "/faces/view/confirmar.xhtml");
        }
    }

}
