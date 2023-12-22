package marketinator3000.controlador;

import marketinator3000.modelo.Modelo;
import marketinator3000.modelo.almacen.Producto;
import java.util.HashMap;

public class Cstock implements Controlador{

    public Cstock(){
	
    }
	/**
	 * Metodo que da formato a la salida del stock y que ademas lo consulta 
	 * @param modelo El modelo del programa
	 */
    @Override
    public void exec(Modelo modelo){
	HashMap<String,Producto> completo = modelo.stockCompleto();
	System.out.println("Stock completo:");
	System.out.printf("%15s%30s%15s%15s%n","sku", "nombre", "cantidad", "precio");
	for (String sku : completo.keySet()){
	    System.out.println(completo.get(sku));
	}
    }

}
