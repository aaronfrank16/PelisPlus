
package controlador;

import java.util.Date;

public class DatosPagoPojo {
    
    private int idDatosPago;
    private String tarjeta;
    private Date fecha_expiracion;
    private String codigo_seguridad;
    
    public DatosPagoPojo() {
        
    }

    public int getIdDatosPago() {
        return idDatosPago;
    }

    public void setIdDatosPago(int idDatosPago) {
        this.idDatosPago = idDatosPago;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public Date getFecha_expiracion() {
        return fecha_expiracion;
    }

    public void setFecha_expiracion(Date fecha_expiracion) {
        this.fecha_expiracion = fecha_expiracion;
    }

    public String getCodigo_seguridad() {
        return codigo_seguridad;
    }

    public void setCodigo_seguridad(String codigo_seguridad) {
        this.codigo_seguridad = codigo_seguridad;
    }
    
}
