package controlador;

import controlador.exceptions.RollbackFailureException;
import entidad.Usuarios;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

@Stateless
public class UsuariosFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("BlockbusterPU");
    private UserTransaction utx;
    private UsuariosJpaController userJpa = new UsuariosJpaController(emf);

    public boolean crearUsuario(UsuarioPojo usuario) {
        try {
            Usuarios usuarioN = userJpa.findByCorreo(usuario.getCorreo());
            if (usuarioN == null) {
                Usuarios user = new Usuarios();
                user.setColonia(usuario.getColonia());
                user.setMunicipio(usuario.getMunicipio());
                user.setCp(usuario.getCp());
                user.setContraseña(usuario.getContraseña());
                user.setApellidoM(usuario.getApellidoM());
                user.setApellidoP(usuario.getApellidoP());
                user.setCalle(usuario.getCalle());
                user.setCelular(usuario.getCelular());
                user.setIdUsuario(usuario.getIdUsuario());
                user.setNoExt(usuario.getNo_ext());
                user.setNoInt(usuario.getNo_int());
                user.setCorreo(usuario.getCorreo());
                user.setNombre(usuario.getNombre());
                user.setTelefonoFijo(usuario.getTelefono_fijo());
                user.setRol(usuario.getRol());
                userJpa.create(user);
                return true;
            }else{
                return false;
            }
        } catch (RollbackFailureException ex) {
            Logger.getLogger(UsuariosFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UsuariosFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void editarUsuario(Usuarios usuario) throws Exception {
        System.out.println(usuario.getIdUsuario());
        System.out.println(usuario.getCorreo());
        System.out.println(usuario.getCalle());
        System.out.println(usuario.getMunicipio());
        System.out.println(usuario.getIdUsuario());
        System.out.println(usuario.getApellidoP());
        System.out.println(usuario.getApellidoM());
        System.out.println(usuario.getCalle());

        userJpa.edit(usuario);
    }

    public UsuarioPojo buscarPorcorreo(String Correo) {
        UsuarioPojo user = new UsuarioPojo();
        Usuarios usuario = userJpa.findByCorreo(Correo);
        user.setContraseña(usuario.getContraseña());
        user.setCorreo(usuario.getCorreo());
        user.setRol(usuario.getRol());
        return user;
    }

    public Usuarios buscarPorcorreo2(String Correo) {
        UsuarioPojo user = new UsuarioPojo();
        Usuarios usuario = userJpa.findByCorreo(Correo);
        user.setContraseña(usuario.getContraseña());
        user.setCorreo(usuario.getCorreo());
        user.setRol(usuario.getRol());
        return usuario;
    }

    public boolean buscarUsuario(String correo, String contraseña) {
        Usuarios userPojo;
        boolean valido = false;

        userPojo = new Usuarios();

        userPojo = userJpa.findByCorreo(correo);

        System.out.println("Usuario hallado ");
        if (userPojo != null) {
            valido = validarUsuario(userPojo, contraseña);
            if (valido) {
                System.out.println("Es valido ********************************");
                return true;
            } else {
                System.out.println("No es valido");
                return false;
            }
        }
        return false;
    }

    public boolean validarUsuario(Usuarios user, String pwd) {
        String actLogin, actPwd;
        actPwd = user.getContraseña();
        if (actPwd.equals(pwd)) {
            return true;
        } else {
            return false;
        }
    }

    public UsuariosFacade() {

    }

}
