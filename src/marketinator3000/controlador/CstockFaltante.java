package marketinator3000.controlador;

import marketinator3000.modelo.Modelo;
import marketinator3000.modelo.almacen.Producto;
import java.util.HashMap;

public class CstockFaltante implements Controlador{
    
    public  CstockFaltante(){
        
    }
    /**
     * Metodo que invoca el almacen agotado y por agotarse y los imprime en pantalla, asi como tambien les da formato
     * @param modelo El modelo del programa
     */
    @Override
    public void exec(Modelo modelo){
        HashMap<String,Producto> agotado = modelo.stockAgotado();
        HashMap<String,Producto> porAgotarse = modelo.stockPorAgotarse();
        System.out.println("Stock agotado:");
        System.out.printf("%15s%30s%15s%15s%n","sku", "nombre", "cantidad", "precio");
        for (String sku : agotado.keySet()){
            System.out.println(agotado.get(sku));
        }
        System.out.println("Stock por agotarse:");
        System.out.printf("%15s%30s%15s%15s%n","sku", "nombre", "cantidad", "precio");
        for (String sku : porAgotarse.keySet()){
            System.out.println(porAgotarse.get(sku));
        }
    }
}
