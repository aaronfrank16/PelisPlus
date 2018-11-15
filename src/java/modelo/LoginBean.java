package modelo;

import controlador.UsuarioPojo;
import controlador.UsuariosFacade;
import entidad.Usuarios;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable
{

    private String correo;
    private String contraseña;
    private int idCarrito = 0;

    private boolean activado;
    private boolean validado;
    private HttpSession session;
    private ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

    private UsuariosFacade usuarioFacade;
    private UsuarioPojo usuario = new UsuarioPojo();

    public LoginBean()
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
        UsuarioPojo user = new UsuarioPojo();
        user.setCorreo(correo);
        user.setContraseña(contraseña);
        
        usuario = usuarioFacade.buscarPorcorreo(correo);
        if (usuario != null)
        {
            activado = false;
            this.correo = usuario.getCorreo();
            this.contraseña = usuario.getContraseña();
        }
        
        cambioSesion();
        validado = true;
        session = (HttpSession) ec.getSession(false);
        session.setAttribute("validado", validado);
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        RequestContext context = RequestContext.getCurrentInstance();
        System.out.println("VOY A ENTRAR AL IF");
        if (request.isUserInRole("comprador"))
        {
            try
            {
                ec.redirect(ec.getRequestContextPath() + "/PelisPlus/faces/view/Home.xhtml");
                System.out.println("HOLA ESTOY EN EL IF");
            } catch (IOException ex)
            {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.out.println("VALIO VERGA EL IF");
        }
        
        //Desde aqui si funcionaba
//        FacesContext context = FacesContext.getCurrentInstance();
//        usuarioFacade = new UsuariosFacade();
//        setCorreo(correo);
//        if (correo.isEmpty() || contraseña.isEmpty())
//        {
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Los datos no son validos", "Error"));
//        } else
//        {
//            UsuarioPojo userPojo = usuarioFacade.buscarPorcorreo(correo);
//            if (userPojo != null)
//            {
//                activado = false;
//
//                cambioSesion();
//                validado = true;
//                session = (HttpSession) context.getExternalContext().getSession(true);
//                session.setAttribute("validado", validado);
//                HttpServletRequest request = (HttpServletRequest) ec.getRequest();
//                
//                if (request.isUserInRole("comprador"))
//                {
//                    try
//                    {
//                        ec.redirect(ec.getRequestContextPath() + "/PelisPlus/faces/view/Home.xhtml");
//                        context.getExternalContext().redirect("/PelisPlus/faces/view/Home.xhtml");
//                    } catch (IOException ex)
//                    {
//                        System.out.println("NO funcina");
//                    }
//                } else if (request.isUserInRole("almacenista"))
//                {
//                    try
//                    {
//                        ec.redirect(ec.getRequestContextPath() + "/PelisPlus/faces/view/Catalogo_Series.xhtml");
//                        context.getExternalContext().redirect("/PelisPlus/faces/view/Catalogo_Series.xhtml");
//                    } catch (IOException ex)
//                    {
//                        Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            }
        ///HAcia arriba si funcionaba
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
//        } 
    }

    public void cambioSesion()
    {
        session = (HttpSession) ec.getSession(false);

        System.out.println("Sesion nueva: " + session.isNew());
        System.out.println("id sesion: " + session.getId());
        session.invalidate();
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

    public String logOutUser()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tu no estas Logeado.", null));
        context.getExternalContext().invalidateSession();
        return "Login.xhtml?faces-redirect=true";
    }

    public boolean IsOnline(String email)
    {
        if (email.isEmpty())
        {
            return false;
        }
        return true;
    }

    public boolean IsOffline(String email)
    {
        if (email.isEmpty())
        {
            return true;
        }
        return false;
    }

}
