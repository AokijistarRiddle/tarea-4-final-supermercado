package marketinator3000.modelo;

import marketinator3000.modelo.almacen.Almacen;
import marketinator3000.modelo.cajas.Gerente;
import marketinator3000.modelo.clientes.ClienteBuilder;
import marketinator3000.modelo.almacen.Producto;
import marketinator3000.modelo.almacen.Ticket;
import marketinator3000.modelo.cajas.*;

import java.util.LinkedList;
import java.util.HashMap;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase que representa el modelo del sistema Marketinator3000.
 */
public class Modelo {

    // Atributos
    private LinkedList<Retrasada> retrasadas;
    private double ratio;
    private Almacen almacen;
    private Gerente gerente;
    private ClienteBuilder clienteBuilder;
    private AreaRapida areaRapida;
    private long vida;
    private boolean ejecutado;
    
    // Constructor
    public Modelo() {
        retrasadas = new LinkedList<Retrasada>();
        ratio = 0.03;
        almacen = Almacen.almacen;
        gerente = Gerente.gerente;
        areaRapida = AreaRapida.areaRapida;
        clienteBuilder = ClienteBuilder.clienteBuilder;
        vida = 100000;
        ejecutado = false;
    }

    // Metodos para configurar el modelo

    public LinkedList<Retrasada> getCajasRetrasadas(){
        return this.retrasadas;
    }

    public boolean getEjecutado(){
        return this.ejecutado;
    }

    public void setEjecutado(){
        this.ejecutado = true;
    }

    public Almacen getAlmacen(){
        return this.almacen;
    }

    public AreaRapida getAreaRapida(){
        return this.areaRapida;
    }

    public ClienteBuilder getClienteBuilder(){
        return this.clienteBuilder;
    }

    public long getVida(){
        return this.vida;
    }

    public boolean todasInactivas(){
        for (Retrasada r : retrasadas){
            if (r.getActiva()){
                return false;
            }
        }
        return true;
    }

    /**
     * Establece el ratio de devolucion de productos.
     *
     * @param ratio El ratio de devolucion.
     * @throws IllegalArgumentException Si el ratio no esta en el rango [0, 1].
     */
    public void setDevolucion(double ratio) throws IllegalArgumentException {
        if (ratio < 0 || ratio > 1) {
            throw new IllegalArgumentException("El ratio debe ser un numero entre 0 y 1");
        }
        this.ratio = ratio;
    }       

    /**
     * Establece el precio de un producto en el almacen.
     *
     * @param sku El codigo SKU del producto.
     * @param precio El nuevo precio del producto.
     * @throws IllegalArgumentException Si el precio es negativo.
     */
    public void setPrecio(String sku, double precio) throws IllegalArgumentException {
        this.almacen.setPrecio(sku, precio);
    }

    /**
     * Agrega cajas normales al modelo.
     *
     * @param numCajas El numero de cajas normales a agregar.
     * @throws IllegalArgumentException Si el numero de cajas es negativo.
     */
    public void setNormales(int numCajas) throws IllegalArgumentException {
        if (numCajas < 0) {
            throw new IllegalArgumentException("No puede haber menos de 0 cajas");
        }
        for (int i = 0; i < numCajas; i++) {
            String id = "Normal-" + i;
            Retrasada newCaja = new Retrasada(id, almacen, gerente, clienteBuilder, ratio, vida);
            retrasadas.add(newCaja);
        }
    }

    // Metodos relacionados con la consulta de informacion sobre cajas

    /**
     * Obtiene la cantidad de cajas rapidas en el modelo.
     *
     * @return La cantidad de cajas rapidas.
     */
    public int getRapidas() {
        return areaRapida.getCajas().keySet().size();
    }
    
    /**
     * Obtiene la cantidad de cajas retrasadas en el modelo.
     *
     * @return La cantidad de cajas retrasadas.
     */
    public int getRetrasadas() {
        return retrasadas.size();
    }

    /**
     * Agrega cajas rapidas al modelo.
     *
     * @param numCajas El numero de cajas rapidas a agregar.
     * @throws IllegalArgumentException Si el numero de cajas es negativo.
     */
    public void setRapidas(int numCajas) {
        if (numCajas < 0) {
            throw new IllegalArgumentException("No puede haber menos de 0 cajas");
        }
        for (int i = 0; i < numCajas; i++) {
            String id = "Rapida-" + i;
            Rapida newCaja = new Rapida(id, almacen, gerente, clienteBuilder, ratio, vida, areaRapida);
            areaRapida.anadirCaja(newCaja);
        }
    }

    // Metodos relacionados con el inventario y productos

    /**
     * Reabastece todos los productos en el almacen.
     */
    public void reabasteceTodos() {
        this.almacen.reabasteceTodos();
    }

    /**
     * Verifica si un producto con un SKU dado existe en el almacen.
     *
     * @param sku El codigo SKU del producto.
     * @return true si el producto existe, false en caso contrario.
     */
    public boolean existeProducto(String sku) {
        return this.almacen.existeProducto(sku); 
    }

    /**
     * Agrega un nuevo producto al almacen.
     *
     * @param sku El codigo SKU del producto.
     * @param nombre El nombre del producto.
     * @param precio El precio del producto.
     * @param cantidad La cantidad inicial en el almacen.
     * @param peso El peso del producto.
     * @param defecto La cantidad por defecto en los carritos de los clientes.
     * @param stockMinimo La cantidad minima antes de reabastecer.
     */
    public void nuevoProducto(String sku, String nombre, double precio, int cantidad, double peso, int defecto, int stockMinimo) {
        this.almacen.agregarProducto(sku, nombre, precio, cantidad, peso, defecto, stockMinimo);
    }

    /**
     * Añade una cantidad unitaria de un producto al almacen.
     *
     * @param sku El codigo SKU del producto.
     * @param cantidad La cantidad a añadir.
     */
    public void anadirUnitario(String sku, int cantidad) {
        this.almacen.anadirUnitario(sku, cantidad);
    }

    /**
     * Devuelve todos los productos en el almacen.
     *
     * @return La lista de productos en el almacen.
     */
    public HashMap<String, Producto> stockCompleto() {
        return this.almacen.obtenerTodosProductos();
    }

    /**
     * Devuelve el estado de los productos por agotarse.
     *
     * @return La lista de productos por agotarse.
     */
    public HashMap<String, Producto> stockPorAgotarse() {
        return this.almacen.stockPorAgotarse();
    }

    /**
     * Devuelve el estado de los productos agotados.
     *
     * @return La lista de productos agotados.
     */
    public HashMap<String, Producto> stockAgotado() {
        return this.almacen.stockAgotado();
    }

    // Metodo para guardar el almacen

    /**
     * Guarda el estado actual del almacen.
     *
     * @throws IOException Si ocurre un error durante la operacion de guardado.
     */
    public void saveAlmacen() throws IOException {
        this.almacen.save();
    }

    public void guardaSimulacion(){
        try{
            LocalDateTime dia = LocalDateTime.now();
            DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
            DateTimeFormatter f2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            BufferedWriter bf = new BufferedWriter(new FileWriter("ventas-" + dia.format(f) + ".txt"));
            double sumaTotal = 0;
            for (Ticket t : almacen.getTickets().get(dia.format(f2))){
                bf.write(t.toString());
                sumaTotal += t.total();
            }
            bf.write("El total vendido del día fue de : " + sumaTotal + "\n");
            int max = 0;
            String caja = "";
            for (Retrasada r : retrasadas){
                if (r.getMaxFormados() > max){
                    max = r.getMaxFormados();
                    caja = r.getIdentificador();
                }
            }
            if (areaRapida.getMaxFormados() > max){
                caja = "área de cajas rápidas";
            }
            bf.write("La caja con mayor número de clientes formados fue: " + caja);
            bf.close();
            bf = new BufferedWriter(new FileWriter("resumen-cajas-"+ dia.format(f) + ".txt"));
            for (Retrasada r : retrasadas){
                bf.write(r.getIdentificador() + ":" + "\n");
                bf.write("\tProductos vendidos: " + r.getProductosVendidos() + "\n");
                bf.write("\tTotal vendido: " + r.getTotalVendido() + "\n");
                bf.write("\tClientes atendidos: " + r.getAtendidos() + "\n");  
                bf.write("\tMáximo de clientes formados: " + r.getMaxFormados() + "\n");
            }
            for (Rapida r : this.areaRapida.getCajas().values()){
                bf.write(r.getIdentificador() + ":" + "\n");
                bf.write("\tProductos vendidos: " + r.getProductosVendidos() + "\n");
                bf.write("\tTotal vendido: " + r.getTotalVendido() + "\n");
                bf.write("\tClientes atendidos: " + r.getAtendidos() + "\n");  
            }
            bf.write("Máximo número de clientes formados en área rápida: " + this.areaRapida.getMaxFormados() + "\n");
            bf.close();
        } catch (IOException e){
            System.out.println("No se pudo escribir en archivo");
        }
    }

}
