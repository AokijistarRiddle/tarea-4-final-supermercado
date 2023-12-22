package marketinator3000.modelo.cajas;

import java.util.Random;

public class Gerente {
    /**Atributos de la clase */
    public static Gerente gerente = new Gerente();
    /**
     * Se declara como privado el metodo constructor para garantizar que solo el se pueda invocar una vez
     */
    private Gerente(){}
    /**Metodo sincronizado de cancelar (es parte de las acciones concurrentes del programa)
     */
    public synchronized void cancela(){
        try{
            Random random = new Random();
            int tiempo = 1 + random.nextInt(100);
            Thread.sleep(Long.valueOf(tiempo));
        }catch (InterruptedException e){}
    }
    
}
