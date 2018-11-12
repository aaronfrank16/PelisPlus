
package modelo;

import controlador.ProductoPojo;
import controlador.ProductosFacade;
import entidad.Categorias;
import entidad.Series;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

@Named(value = "descripcionSerieBean")
@RequestScoped
public class DescripcionSerieBean {

    private int idProducto;
    private Categorias idCategoria;
    private int tipo; //1.-Peliculas,2.-Serie
    private String titulo;
    private String sinopsis;
    private String año;
    private double rating;
    
    private Series serie;
    
    ProductosFacade productoFacade;
    FacesContext context = FacesContext.getCurrentInstance();
    
    public DescripcionSerieBean() {
        
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Categorias getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categorias idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Series getSerie() {
        return serie;
    }

    public void setSerie(Series serie) {
        this.serie = serie;
    }
            
    public String buscar_descripcion(int id) {
        productoFacade = new ProductosFacade();
        ProductoPojo productoPojo = productoFacade.buscarSerie(id);
        if (productoPojo != null) {
            setAño(productoPojo.getAño());
            setIdCategoria(productoPojo.getIdCategoria());
            setIdProducto(productoPojo.getIdProducto());
            setRating(productoPojo.getRating());
            setSinopsis(productoPojo.getSinopsis());
            setTipo(productoPojo.getTipo());
            setTitulo(productoPojo.getTitulo());
            setSerie(productoPojo.getSerie());
            return "descripcion_serie";
        } else {
            return "home";
        }
    }
    
}
