package marketinator3000.modelo.cajas;

import marketinator3000.modelo.almacen.Almacen;
import marketinator3000.modelo.almacen.Ticket;
import marketinator3000.modelo.clientes.ClienteBuilder;

import java.util.HashMap;

public abstract class Caja extends Thread{
    /**
     * Atributos de la clase
     */
    protected String tipo;
    protected String id;
    protected Almacen almacen;
    protected Gerente gerente;
    protected ClienteBuilder clienteBuilder;
    protected int productosvendidos;
    protected double totalVendido;
    protected double ratio;
    protected long vida;
    protected boolean activa;
    protected long tiempoDescanso;
    protected long horaDescanso;
    protected int atendidos;

    /**
     * Metodo constructor 
     * @param id El identificador de la caja 
     * @param almacen El almacen del modelo
     * @param gerente El gerente de la tienda
     * @param clienteBuilder El constructor del cliente
     * @param ratio El porcentaje de devolucion 
     * @param vida El tiempo de vida de la caja
     */

    public Caja(String id, Almacen almacen, Gerente gerente, ClienteBuilder clienteBuilder, double ratio, long vida){
        this.id = id;
        this.almacen = almacen;
        this.gerente = gerente;
        this.clienteBuilder = clienteBuilder; 
        this.vida = vida;
        this.productosvendidos = 0;
        this.totalVendido = 0;
        this.atendidos = 0;
        this.ratio = ratio;
        this.activa = false;
        this.tiempoDescanso = vida / 8;
    }
    public int getAtendidos(){
        return this.atendidos;
    }
    public int getProductosVendidos(){
        return this.productosvendidos;
    }
    public double getTotalVendido(){
        return this.totalVendido;
    }
    public boolean getActiva(){
        return this.activa;
    }
    public void setActiva(){
        this.activa = true;
    }
    /**
     * Metodo que devuelve el tipo de caja que es
     * @return El tipo de caja
     */
    public String getTipo() {
        return tipo;
    }

    public String getIdentificador() {
        return this.id; // Corregir asignación redundante
    }

    /**
     * Metodo que atiende gente
     */
    public abstract void atender();

    /**
     * Metodo que llama al gerente a cancelar una compra
     */
    protected void cancelar(){
        gerente.cancela();
    }
    /**
     * Metodo que crea ticket
     * @return ticket del carrito
     */
    public Ticket creaTicket(HashMap<String, Integer> carrito){
        for (String s : carrito.keySet()){
            this.totalVendido += almacen.obtenerTodosProductos().get(s).getPrecio() * carrito.get(s);
        }
        return new Ticket(carrito, almacen, this);
    }
    /**
     * Metodo que corre el hilo de la caja
     * 
     */
    @Override
    public void run(){
        return;
    }

}
