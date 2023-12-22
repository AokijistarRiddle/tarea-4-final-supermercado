package marketinator3000.modelo.almacen;

import java.io.Serializable;

public class Producto implements Serializable{
    
    private String sku;
    private String nombre;
    private double precio;
    private int cantidad;
    private double peso;
    private int defecto;
    private int stockMinimo;

    public Producto(String sku, String nombre, double precio, int cantidad, double peso, int defecto, int stockMinimo){
        this.sku = sku;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.peso = peso;
        this.defecto = defecto ;
        this.stockMinimo = stockMinimo;
    }

    public void setStockMinimo(int stockMinimo){
        this.stockMinimo = stockMinimo;
    }
    public int getStockMinimo(){
        return stockMinimo;
    }
    public void setDefecto(int defecto){
        this.defecto = defecto;
    }
    public int getDefecto(){ 
        return defecto;
    }   
    public void setSku(String sku){
        this.sku = sku;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setPrecio(double precio) throws IllegalArgumentException{
	if (precio < 0){
	    throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
	}
	this.precio = precio;
    }
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    public void setPeso(double peso){
        this.peso = peso;
    }

    public String getSku(){
        return sku;
    }
    public String getNombre(){
        return nombre;
    }
    public double getPrecio(){
        return precio;
    }
    public int getCantidad(){
        return cantidad;    
    }
    public double getPeso(){
        return peso;
    }

    //METODOS
    public void restaCantidad(int cantidad){
        if(this.cantidad - cantidad < 0){
            System.out.println("No hay suficiente producto parea realizar tu peticion");
        }
        else {
            this.cantidad -= cantidad;
        }
    }

    @Override
    public String toString(){
        return String.format("%15s%30s%15s%15s%n", this.sku, this.nombre, this.cantidad, this.precio +"MXN");
    }
    
    public void aumentaCantidad(int cantidad){
        this.cantidad += cantidad;
    }

/*
 



*/

}
