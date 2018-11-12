/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.CategoriasFacade;
import controlador.PeliculasFacade;
import controlador.ProductosFacade;
import entidad.Categorias;
import entidad.Peliculas;
import entidad.Productos;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author JuandeJesus
 */
@Named(value = "peliculabean")
@RequestScoped
public class peliculabean {
   
    private String Titulo;
    private String Director;
    private String Duracion;
    private String Clasificacion;
    private int Cantidad_almacen;
    private int cantidad_renta;
    private double precio_renta;
    private double precio_compra;

    private int tipo;
    private String Sinopsis;
    private String Ano;
    private String Año;
    private String tit2;
    private String sin;
private double rating;
    private int id_prod;
    private int id_cat;

    private PeliculasFacade usfac=new PeliculasFacade();
    private ProductosFacade prodfac= new ProductosFacade();
    private CategoriasFacade catfac= new CategoriasFacade();
private FacesContext fc = FacesContext.getCurrentInstance();
 private Peliculas peliculac;
 private Productos prod;
 private Categorias cat;
 private ExternalContext ec = fc.getExternalContext();

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String Director) {
        this.Director = Director;
    }

    public String getDuracion() {
        return Duracion;
    }

    public void setDuracion(String Duracion) {
        this.Duracion = Duracion;
    }

    public String getClasificacion() {
        return Clasificacion;
    }

    public void setClasificacion(String Clasificacion) {
        this.Clasificacion = Clasificacion;
    }

    public int getCantidad_almacen() {
        return Cantidad_almacen;
    }

    public void setCantidad_almacen(int Cantidad_almacen) {
        this.Cantidad_almacen = Cantidad_almacen;
    }

    public int getCantidad_renta() {
        return cantidad_renta;
    }

    public void setCantidad_renta(int cantidad_renta) {
        this.cantidad_renta = cantidad_renta;
    }

    public double getPrecio_renta() {
        return precio_renta;
    }

    public void setPrecio_renta(double precio_renta) {
        this.precio_renta = precio_renta;
    }

    public double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(double precio_compra) {
        this.precio_compra = precio_compra;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getSinopsis() {
        return Sinopsis;
    }

    public void setSinopsis(String Sinopsis) {
        this.Sinopsis = Sinopsis;
    }

    public String getAno() {
        return Ano;
    }

    public void setAno(String Ano) {
        this.Ano = Ano;
    }

    public String getAño() {
        return Año;
    }

    public void setAño(String Año) {
        this.Año = Año;
    }

    public String getTit2() {
        return tit2;
    }

    public void setTit2(String tit2) {
        this.tit2 = tit2;
    }

    public String getSin() {
        return sin;
    }

    public void setSin(String sin) {
        this.sin = sin;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public Peliculas getPeliculac() {
        return peliculac;
    }

    public void setPeliculac(Peliculas peliculac) {
        this.peliculac = peliculac;
    }
    
    public peliculabean() {
    }
    
    public void gend(){
    cat= catfac.buscar_cat(id_cat);
           prod=new Productos();
           prod.setIdCategoria(cat);
           
           prod.setIdProducto(id_prod);
           prod.setAño(Año);
           System.out.println(this.Año+"AAAAAAAAAAAAAANNNNNNNNNNNNNNOOOOOOOOO");
           prod.setTipo(tipo);
           prod.setSinopsis(sin);
           System.out.println(this.sin+"SSSSSSSSSSSSSSSSSIIIIIIIIIIIINNNNNNNNNNNNNNOOOOOOOOOOPPPPPPPPPSSSSSSSSSSSSs");
           prod.setRating(rating);
           prod.setTitulo(Titulo);
           System.out.println(this.Titulo+"TTTTTTTTTTTTTIIIIIIIIIIIIITTTTTTUUUUUUUUUUUUULLLLLLOOOOOOOO");
            peliculac=new Peliculas();
            peliculac.setDirector(Director);
            peliculac.setClasificacion(Clasificacion);
            peliculac.setDuracion(Duracion);
            peliculac.setCantidadAlmacen(Cantidad_almacen);
            peliculac.setCantidadRenta(cantidad_renta);
            peliculac.setPrecioCompra(precio_compra);
            peliculac.setPrecioRenta(precio_renta);
            peliculac.setIdProducto(prod);
    alta();
    }
   public void alta(){
      try{ 
//       if(Titulo.equals("")) {
//           fc.addMessage("", new FacesMessage("Te falta escribir el titulo"));
//        }else if(tipo==0){
//            fc.addMessage("", new FacesMessage("Te falta escribir el tipo 1 o 2"));
//            
//        }else if(Director.equals("")){
//            
//            fc.addMessage("", new FacesMessage("Te falta escribir el Director"));
//            
//        }else if(Sinopsis.equals("")){
//            fc.addMessage("", new FacesMessage("Te falta escribir la sinopsis"));
//            
//            
//        }else if(Duracion.equals("")){
//            fc.addMessage("", new FacesMessage("Te falta escribir la duracion"));
//        }else if(Clasificacion.equals("")){
//            
//            fc.addMessage("", new FacesMessage("Te falta escribir la clasificacion"));}
//        else if(ano.equals("")){
//            fc.addMessage("", new FacesMessage("Te falta escribir el año"));
//        }
//        else if(rating==0){
//            fc.addMessage("", new FacesMessage("Te falta escribir el rating"));
//        }
//        else if(Cantidad_almacen==0){
//            fc.addMessage("", new FacesMessage("Te falta escribir la cantidad de almacen"));}
//        else if(cantidad_renta==0){
//            fc.addMessage("", new FacesMessage("Te falta escribir la cantidad en renta"));
//        }
//        else if(precio_compra==0){
//            fc.addMessage("", new FacesMessage("Te falta escribir el precio de compra"));
//        }
//        else if(precio_renta==0){
//            
//            fc.addMessage("", new FacesMessage("Te falta escribir el precio de renta"));
//        }
//        else{
            
            
            try{
                prodfac.CrearP(prod);
                usfac.crearPelicula(peliculac);
                System.out.println("Registro correcto");
            }catch (Exception e){
                
                System.out.println("No se ingreso");}
            
            
//        }
   }catch (Exception e){
        
          System.out.println(e.getMessage());
          
        }
   }    
}
