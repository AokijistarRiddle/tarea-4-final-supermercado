package marketinator3000.controlador;
import marketinator3000.modelo.Modelo;

public class Cfaltantestodos implements Controlador {
    
    public Cfaltantestodos(){

    }
    /**
     * Metodo exec que llama a la funcion del modelo que reabastece
     *
     *@param modelo El modelo del programa 
     */
    @Override
    public void exec(Modelo modelo){
        modelo.reabasteceTodos();
    }
}
