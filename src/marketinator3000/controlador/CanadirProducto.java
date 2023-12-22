package marketinator3000.controlador;

import marketinator3000.modelo.Modelo;
import marketinator3000.modelo.almacen.Producto;
import java.util.Scanner;

public class CanadirProducto implements Controlador{

    public CanadirProducto(){
	
    }
	/*
	*Metodo que recibe la seleccion del usuario
	*/
    public String seleccionString(){
	Scanner sc = new Scanner(System.in);
	return sc.nextLine();
    }
	/*
	*Metodo que recibe la seleccion del usuario
	*/
    public double seleccionDouble(){
	Scanner sc = new Scanner(System.in);
	double seleccion = -1;
	while (true){
	    try{
		String input = sc.nextLine();
		seleccion = Double.parseDouble(input);
		break;
	    }catch (NumberFormatException e){
		System.out.println("Número inválido");
	    }
	}
	return seleccion;
    }
	/*
	*Metodo que recibe la seleccion del usuario
	*/
    public int seleccionInt(){
	Scanner sc = new Scanner(System.in);
	int seleccion = -1;
	while (true){
	    try{
		String input = sc.nextLine();
		seleccion = Integer.parseInt(input);
		break;
	    }catch (NumberFormatException e){
		System.out.println("Número inválido");
	    }
	}
	return seleccion;
    }
    /**
     * Método exec que ejecuta que pide al usuario
     * los datos del producto que debe agregar
     * @param modelo El modelo del programa
     */
    @Override
    public void exec(Modelo modelo){
	String sku, nombre;
	double precio, peso;
	int cantidad, defecto, stockMinimo;
	while (true){
	    System.out.println("Ingrese el sku del producto");
	    sku = seleccionString();
	    if (modelo.existeProducto(sku)){
		System.out.println("Ya existe un producto con ese sku");
		continue;
	    }
	    break;
	}
	System.out.println("Ingrese el nombre del producto:");
	nombre = seleccionString();
	while (true){
	    System.out.println("Ingrese el precio del producto:");
	    precio = seleccionDouble();
	    if (precio <= 0){
		System.out.println("El precio debe ser mayor a 0");
		continue;
	    }
	    break;
	}
	while (true){
	    System.out.println("Ingrese el peso del producto:");
	    peso = seleccionDouble();
	    if (peso < 0 || peso > 1){
		System.out.println("El peso debe ser un número entre uno y cero");
		continue;
	    }
	    break;
	}
	while (true){
	    System.out.println("Ingrese la cantidad inicial del producto:");
	    cantidad = seleccionInt();
	    if (cantidad < 0){
		System.out.println("La cantidad no puede ser negativa");
		continue;
	    }
	    break;
	}
	while (true){
	    System.out.println("Ingrese el la cantidad con la que debería reabastacerse el producto en caso de agotarse:");
	    defecto = seleccionInt();
	    if (defecto <= 0){
		System.out.println("La cantidad debe ser mayor a 0");
		continue;
	    }
	    break;
	}
	while (true){
	    System.out.println("Ingrese la cantidad con la que se debe indicar que el producto se está agotando:");
	    stockMinimo = seleccionInt();
	    if (stockMinimo <= 0){
		System.out.println("El precio debe ser mayor a 0");
		continue;
	    }
	    break;
	}
	modelo.nuevoProducto(sku, nombre, precio, cantidad, peso, defecto, stockMinimo);
	System.out.println("Producto agregado con éxito.");
    }

}
