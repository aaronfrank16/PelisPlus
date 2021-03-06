package modelo;

import controlador.ProductosFacade;
import entidad.Categorias;
import entidad.Productos;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "catalogoBean")
@RequestScoped
public class CatalogoBean {

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
    
    private List<Productos> categoria4;
    private List<Productos> categoria5;
    private List<Productos> categoria6;

    private List<Productos> pelis;
    private List<Productos> series;

    public CatalogoBean() {
        productoFacade = new ProductosFacade();
        setCategoria1(productoFacade.listaCategoria1());
        setCategoria2(productoFacade.listaCategoria2());
        setCategoria3(productoFacade.listaCategoria3());
        setCategoria4(productoFacade.listaCategoria4());
        setCategoria5(productoFacade.listaCategoria5());
        setCategoria6(productoFacade.listaCategoria6());
        
        setPelis(productoFacade.listaPelis());
        setSeries(productoFacade.listaSeries());
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

    public List<Productos> getPelis() {
        return pelis;
    }

    public void setPelis(List<Productos> pelis) {
        this.pelis = pelis;
    }

    public List<Productos> getSeries() {
        return series;
    }

    public void setSeries(List<Productos> series) {
        this.series = series;
    }

    public List<Productos> getCategoria4() {
        return categoria4;
    }

    public void setCategoria4(List<Productos> categoria4) {
        this.categoria4 = categoria4;
    }

    public List<Productos> getCategoria5() {
        return categoria5;
    }

    public void setCategoria5(List<Productos> categoria5) {
        this.categoria5 = categoria5;
    }

    public List<Productos> getCategoria6() {
        return categoria6;
    }

    public void setCategoria6(List<Productos> categoria6) {
        this.categoria6 = categoria6;
    }
    
    
}
