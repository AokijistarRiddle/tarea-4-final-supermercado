package marketinator3000.modelo.cajas;

import marketinator3000.modelo.clientes.Cliente;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.time.Instant;

public class AreaRapida extends Thread{

    public static AreaRapida areaRapida = new AreaRapida();
    private Queue<Cliente> formados;
    private HashMap<String,Boolean> disponibilidad;
    private HashMap<String,Rapida> cajas;
    private int maxFormados;
    private long vida;

    private AreaRapida(){
        this.formados = new LinkedList<Cliente>();
        this.maxFormados = 0;
        this.disponibilidad = new HashMap<String,Boolean>();
        this.cajas = new HashMap<String,Rapida>();
    }

    public int getMaxFormados(){
        return this.maxFormados;
    }

    public HashMap<String,Rapida> getCajas(){
        return this.cajas;
    }

    public void setVida(long vida){
        this.vida = vida;
    }

    public boolean todasInactivas(){
        for (Rapida r : cajas.values()){
            if (r.getActiva()){
                return false;
            }
        }
        return true;
    }

    /**
     * Metodo que añade clientes a la unifila de las cajas rapidas
     * @param cliente Recibe los clientes a formar 
     */
    public void anadirCliente(Cliente cliente){
        this.formados.add(cliente);
        if (this.formados.size() > this.maxFormados){
            this.maxFormados = this.formados.size();
        }
    }
    
    public void anadirCaja(Rapida rapida){
        String str = rapida.getIdentificador();
        disponibilidad.put(str, true);
        cajas.put(str, rapida);
    }

    public void execAreaRapida(){
        Instant inicio = Instant.now();
        Instant fin = inicio.plusMillis(this.vida);
        while (Instant.now().isBefore(fin) || !formados.isEmpty()){
            if (formados.isEmpty()){
                try{
                    Thread.sleep(10);
                }catch (InterruptedException e){}
                continue;
            }
            Cliente actual = formados.remove();
            for (String id : disponibilidad.keySet()){
                if (disponibilidad.get(id) && cajas.get(id).getActiva()){
                    cajas.get(id).setCliente(actual);
                    disponibilidad.put(id, false);
                    break;
                }
            }
        }
    }

    @Override
    public void run(){
        execAreaRapida();
    }

    public synchronized void update(String id){
        this.disponibilidad.put(id, true);
    }

}
