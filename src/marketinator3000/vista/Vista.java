package marketinator3000.vista;

import marketinator3000.controlador.*;
import marketinator3000.modelo.Modelo;

import java.util.Scanner;

/**
 * Esta es la clase vista que modela el comportamiento del menú 
 * de terminal
 */

public class Vista{

	// Atributos
    private Menu menu;
    private Controlador controlador;
    private Modelo modelo;
    
	/**
	 * Método bienvenida que imprime el inicio del programa
	 */
    public void bienvenida(){

        System.out.println("¡Bienvenido!, ¿En que podemos servirle?");

    }

	/**
	 * Método constructor que inicializa la vista
	 */
    public Vista(){
        Menu menuInicial = new SubMenu(0, "Menu Inicial", "inicial");
        menuInicial.addEntry(new EntradaMenu(1, "Empezar simulación", "simulacion"));
	Menu submenu = new SubMenu(2, "Abrir Menú Administrativo", "administracion");
	submenu.addEntry(new EntradaMenu(1, "Consultar Stock", "stock"));
	submenu.addEntry(new EntradaMenu(2, "Consultar Stock Agotado/Por Agotarse", "stockFaltante"));
	Menu submenu2 = new SubMenu(3, "Reabastecer", "reabastecer");
	submenu2.addEntry(new EntradaMenu(1, "Reabastecer faltantes (Todos los faltantes, valores por default)","faltantesTodos"));
	submenu2.addEntry(new EntradaMenu(2,"Reabastecer unitario","faltantesUnitarios"));
	submenu2.addEntry(new EntradaMenu(3, "Volver", "volver"));
	submenu.addEntry(submenu2);
	submenu.addEntry(new EntradaMenu(4, "Añadir producto", "anadirProducto"));
	submenu.addEntry(new EntradaMenu(5, "Volver", "volver"));
        menuInicial.addEntry(submenu);
	Menu submenu3 = new SubMenu(3,"Estado cajas", "estadoCajas");
	submenu3.addEntry(new EntradaMenu(1, "Cantidad cajas", "cantidadCajas"));
	submenu3.addEntry(new EntradaMenu(2, "Establecer Número de Cajas Rapidas", "anadirRapidas"));
	submenu3.addEntry(new EntradaMenu(3, "Establecer Número de Cajas Normales", "anadirNormales"));
	submenu3.addEntry(new EntradaMenu(4, "Volver", "volver"));
        menuInicial.addEntry(submenu3);
	Menu submenu4 = new SubMenu(4, "Avanzado", "avanzado");
	submenu4.addEntry(new EntradaMenu(1, "Cambiar Precio de Articulo","cambiarPrecio"));
	submenu4.addEntry(new EntradaMenu(2, "Cambiar ratio de devolucuion","cambiarDevolucion"));
	submenu4.addEntry(new EntradaMenu(3, "Volver", "volver"));
        menuInicial.addEntry(submenu4);
        menuInicial.addEntry(new EntradaMenu(5, "Salir", "salir"));
        menu = menuInicial;
        modelo = new Modelo();
    }

    
    
    public void menu(){
        menu.mostrarSubMenu();
    }

    /**
	 * Método que le pide al usuario un número
     * @return El entero seleccionado por el usuario
     */
    public int numSeleccion(){
	Scanner sc = new Scanner(System.in);
	int num = -1;
	while(true){
	    try{
		menu();
		System.out.println("Ingresa una opción:");
		System.out.println("----------------------");
		String seleccion = sc.nextLine();
		num = Integer.parseInt(seleccion);
		break;
	    }
	    catch(NumberFormatException e){
		System.out.println("No es un número válido");
		System.out.println(e);
	    }
        }
	
	return num;
    }
    
    /* 
	Metodo que recibe la seleccion del usuario y ejecuta la accion pertinetne*/
    public void seleccion(){
	bienvenida();
	int seleccion = -1;
	while(true){
	    seleccion = numSeleccion();
		if (seleccion == 42069){
			InitAlmacen.initAlmacen();
			continue;
		}
	    Menu selMenu = menu.getMenu(seleccion);
	    if (selMenu == null){
		System.out.println("Seleccón inválida, intente de nuevo:");
		continue;
	    }
	    String accion = selMenu.getAccion();
	    switch (accion){
	    case "simulacion":
		this.controlador = new Csimulacion();
		this.controlador.exec(modelo);
		continue;
	    case "administracion":
		this.menu = selMenu;
		continue;
	    case "stock":
		this.controlador = new Cstock();
		this.controlador.exec(modelo);
		continue;
	    case "stockFaltante":
		this.controlador = new CstockFaltante();
		this.controlador.exec(modelo);
		continue;
	    case "reabastecer":
		this.menu = selMenu;
		continue;
	    case "faltantesTodos":
		this.controlador = new Cfaltantestodos();
		this.controlador.exec(modelo);
		continue;
	    case "faltantesUnitarios":
		this.controlador = new Cfaltantesunitarios();
		this.controlador.exec(modelo);
		continue;
	    case "anadirProducto":
		this.controlador = new CanadirProducto();
		this.controlador.exec(modelo);
		continue;
	    case "volver":
		this.menu = this.menu.getAnterior();
		continue;
	    case "estadoCajas":
		this.menu = selMenu;
		continue;
	    case "cantidadCajas":
		this.controlador = new Ccantidadcajas();
		this.controlador.exec(modelo);
		continue;
	    case "anadirRapidas":
		this.controlador = new CanadirRapidas();
		this.controlador.exec(modelo);
		continue;
	    case "anadirNormales":
		this.controlador = new Canadirnormales();
		this.controlador.exec(modelo);
		continue;
	    case "avanzado":
		this.menu = selMenu;
		continue;
	    case "cambiarPrecio":
		this.controlador = new Ccambiarprecio();
		this.controlador.exec(modelo);
		continue;
	    case "cambiarDevolucion":
		this.controlador = new CcambiarDevolucion();
		this.controlador.exec(modelo);
		continue;
	    case "salir":
		this.controlador = new Cguardar();
		this.controlador.exec(modelo);
		System.out.println("Hasta la próxima");
		System.exit(0);
	    }
	}
    }
}
