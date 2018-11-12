package modelo;

import controlador.ProductoPojo;
import controlador.ProductosFacade;
import entidad.Categorias;
import entidad.Peliculas;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "descripcionBean")
@RequestScoped
public class DescripcionBean {

    private int idProducto;
    private Categorias idCategoria;
    private int tipo; //1.-Peliculas,2.-Serie
    private String titulo;
    private String sinopsis;
    private String año;
    private double rating;
    
    private Peliculas pelicula;

    ProductosFacade productoFacade;
    FacesContext context = FacesContext.getCurrentInstance();
            

    public DescripcionBean() {

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

    public Peliculas getPelicula() {
        return pelicula;
    }

    public void setPelicula(Peliculas pelicula) {
        this.pelicula = pelicula;
    }

    public String buscar_descripcion(int id) {
        productoFacade = new ProductosFacade();
        ProductoPojo productoPojo = productoFacade.buscarPelicula(id);
        if (productoPojo != null) {
            setAño(productoPojo.getAño());
            setIdCategoria(productoPojo.getIdCategoria());
            setIdProducto(productoPojo.getIdProducto());
            setRating(productoPojo.getRating());
            setSinopsis(productoPojo.getSinopsis());
            setTipo(productoPojo.getTipo());
            setTitulo(productoPojo.getTitulo());
            setPelicula(productoPojo.getPelicula());
            return "descripcion_pelicula";
        } else {
            return "home";
        }
    }

}
