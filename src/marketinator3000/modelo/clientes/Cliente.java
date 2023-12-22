package marketinator3000.modelo.clientes;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

public class Cliente{
    
    private HashMap<String,Integer> carrito;
    /**
     * Creamos el carrito que llevara cada cliente
     */
    public Cliente(){
        carrito = new HashMap<String,Integer>();
    }
    /**
     * Metodo que añadee productos a un carrito
     * @param sku El identificador de producto
     * @param cantidad La cantidad de elemnentos que tendra el carrito
     */
    public void anadirProductoEnCarrito(String sku, int cantidad){
        if (carrito.containsKey(sku)){
            int cantidadprevia = carrito.get(sku);
            carrito.put(sku, cantidad + cantidadprevia);
            return;
        }
        carrito.put(sku, cantidad);
    }
    /**
     * Metodo que devuelve el carrito
     * @return El carrito 
     */
    public HashMap<String, Integer> getCarrito(){
        return this.carrito;
    }
    /**
     * Metodo que escoge un objeto aleatorio del carrito de un cliente
     * Sirve para las devoluciones
     * 
     */
    public String eliminaAleatorio(){
        ArrayList<String> keys = new ArrayList<String>(carrito.keySet());
        Random random = new Random();
        String seleccion = keys.get(random.nextInt(keys.size()));
        if (carrito.get(seleccion) - 1 <= 0){
            carrito.remove(seleccion);
        }else{
            carrito.put(seleccion, carrito.get(seleccion)-1);
        }
        return seleccion;
    }
    public int totalProductos(){
        int cuenta = 0;
        for(String sku : this.carrito.keySet()){
            cuenta += carrito.get(sku);
        }
        return cuenta;
    }

}
