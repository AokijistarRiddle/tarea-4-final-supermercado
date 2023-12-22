package marketinator3000.controlador;
import java.util.Scanner;

import marketinator3000.modelo.Modelo;

public class CanadirRapidas implements Controlador{
    
    public CanadirRapidas(){

    }
    /*
    *Metodo que pide un numero al usuario y lo guarda
    */
    public int seleccion(){
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
     * Método exec que ejecuta que pide al usuario
     * cuantas cajas normales debe abrir
     * @param modelo El modelo del programa
     */
    @Override
    public void exec(Modelo modelo){
            while (true){
                try{
                    int sel = seleccion();
                    modelo.setRapidas(sel);
                    System.out.println(sel + " cajas rapidas anadidas con exito");
                    break;
                } catch (IllegalArgumentException e){
                    System.out.println("Numero invalido");
                }
            }
        }
    
    }



