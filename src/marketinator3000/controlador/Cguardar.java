package marketinator3000.controlador;

import marketinator3000.modelo.Modelo;

import java.io.IOException;

public class Cguardar implements Controlador{

    public Cguardar(){

    }
    /**
     * Metodo exec que llama a el metodo que guarda el estado del almacen
     * @param modelo El modelo del programa
     */
    @Override
    public void exec(Modelo modelo){
	try{
	    modelo.saveAlmacen();
	}catch (IOException e){
	    System.out.println("No se pudo guardar el almacén");
        e.printStackTrace();
	}
    }
    
}
