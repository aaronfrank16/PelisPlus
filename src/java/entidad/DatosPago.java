/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author aaron
 */
@Entity
@Table(name = "datos_pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatosPago.findAll", query = "SELECT d FROM DatosPago d")
    , @NamedQuery(name = "DatosPago.findByIdDatosPago", query = "SELECT d FROM DatosPago d WHERE d.idDatosPago = :idDatosPago")
    , @NamedQuery(name = "DatosPago.findByTarjeta", query = "SELECT d FROM DatosPago d WHERE d.tarjeta = :tarjeta")
    , @NamedQuery(name = "DatosPago.findByFechaExpiracion", query = "SELECT d FROM DatosPago d WHERE d.fechaExpiracion = :fechaExpiracion")
    , @NamedQuery(name = "DatosPago.findByCodigoEsguridad", query = "SELECT d FROM DatosPago d WHERE d.codigoEsguridad = :codigoEsguridad")})
public class DatosPago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDatosPago")
    private Integer idDatosPago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tarjeta")
    private String tarjeta;
    @Basic(optional = false)
    @Null
    @Column(name = "fecha_expiracion")
    @Temporal(TemporalType.DATE)
    private Date fechaExpiracion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "codigo_esguridad")
    private String codigoEsguridad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDatosPago")
    private List<Rentas> rentasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDatosPago")
    private List<Compras> comprasList;

    public DatosPago() {
    }

    public DatosPago(Integer idDatosPago) {
        this.idDatosPago = idDatosPago;
    }

    public DatosPago(Integer idDatosPago, String tarjeta, Date fechaExpiracion, String codigoEsguridad) {
        this.idDatosPago = idDatosPago;
        this.tarjeta = tarjeta;
        this.fechaExpiracion = fechaExpiracion;
        this.codigoEsguridad = codigoEsguridad;
    }

    public Integer getIdDatosPago() {
        return idDatosPago;
    }

    public void setIdDatosPago(Integer idDatosPago) {
        this.idDatosPago = idDatosPago;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getCodigoEsguridad() {
        return codigoEsguridad;
    }

    public void setCodigoEsguridad(String codigoEsguridad) {
        this.codigoEsguridad = codigoEsguridad;
    }

    @XmlTransient
    public List<Rentas> getRentasList() {
        return rentasList;
    }

    public void setRentasList(List<Rentas> rentasList) {
        this.rentasList = rentasList;
    }

    @XmlTransient
    public List<Compras> getComprasList() {
        return comprasList;
    }

    public void setComprasList(List<Compras> comprasList) {
        this.comprasList = comprasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDatosPago != null ? idDatosPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatosPago)) {
            return false;
        }
        DatosPago other = (DatosPago) object;
        if ((this.idDatosPago == null && other.idDatosPago != null) || (this.idDatosPago != null && !this.idDatosPago.equals(other.idDatosPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.DatosPago[ idDatosPago=" + idDatosPago + " ]";
    }
    
}
