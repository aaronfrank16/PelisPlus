
package entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "carrito_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CarritoProducto.findAll", query = "SELECT c FROM CarritoProducto c")
    , @NamedQuery(name = "CarritoProducto.findByIdCarritoProducto", query = "SELECT c FROM CarritoProducto c WHERE c.idCarritoProducto = :idCarritoProducto")
    , @NamedQuery(name = "CarritoProducto.findBySubtotal", query = "SELECT c FROM CarritoProducto c WHERE c.subtotal = :subtotal")
    , @NamedQuery(name = "CarritoProducto.findByCantidad", query = "SELECT c FROM CarritoProducto c WHERE c.cantidad = :cantidad")
    , @NamedQuery(name = "CarritoProducto.findByTipoProducto", query = "SELECT c FROM CarritoProducto c WHERE c.tipoProducto = :tipoProducto")
    , @NamedQuery(name = "CarritoProducto.findByTipoCompra", query = "SELECT c FROM CarritoProducto c WHERE c.tipoCompra = :tipoCompra")})
public class CarritoProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCarritoProducto")
    private Integer idCarritoProducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "subtotal")
    private double subtotal;
    @Column(name = "cantidad")
    private Integer cantidad;
    @JoinColumn(name = "idCarrito", referencedColumnName = "idCarrito")
    @ManyToOne(optional = false)
    private Carritos idCarrito;
    @JoinColumn(name = "idProducto", referencedColumnName = "idProducto")
    @ManyToOne(optional = false)
    private Productos idProducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_producto")
    private int tipoProducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_compra")
    private int tipoCompra;

    public CarritoProducto() {
    }

    public CarritoProducto(Integer idCarritoProducto) {
        this.idCarritoProducto = idCarritoProducto;
    }

    public CarritoProducto(Integer idCarritoProducto, double subtotal, int tipoProducto, int tipoCompra) {
        this.idCarritoProducto = idCarritoProducto;
        this.subtotal = subtotal;
        this.tipoProducto = tipoProducto;
        this.tipoCompra = tipoCompra;
    }

    public Integer getIdCarritoProducto() {
        return idCarritoProducto;
    }

    public void setIdCarritoProducto(Integer idCarritoProducto) {
        this.idCarritoProducto = idCarritoProducto;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public int getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(int tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public int getTipoCompra() {
        return tipoCompra;
    }

    public void setTipoCompra(int tipoCompra) {
        this.tipoCompra = tipoCompra;
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
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCarritoProducto != null ? idCarritoProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarritoProducto)) {
            return false;
        }
        CarritoProducto other = (CarritoProducto) object;
        if ((this.idCarritoProducto == null && other.idCarritoProducto != null) || (this.idCarritoProducto != null && !this.idCarritoProducto.equals(other.idCarritoProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.CarritoProducto[ idCarritoProducto=" + idCarritoProducto + " ]";
    }
    
}
