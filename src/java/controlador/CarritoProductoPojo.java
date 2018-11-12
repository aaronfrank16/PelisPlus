
package controlador;

import entidad.Carritos;
import entidad.Productos;

public class CarritoProductoPojo {
    
    private int idCarritoProducto;
    private Carritos idCarrito;
    private Productos idProducto;
    private double subtotal;
    private int cantidad;
    private int tipo_producto;
    private int tipo_compra;

    public CarritoProductoPojo() {
        
    }

    public int getIdCarritoProducto() {
        return idCarritoProducto;
    }

    public void setIdCarritoProducto(int idCarritoProducto) {
        this.idCarritoProducto = idCarritoProducto;
    }

    public Carritos getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Carritos idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getTipo_producto() {
        return tipo_producto;
    }

    public void setTipo_producto(int tipo_producto) {
        this.tipo_producto = tipo_producto;
    }

    public int getTipo_compra() {
        return tipo_compra;
    }

    public void setTipo_compra(int tipo_compra) {
        this.tipo_compra = tipo_compra;
    }
}
