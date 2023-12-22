package marketinator3000.controlador;
import marketinator3000.modelo.Modelo;
import java.util.Scanner;

public class CcambiarDevolucion implements Controlador {
    
    public CcambiarDevolucion(){

    }
    /*
    *Metodo que pide al usuario que ingrese un dato
     */
    public double seleccion(){
        Scanner esece = new Scanner(System.in);
        double seleccion = -1;
        while (true) {
            try{
                System.out.println("Ingresa un número");
                String input = esece.nextLine();
                seleccion = Double.parseDouble(input);
                break;
            }catch (NumberFormatException e){
                System.err.println("No es un número válido");
            }
        }
        
        return seleccion;
    }
    /**
    *Metodo exec que ejecuta el cambio de valor en el radio de devolucion y despues notifica la operacion con exito
    * @param modelo  el modelo del programa
    */
    @Override
    public void exec(Modelo modelo){{
        while (true){
            try{
                double sel = seleccion();
                modelo.setDevolucion(sel);
                System.out.println("El radio de devolución fue modificado con exito");
		break;
            } catch (IllegalArgumentException e){
                System.out.println("Numero invalido");
            }
         }
        }
    
    }
}
