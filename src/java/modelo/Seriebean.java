/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.CategoriasFacade;
import controlador.PeliculasFacade;
import controlador.ProductosFacade;
import controlador.SeriesFacade;
import entidad.Categorias;
import entidad.Peliculas;
import entidad.Productos;
import entidad.Series;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author JuandeJesus
 */
@Named(value = "seriebean")
@RequestScoped
public class Seriebean {

     private String Titulo;
       private String Sinopsis;
       private String Año;
    private double rating;
     private int no_temp;
    private String estudio;
    private double precio_compra;
    private int id_prod;
    private int id_cat;
    private int tipo;
     private SeriesFacade usfac=new SeriesFacade();
    private ProductosFacade prodfac= new ProductosFacade();
    private CategoriasFacade catfac= new CategoriasFacade();
private FacesContext fc = FacesContext.getCurrentInstance();
 private Series seriec;
 private Productos prod;
 private Categorias cat;
 private ExternalContext ec = fc.getExternalContext();
    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getSinopsis() {
        return Sinopsis;
    }

    public void setSinopsis(String Sinopsis) {
        this.Sinopsis = Sinopsis;
    }

    public String getAño() {
        return Año;
    }

    public void setAño(String Año) {
        this.Año = Año;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNo_temp() {
        return no_temp;
    }

    public void setNo_temp(int no_temp) {
        this.no_temp = no_temp;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
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

    
   
    public Seriebean() {
    }
    
     public void alta(){
         System.out.println("Entra a metodo alta");
//       if(Titulo.equals("")) {
//           fc.addMessage("", new FacesMessage("Te falta escribir el titulo"));
//        }else if(tipo==0){
//            fc.addMessage("", new FacesMessage("Te falta escribir el tipo 1 o 2"));
//            
//        }else if(Sinopsis.equals("")){
//            
//            fc.addMessage("", new FacesMessage("Te falta escribir Sinopsis"));
//            
//        }else if(no_temp==0){
//           fc.addMessage("", new FacesMessage("Te falta escribir no temporadas"));
//        }else if(id_cat==0){   
//            fc.addMessage("", new FacesMessage("Te falta escribir el id_cat"));
//        }
//        else if(Año.equals("")){
//            fc.addMessage("", new FacesMessage("Te falta escribir el año"));
//        }
//        else if(rating==0){
//            fc.addMessage("", new FacesMessage("Te falta escribir el rating"));
//        }
//        else if(precio_compra==0){
//            fc.addMessage("", new FacesMessage("Te falta escribir la cantidad de almacen"));}
//        else if(estudio.equals("")){
//            fc.addMessage("", new FacesMessage("Te falta escribir la cantidad en renta"));
//        }
//        
//        else{
            cat= catfac.buscar_cat(id_cat);
           prod=new Productos();
           prod.setIdCategoria(cat);
           prod.setIdProducto(id_prod);
           prod.setAño(Año);
           prod.setTipo(tipo);
           prod.setSinopsis(Sinopsis);
          prod.setRating(rating);
           prod.setTitulo(Titulo);
           System.out.println(Titulo+"TITULOOOOOOOOOOOO");
            seriec=new Series();
            seriec.setNumeroTemporadas(no_temp);
            seriec.setPrecioCompra(precio_compra);
            seriec.setEstudio(estudio);
            seriec.setIdProducto(prod);
            
            try{
                prodfac.CrearP(prod);
                usfac.CrearSerie(seriec);
                System.out.println("Registro correcto");
            }catch (Exception e){
                
                System.out.println("No se ingreso");}
            
            
//        }
  
   }
    
}
