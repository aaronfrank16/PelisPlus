/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author aaron
 */
@Entity
@Table(name = "carritos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carritos.findAll", query = "SELECT c FROM Carritos c")
    , @NamedQuery(name = "Carritos.findByIdCarrito", query = "SELECT c FROM Carritos c WHERE c.idCarrito = :idCarrito")
    , @NamedQuery(name = "Carritos.findByTotal", query = "SELECT c FROM Carritos c WHERE c.total = :total")})
public class Carritos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCarrito")
    private Integer idCarrito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private double total;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCarrito")
    private List<CarritoProducto> carritoProductoList;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;

    public Carritos() {
    }

    public Carritos(Integer idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Carritos(Integer idCarrito, double total) {
        this.idCarrito = idCarrito;
        this.total = total;
    }

    public Integer getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Integer idCarrito) {
        this.idCarrito = idCarrito;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @XmlTransient
    public List<CarritoProducto> getCarritoProductoList() {
        return carritoProductoList;
    }

    public void setCarritoProductoList(List<CarritoProducto> carritoProductoList) {
        this.carritoProductoList = carritoProductoList;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCarrito != null ? idCarrito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carritos)) {
            return false;
        }
        Carritos other = (Carritos) object;
        if ((this.idCarrito == null && other.idCarrito != null) || (this.idCarrito != null && !this.idCarrito.equals(other.idCarrito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.Carritos[ idCarrito=" + idCarrito + " ]";
    }
    
}
