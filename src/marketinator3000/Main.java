package marketinator3000;
/**
 * Estoy que te parto y tu vato barato, vente un fin de semana a irapuato
 */
import marketinator3000.vista.Vista;

public class Main{
    /**
     * Metodo main que reciben la vista, la invocan y la ejecutan
     */
    public static Vista vista;

    public static void main(String[] args){
        vista = new Vista();
        vista.seleccion();
    }

}