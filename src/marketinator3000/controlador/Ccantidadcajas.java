package marketinator3000.controlador;
import marketinator3000.modelo.Modelo;

public class Ccantidadcajas implements Controlador{

    public Ccantidadcajas(){

    }
    /**
     * Metodo que obtiene la cantidad total de cajas que hay y las muestra al usuario
     * @param modelo El modelo del programa
     */
    @Override
    public void exec(Modelo modelo){
        int cajasTotales = modelo.getRapidas() + modelo.getRetrasadas();
        System.out.println("Cantidad de cajas rápidas: " + modelo.getRapidas() + 
                           "\nCantidad de cajas normales:" + modelo.getRetrasadas() +
                           "\nCantidad de cajas totales: " + cajasTotales);
    }
    
}
