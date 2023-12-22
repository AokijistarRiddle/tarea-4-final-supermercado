package marketinator3000.modelo.almacen;

import java.util.NoSuchElementException;
import java.util.HashMap;
import java.util.LinkedList;
import java.io.Serializable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class Almacen implements Serializable{

    public static Almacen almacen = new Almacen();
    private HashMap<String,Producto> listaProductos;
    private transient HashMap<String, LinkedList<Ticket>> tickets;

    private Almacen() {
	File f = new File("almacen.obj");
	if (f.exists() && !f.isDirectory()){
	    try{
		Almacen cargado = load();
		this.listaProductos = cargado.obtenerTodosProductos();
        this.tickets = cargado.getTickets();
		return;
	    }catch (IOException | ClassNotFoundException e){
		System.out.println("No se pudo cargar el almacen");
        e.printStackTrace();
	    }
	}
	System.out.println("El almacén no existe");
    this.listaProductos = new HashMap<String,Producto>();
    this.tickets = new HashMap<String,LinkedList<Ticket>>();
    }

    public HashMap<String,LinkedList<Ticket>> getTickets(){
        return this.tickets;
    }

    public void agregarProducto(String sku, String nombre, double precio, int cantidad, double peso, int defecto, int stockMinimo) {
        Producto nuevoProducto = new Producto(sku, nombre, precio, cantidad, peso, defecto, stockMinimo);
        listaProductos.put(sku, nuevoProducto);
    }

    public boolean existeProducto(String sku){
        return listaProductos.containsKey(sku);
    }

    public void reabasteceTodos(){
        for (String sku : listaProductos.keySet()){
            Producto producto = listaProductos.get(sku);
            if (producto.getCantidad() < producto.getStockMinimo()){
                producto.setCantidad(producto.getDefecto());
            }
        }
    }

    public HashMap<String, Producto> stockPorAgotarse(){
        HashMap<String, Producto> resultado = new HashMap<String,Producto>();
        for (String sku : listaProductos.keySet()){
            Producto producto = listaProductos.get(sku);
            if (producto.getCantidad() < producto.getStockMinimo() &&
                producto.getCantidad() > 0 ){
                resultado.put(sku,producto);
            }
        }
        return resultado;
    }

    public HashMap<String, Producto> stockAgotado(){
        HashMap<String, Producto> resultado = new HashMap<String, Producto>();
        for (String sku : listaProductos.keySet()){
            Producto producto = listaProductos.get(sku);
            if (producto.getCantidad() == 0){
                resultado.put(sku,producto);
            }
        }
        return resultado;
    }

    public HashMap<String,Producto> obtenerTodosProductos() {
        return listaProductos;
    }

    public void anadirUnitario(String sku, int cantidad){
        listaProductos.get(sku).aumentaCantidad(cantidad);
    }

    public void setPrecio(String sku, double precio) throws NoSuchElementException, IllegalArgumentException{
	if (!listaProductos.containsKey(sku)){
	    throw new NoSuchElementException("No existe el producto");
	}
	listaProductos.get(sku).setPrecio(precio);
    }

    public void save() throws IOException {
	FileOutputStream fos = new FileOutputStream("almacen.obj");
	ObjectOutputStream oos = new ObjectOutputStream(fos);
	oos.writeObject(this);
	oos.close();
    }

    public Almacen load() throws IOException, ClassNotFoundException {
	FileInputStream fis = new FileInputStream("almacen.obj");
	ObjectInputStream ois = new ObjectInputStream(fis);
	Almacen cargado = (Almacen) ois.readObject();
	ois.close();
	return cargado;
    }

    public synchronized void procesaTicket(Ticket ticket){
        String fecha = ticket.getFecha();
        int venta = 0;
        if (tickets.containsKey(fecha) && !tickets.get(fecha).isEmpty()){
            venta = tickets.get(fecha).getLast().getVenta() + 1;
        }
        if (!tickets.containsKey(fecha)){
            tickets.put(fecha, new LinkedList<>());
        }
        ticket.setVenta(venta);
        tickets.get(fecha).add(ticket);
        for (String sku : ticket.getProductos().keySet()){
            listaProductos.get(sku).restaCantidad(ticket.getProductos().get(sku));
        }
    }

}
