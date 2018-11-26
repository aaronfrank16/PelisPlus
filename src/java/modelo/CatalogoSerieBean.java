
package modelo;

import controlador.ProductosFacade;
import entidad.Categorias;
import entidad.Productos;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "catalogoSerieBean")
@RequestScoped
public class CatalogoSerieBean {

    private int idProducto;
    private Categorias idCategoria;
    private int tipo; //1.-Peliculas,2.-Serie
    private String titulo;
    private String sinopsis;
    private String año;
    private double rating;
    
    ProductosFacade productoFacade;
    
    private List<Productos> categoria1;
    private List<Productos> categoria2;
    private List<Productos> categoria3;
    private List<Productos> seriesbuscar;

    public List<Productos> getSeriesbuscar() {
        return seriesbuscar;
    }

    public void setSeriesbuscar(List<Productos> seriesbuscar) {
        this.seriesbuscar = seriesbuscar;
    }
    
    public CatalogoSerieBean() {
        productoFacade = new ProductosFacade();
        setCategoria1(productoFacade.listaCategoriaSerie1());
        setCategoria2(productoFacade.listaCategoriaSerie5());
        setCategoria3(productoFacade.listaCategoriaSerie2());
       
        
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

    public List<Productos> getCategoria1() {
        return categoria1;
    }

    public void setCategoria1(List<Productos> categoria1) {
        this.categoria1 = categoria1;
    }

    public List<Productos> getCategoria2() {
        return categoria2;
    }

    public void setCategoria2(List<Productos> categoria2) {
        this.categoria2 = categoria2;
    }

    public List<Productos> getCategoria3() {
        return categoria3;
    }

    public void setCategoria3(List<Productos> categoria3) {
        this.categoria3 = categoria3;
    }
    
    
    
}
