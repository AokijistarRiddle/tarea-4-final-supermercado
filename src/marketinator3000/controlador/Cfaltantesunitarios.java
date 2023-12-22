package marketinator3000.controlador;

import java.util.Scanner;
import marketinator3000.modelo.Modelo;

public class Cfaltantesunitarios implements Controlador{
    
    public Cfaltantesunitarios(){

    }
    /**
     * Metodo que consulta al usuario por una seleccion
     */
    public String seleccionStr(){
        Scanner esece = new Scanner(System.in);
        String seleccion = esece.nextLine();
        return seleccion;
    }
    /**
     * Metodo que consulta al usuario por un numero
     */
    public int seleccionInt(){
        Scanner esece = new Scanner(System.in);
        int seleccion = -1;
        while (true) {
            try{
                System.out.println("Ingresa un número");
                String input = esece.nextLine();
                seleccion = Integer.parseInt(input);
                break;
            }catch (NumberFormatException e){
                System.err.println("No es un número válido");
            }
        }
        return seleccion;
    }

    /**
 * Método exec que consulta al usuario por el SKU del producto y la cantidad a añadir, después
 * llama a la función añadir unitario del modelo.
 *
 * @param modelo El modelo del programa.
 */
@Override
public void exec(Modelo modelo) {
    String sku = "";
    do {
        System.out.println("Ingrese el SKU del producto:");
        sku = seleccionStr();
        if (!modelo.existeProducto(sku)) {
            System.out.println("El producto no existe");
            continue;
        }
        break;
    } while (true);

    int cantidad = -1;
    do {
        System.out.println("Ingrese la cantidad que quiere añadir:");
        cantidad = seleccionInt();
        if (cantidad <= 0) {
            System.out.println("Elige un número mayor a cero");
            continue;
        }
        break;
    } while (true);

    modelo.anadirUnitario(sku, cantidad);
}
}
