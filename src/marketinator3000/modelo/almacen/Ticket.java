package marketinator3000.modelo.almacen;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.HashMap;

import marketinator3000.modelo.cajas.Caja;

public class Ticket{
    /**
     * Atributos del almacen
     */
    private int venta;
    private HashMap<String,Integer> productos;
    private String fecha;
    private Caja caja;
    private Almacen almacen;
    /**
     * Constructor del almacen
     * @param productos El mapeo de los productos en el carrito de un cliente
     * @param almacen El almacen del modelo
     */
    public Ticket(HashMap<String,Integer> productos, Almacen almacen, Caja caja){
        this.productos = productos;
        LocalDateTime dia = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.fecha = dia.format(formato);
        this.almacen = almacen;
        this.caja = caja;
    }
    /**
     * Metodo que fija la venta
     * @param venta La venta que se dio
     */
    public void setVenta(int venta){
        this.venta = venta;
    }
    /**
     * Metodo que devuelve la venta
     * @return La venta del carrito
     */
    public int getVenta(){
        return this.venta;
    }
    public HashMap<String,Integer> getProductos(){
        return this.productos;
    }
    public String getFecha(){
        return this.fecha;
    }
    /**
     * Metodo que obtiene el total de productos vendidos, por sku, los multiplica por su precio y devuelve el total de compra
     * @return El total de compra
     */
    public double total(){
        double suma = 0;
        for (String sku : productos.keySet()){
            suma += almacen.obtenerTodosProductos().get(sku).getPrecio() * productos.get(sku);
        }
        return suma;
    }
    /**
     * Metodo que obtiene y devuelve el subtotal de venta (venta-(venta*iva))
     * @return el subtotal de compra
     */
    public double subtotal(){
        return total() * 0.84;
    }
    /**
     * Metodo que 
     */
    public double iva(){
        return total() * 0.16;
    }

    @Override
    public String toString(){
        String resultado = "Ticket de compra del día " + fecha + "\n";
        resultado += "atendió: " + caja.getIdentificador() + "\n";
        resultado += String.format("%15s%15s%30s%15s%15s%n","Sku","Cantidad","Nombre","Precio","Total");
        resultado += "--------------------------------------------------------------------------------\n";
        for (String sku : productos.keySet()){
            int cantidad = productos.get(sku);
            Producto producto = almacen.obtenerTodosProductos().get(sku);
            String nombre = producto.getNombre();
            double precio = producto.getPrecio();
            double total = precio * (double)cantidad;
            resultado += String.format("%15s%15s%30s%15s%15.2f%n",sku,cantidad,nombre,precio,total);
        }
        resultado += "--------------------------------------------------------------------------------\n";
        resultado += "Subtotal:\t" + subtotal() + "\n";
        resultado += "IVA:\t" + iva() + "\n";
        resultado += "Total:\t" + total() + "\n";
        resultado += "--------------------------------------------------------------------------------\n";
        resultado += "¡Gracias por tu compra!\n";
        return resultado;
    }

}
