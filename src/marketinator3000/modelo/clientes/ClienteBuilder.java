package marketinator3000.modelo.clientes;

import marketinator3000.modelo.almacen.Almacen;
import marketinator3000.modelo.cajas.Formador;
import marketinator3000.modelo.almacen.Producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.Random;
import java.time.Instant;

public class ClienteBuilder extends Thread{
    /**
     * Atributos de la clase ClienteBuilder
     */
    public static ClienteBuilder clienteBuilder = new ClienteBuilder();
    private Cliente cliente;
    private Formador formador;
    private HashMap<String, Integer> contador;
    private Almacen almacen;
    private long vida;

    /**
     * Constructor de la clase
     * @param formador El metodo con el que se van a formar (Strategy)
     * @param almacen El almacen del modelo
     */
    private ClienteBuilder(){}
    
    public void setFormador(Formador formador){
        this.formador = formador;
    }

    public void setAlmacen(Almacen almacen){
        this.almacen = almacen;
        this.contador = new HashMap<String, Integer>();
        for (String id : almacen.obtenerTodosProductos().keySet()){
            contador.put(id, almacen.obtenerTodosProductos().get(id).getCantidad());
        }
    }

    public void setVida(long vida){
        this.vida = vida;
    }

    private class SortByPeso implements Comparator<Producto>{

        private double ran;

        public SortByPeso(double ran){
            this.ran = ran;
        }

        @Override
        public int compare(Producto a, Producto b){
            return (int)(ran - b.getPeso()) - (int)(ran - a.getPeso()); 
        }

    }

    /**
     * Seleccionador de manera aleatoria del producto, priorizando los pesos mas grandes
     */
    public void buildCliente(){
        this.cliente = new Cliente();
        LinkedList<Producto> productos = new LinkedList<Producto>(almacen.obtenerTodosProductos().values());
        Random random = new Random();
        int iteraciones = -1;
        while (true){
            iteraciones = (int)(random.nextGaussian() * Math.sqrt(20) + 20);
            if (iteraciones < 1 || iteraciones > 70){
                continue;
            }
            break;
        }
        while (iteraciones > 0){
            double r = random.nextGaussian() * Math.sqrt(.2) + 1;
            if (r < 0 || r > 1){
                continue;
            }
            productos.sort(new SortByPeso(r));
            LinkedList<Producto> elegidos = new LinkedList<>();
            Producto first = null;
            for (Producto p : productos){
                if (contador.get(p.getSku()) > 0){
                    first = p;
                }
            }
            if (first == null){
                break;
            }
            for (Producto p : productos){
                if (r - p.getPeso() == r - first.getPeso() && contador.get(p.getSku()) > 0){
                    elegidos.add(p);
                    continue;
                }
                if (r - p.getPeso() < r - first.getPeso()){
                    break;
                }
            }
            int sel = 0;
            sel = (elegidos.size() > 0) ? random.nextInt(elegidos.size()) : 0;
            elegidos.add(first);
            Producto seleccionado = elegidos.get(sel);
            if (seleccionado == null){
                iteraciones--;
            }
            cliente.anadirProductoEnCarrito(seleccionado.getSku(), 1);
            contador.put(seleccionado.getSku(), contador.get(seleccionado.getSku()) - 1);
            iteraciones--;
        }
    }
    /**
     * Metodo que devuelve el cliente que se esta tratando
     */
    public Cliente getCliente(){
        return this.cliente;
    }
    /**
     * Metodo que forma el cliente
     */
    public void formaCliente(){
        if (this.cliente.getCarrito().keySet().size() > 0) formador.forma(this.cliente);
    }
    
    public void execBuilder(){
        Instant inicio = Instant.now();
        Instant fin = inicio.plusMillis(this.vida);
        Random random = new Random();
        while (Instant.now().isBefore(fin)){
            buildCliente();
            formaCliente();
            try{
                Thread.sleep(Long.valueOf(100 + random.nextInt(101)));
            }catch (InterruptedException e){}
        }
    }   

    @Override
    public void run(){
        execBuilder();
    }
    /**
     * Metodo que actualiza el contador de objetos que se substraen de el almacen
    */ 
    public synchronized void update(String sku){
        contador.put(sku, contador.get(sku) + 1);
    }

}