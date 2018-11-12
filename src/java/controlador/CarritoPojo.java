
package controlador;

import entidad.Productos;
import entidad.Usuarios;

public class CarritoPojo {
    
    private int idCarrito;
    private Usuarios idUsuario;
    private double total;

    public CarritoPojo() {
        
    }

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
