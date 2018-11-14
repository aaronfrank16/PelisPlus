/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author jair_
 */
@Named(value = "validaciones")
@RequestScoped
public class Validaciones
{
    /**
     * Creates a new instance of Validaciones
     */
    public Validaciones()
    {
    }
    
    public void validaCorreo(String cadena)
    {
        
    }
}
