/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidad.DatosPago;
import entidad.Usuarios;
import java.util.Date;


public class RentaPojo {
    private int idRenta;
    private Usuarios idUsuario;
    private double total_renta;
    private Date fecha_renta;
    private Date fecha_entrega;
    private Date fecha_devolucion;
    private DatosPago idDatosPago;

    public RentaPojo() {
        
    }

    public int getIdRenta() {
        return idRenta;
    }

    public void setIdRenta(int idRenta) {
        this.idRenta = idRenta;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getTotal_renta() {
        return total_renta;
    }

    public void setTotal_renta(double total_renta) {
        this.total_renta = total_renta;
    }

    public Date getFecha_renta() {
        return fecha_renta;
    }

    public void setFecha_renta(Date fecha_renta) {
        this.fecha_renta = fecha_renta;
    }

    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public Date getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(Date fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public DatosPago getIdDatosPago() {
        return idDatosPago;
    }

    public void setIdDatosPago(DatosPago idDatosPago) {
        this.idDatosPago = idDatosPago;
    }
    
    
}
