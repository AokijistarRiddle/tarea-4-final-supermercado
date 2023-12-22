package marketinator3000.controlador;

import java.util.NoSuchElementException;
import java.util.Scanner;

import marketinator3000.modelo.Modelo;

public class Ccambiarprecio implements Controlador {
    
    public Ccambiarprecio(){}
    /**
     * Metodo que consulta al usuario por una referencia al SKU
     */
    public String seleccionString(){
	Scanner sc = new Scanner(System.in);
	System.out.println("Ingresa el sku del producto:");
	return sc.nextLine();
    }
    /**
     * Metodo que consulta al usuario por el precio a cambiar
     */
    public double seleccionDouble(){
        Scanner esece = new Scanner(System.in);
        double seleccion = -1;
        while (true) {
            try{
                System.out.println("Ingresa el nuevo precio:");
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
     * Metodo que llama al modelo a cambiar el precio del articulo correspondiente al SKU
     * @param modelo El modelo del programa
     */
    @Override
    public void exec(Modelo modelo){
	while(true){
	    try{
		String sku = seleccionString();
		double precio = seleccionDouble();
		modelo.setPrecio(sku, precio);
		System.out.println("Precio cambiado con exito, el nuevo precio es: " + precio);
		break;
	    }
	    catch (NoSuchElementException e){
		System.out.println("El producto con el sku indicado no existe");
	    }
	    catch (IllegalArgumentException e){
		System.out.println("Numero invalido");
	    }
	}
    }
        
 }


