
package controlador;

import entidad.DatosPago;
import entidad.Usuarios;
import java.util.Date;

public class ComprasPojo {
    
    private int idCompras;
    private Usuarios idUsuario;
    private double total_compra;
    private DatosPago idDatosPago;
    private Date fecha_compra;
    private Date fecha_entrega;

    public ComprasPojo() {
        
    }

    public int getIdCompras() {
        return idCompras;
    }

    public void setIdCompras(int idCompras) {
        this.idCompras = idCompras;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getTotal_compra() {
        return total_compra;
    }

    public void setTotal_compra(double total_compra) {
        this.total_compra = total_compra;
    }

    public DatosPago getIdDatosPago() {
        return idDatosPago;
    }

    public void setIdDatosPago(DatosPago idDatosPago) {
        this.idDatosPago = idDatosPago;
    }

    public Date getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(Date fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }
    
    
}
