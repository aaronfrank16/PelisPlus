/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.CarritoProductoFacade;
import controlador.CarritosFacade;
import entidad.Usuarios;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author jair_
 */
@Named(value = "rentaPeliculaBean")
@RequestScoped
public class rentaPeliculaBean
{

    /**
     * Creates a new instance of rentaPeliculaBean
     */
    
    private int idCarrito;
    private Usuarios idUsuario;
    private double total;

    private CarritosFacade carritoFacade;
    private CarritoProductoFacade carProductFacade;
    public rentaPeliculaBean()
    {
    }

    public int getIdCarrito()
    {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito)
    {
        this.idCarrito = idCarrito;
    }

    public Usuarios getIdUsuario()
    {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario)
    {
        this.idUsuario = idUsuario;
    }

    public double getTotal()
    {
        return total;
    }

    public void setTotal(double total)
    {
        this.total = total;
    }
    
    
    
    public void crerRenta()
    {
        
    }
}
