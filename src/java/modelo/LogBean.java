package modelo;

import controlador.CarritoProductoFacade;
import controlador.CarritosFacade;
import controlador.UsuarioPojo;
import controlador.UsuariosFacade;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.binary.Base64;

@Named(value = "logBean")
@RequestScoped
public class LogBean
{

    private String correo;
    private String contraseña;
    private int idCarrito = 0;

    private boolean activado;
    private boolean validado;
    private HttpSession session;
    private ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

    private UsuariosFacade usuarioFacade;
    private CarritosFacade carFacade;
    private CarritoProductoFacade carritoProductoFacade;

    public LogBean()
    {

    }

    public String getCorreo()
    {
        return correo;
    }

    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

    public String getContraseña()
    {
        return contraseña;
    }

    public void setContraseña(String contraseña)
    {
        this.contraseña = contraseña;
    }

    public int getIdCarrito()
    {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito)
    {
        this.idCarrito = idCarrito;
    }

    public void submit()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        usuarioFacade = new UsuariosFacade();
        setCorreo(correo);
        if (correo.isEmpty() || contraseña.isEmpty())
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Los datos no son validos", "Error"));
        } else
        {
            UsuarioPojo userPojo = usuarioFacade.buscarPorcorreo(correo);

            if (userPojo != null)
            {                
                if (!usuarioFacade.buscarUsuario(correo, contraseña))
                {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pass incorrecta", "Error"));
                } else
                {
                    
                    activado = false;

                    cambioSesion();
                    validado = true;
                    session = (HttpSession) context.getExternalContext().getSession(true);
                    session.setAttribute("validado", validado);
                    session.setAttribute("rol", userPojo.getRol());
                    session.setAttribute("email", userPojo.getCorreo());
                    session.setAttribute("idCarrito", 0);
                    HttpServletRequest request = (HttpServletRequest) ec.getRequest();
                    if (session.getAttribute("rol").equals("comprador"))
                    {
                        //if (request.isUserInRole("comprador")) {
                        try
                        {
                            FacesContext contex = FacesContext.getCurrentInstance();
                            contex.getExternalContext().redirect("/PelisPlus/faces/view/Home.xhtml");
                        } catch (IOException ex)
                        {
                            System.out.println("NO funcina");
                        }
                    } else if (request.isUserInRole("almacenista"))
                    {
                        try
                        {
                            ec.redirect(ec.getRequestContextPath() + "/Blockbuster/faces/view/Catalogo_Series.xhtml");
                            context.getExternalContext().redirect("/Blockbuster/faces/view/Catalogo_Series.xhtml");
                        } catch (IOException ex)
                        {
                            Logger.getLogger(LogBean.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
//                activado = false;
//                cambioSesion();
//                validado = true;
//                session = (HttpSession) context.getExternalContext().getSession(true);;
//                session.setAttribute("validado", validado);
//                HttpServletRequest request = (HttpServletRequest) ec.getRequest();
//                try {
//                    if (userPojo.getRol().equals("comprador")) {
//                        FacesContext contex = FacesContext.getCurrentInstance();
//                        contex.getExternalContext().redirect("/PelisPlus/faces/view/Home.xhtml");
//                        System.out.println("Holaaaa: "+session.getId());
//                    } else if (userPojo.getRol().equals("almacenista")) {
//
//                    }
//                } catch (Exception e) {
//                    System.out.println("No voy");
//                    System.out.println("HOLA: "+session.getId());
//                }
            }
        }
    }

    public void cambioSesion()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        session = (HttpSession) ec.getSession(false);

        System.out.println("Sesion nueva: " + session.isNew());
        System.out.println("id sesion: " + session.getId());
        session.invalidate();
        context.getExternalContext().invalidateSession();
        session = (HttpSession) ec.getSession(true);

        System.out.println("Sesion nueva: " + session.isNew());
        System.out.println("Id sesion en login: " + session.getId());
        //session = (HttpSession) ec.getSession(false);
        //System.out.println("Sesion nueva " + session.isNew());
        //System.out.println("Id sesion " + session.getId());
        //session.invalidate();
//        context.getExternalContext().invalidateSession();
//        session = (HttpSession) context.getExternalContext().getSession(true);
//        System.out.println("Sesion nueva " + session.isNew());
//        System.out.println("Id sesion " + session.getId());
    }

    public void validaSesion()
    {

    }

    public String logOutUser() throws Exception
    {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tu no estas Logeado.", null));
        vaciar();
        context.getExternalContext().invalidateSession();
        return "Login.xhtml?faces-redirect=true";
    }

    public void vaciar() throws Exception
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesContext context = FacesContext.getCurrentInstance();
        session = (HttpSession) context.getExternalContext().getSession(false);
        System.out.println(session.getId());
        //LoginBean neededBean = (LoginBean) facesContext.getApplication().createValueBinding("#{loginBean}").getValue(facesContext);
        if (session.getAttribute("idCarrito").equals(0))
        {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No tienes nada en el carrito", "Advertencia"));
        } else
        {
            carFacade = new CarritosFacade();
            carritoProductoFacade = new CarritoProductoFacade();
            carritoProductoFacade.remove(carritoProductoFacade.getCarrito(Integer.parseInt(session.getAttribute("idCarrito").toString())));
            carFacade.remove(Integer.parseInt(session.getAttribute("idCarrito").toString()));
            session.setAttribute("idCarrito", 0);
        }
    }

    public boolean IsOnline()
    {
        try
        {
            FacesContext context = FacesContext.getCurrentInstance();
            session = (HttpSession) context.getExternalContext().getSession(false);
            if (!(boolean) session.getAttribute("validado"))
            {
                return false;
            }
            return true;
        } catch (NullPointerException e)
        {
            return false;
        }
    }

    public boolean IsOffline()
    {
        try
        {
            FacesContext context = FacesContext.getCurrentInstance();
            session = (HttpSession) context.getExternalContext().getSession(false);
            if (!(boolean) session.getAttribute("validado"))
            {
                return true;
            }
            return false;
        } catch (NullPointerException e)
        {
            return true;
        }
    }

    public String getCorreoSesion()
    {
        session = (HttpSession) ec.getSession(false);
        return session.getAttribute("email").toString();
    }

    public void validaPagina() throws IOException
    {
        HttpSession session = (HttpSession) ec.getSession(false);
        FacesContext context = FacesContext.getCurrentInstance();
        try
        {
            if (!(boolean) session.getAttribute("validado"))
            {
                ec.redirect(ec.getRequestContextPath() + "/faces/view/Login.xhtml");
            } else
            {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente", "Informacion"));
//            this.rol = (HttpSession) session.getAttribute("rol");
//            this.ap = (HttpSession) session.getAttribute("ap");
//            this.ap = (HttpSession) session.getAttribute("ap");
//            this.email = (HttpSession) session.getAttribute("email");
            }
        } catch (NullPointerException e)
        {
            ec.redirect(ec.getRequestContextPath() + "/faces/view/Login.xhtml");
        }
    }

    
}
