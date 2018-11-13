package modelo;

import controlador.UsuarioPojo;
import controlador.UsuariosFacade;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@Named(value = "registroBean")
@RequestScoped
public class RegistroBean {

    private int idUsuario;
    private String correo;
    private String contraseña;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String calle;
    private String colonia;
    private String municipio;
    private String cp;
    private String celular;
    private String telefono_fijo;
    private int no_int;
    private int no_ext;
    private String rol;
    private String confirmar;

    private UsuariosFacade usuarioFacade;
    private FacesContext fc = FacesContext.getCurrentInstance();
    private ExternalContext ec = fc.getExternalContext();

    public RegistroBean() {

    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefono_fijo() {
        return telefono_fijo;
    }

    public void setTelefono_fijo(String telefono_fijo) {
        this.telefono_fijo = telefono_fijo;
    }

    public int getNo_int() {
        return no_int;
    }

    public void setNo_int(int no_int) {
        this.no_int = no_int;
    }

    public int getNo_ext() {
        return no_ext;
    }

    public void setNo_ext(int no_ext) {
        this.no_ext = no_ext;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getConfirmar() {
        return confirmar;
    }

    public void setConfirmar(String confirmar) {
        this.confirmar = confirmar;
    }

    public void alta() {

        if (nombre.equals("")) {
            fc.addMessage("", new FacesMessage("Te falta escribir tu nombre"));
        } else if (contraseña.equals("")) {
            fc.addMessage("", new FacesMessage("Te falta escribir tu contraseña"));
        } else if (apellidoP.equals("")) {
            fc.addMessage("", new FacesMessage("Te falta escribir tu ap"));
        } else if (apellidoM.equals("")) {
            fc.addMessage("", new FacesMessage("Te falta escribir tu am"));
        } else if (correo.equals("")) {
            fc.addMessage("", new FacesMessage("Te falta escribir tu correo"));
        } else if (calle.equals("")) {
            fc.addMessage("", new FacesMessage("Te falta escribir tu pass"));
        } else if (no_int == 0) {
            fc.addMessage("", new FacesMessage("Te falta escribir tu numero interior"));
        } else if (no_ext == 0) {
            fc.addMessage("", new FacesMessage("Te falta escribir tu numero exteior"));
        } else if (cp.equals("")) {
            fc.addMessage("", new FacesMessage("Te falta escribir tu codigo postal"));
        } else if (celular.equals("")) {
            fc.addMessage("", new FacesMessage("Te falta escribir tu celular"));
        } else if (telefono_fijo.equals("")) {
            fc.addMessage("", new FacesMessage("Te falta escribir tu telefono fijo"));
        } else if (!contraseña.equals(confirmar)) {
            fc.addMessage("", new FacesMessage("Las contraseñas no coinciden"));
        } else {
            UsuarioPojo user = new UsuarioPojo();
            usuarioFacade = new UsuariosFacade();
            FacesContext context = FacesContext.getCurrentInstance();
            user.setColonia(colonia);
            user.setMunicipio(municipio);
            user.setCp(cp);
            user.setContraseña(contraseña);
            user.setApellidoM(apellidoM);
            user.setApellidoP(apellidoP);
            user.setCalle(calle);
            user.setCelular(celular);
            user.setIdUsuario(idUsuario);
            user.setNo_ext(no_ext);
            user.setNo_int(no_int);
            user.setCorreo(correo);
            user.setNombre(nombre);
            user.setTelefono_fijo(telefono_fijo);
            user.setRol("comprador");
            usuarioFacade.crearUsuario(user);
            System.out.println("yeahhhhhhhhhhhhhhhhhhhhhh");
            context.addMessage("", new FacesMessage("Se registro correctamente"));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Registro Existoso", "Información"));
            try {
                FacesContext contex = FacesContext.getCurrentInstance();
                contex.getExternalContext().redirect("/PelisPlus/faces/view/Login.xhtml");
            } catch (Exception e) {
                System.out.println("No voy");
            }
        }
    }
}
