package marketinator3000.controlador;

import java.util.LinkedList;

import marketinator3000.modelo.Modelo;
import marketinator3000.modelo.cajas.Formador;
import marketinator3000.modelo.cajas.Rapida;
import marketinator3000.modelo.cajas.Retrasada;

public class Csimulacion implements Controlador{
   
    public Csimulacion(){

    }
    /**
     * Metodo que llama a inicializar la simulacion del modelo
     * @param modelo El modelo del programa
     */
    @Override
    public void exec(Modelo modelo){
        if (modelo.getEjecutado()){
            System.out.println("Ya ha terminado el horario laboral");
            return;
        }
        if (modelo.getRetrasadas() == 0 || modelo.getRapidas() == 0){
            System.out.println("El número de cajas tanto rápidas como normales deben ser mayores a 0");
            return;
        }
        modelo.getAreaRapida().setVida(modelo.getVida());
        Formador formador = new Formador(modelo.getCajasRetrasadas(), modelo.getAreaRapida());
        modelo.getClienteBuilder().setFormador(formador);
        modelo.getClienteBuilder().setAlmacen(modelo.getAlmacen());
        modelo.getClienteBuilder().setVida(modelo.getVida());
        System.out.println("Empezando simulación");
        modelo.getClienteBuilder().start();
        modelo.getAreaRapida().start();
        for (Retrasada r : modelo.getCajasRetrasadas()){
            r.start();
        }
        for (Rapida r : modelo.getAreaRapida().getCajas().values()){
            r.start();
        }
        modelo.setEjecutado();
        try{
            Thread.sleep(modelo.getVida());
        } catch(InterruptedException e){}
        while (!modelo.getAreaRapida().todasInactivas() || !modelo.todasInactivas()){
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){}
        }
        System.out.println("Terminó la simulación");
        modelo.guardaSimulacion();
    }
}
