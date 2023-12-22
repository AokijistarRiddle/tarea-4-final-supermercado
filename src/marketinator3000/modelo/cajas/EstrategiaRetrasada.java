package marketinator3000.modelo.cajas;

import marketinator3000.modelo.clientes.Cliente;

import java.util.LinkedList;

public class EstrategiaRetrasada implements Estrategia{

    private Retrasada menorDisponible(LinkedList<Retrasada> retrasadas){
        int minCola = 0;
        Retrasada min = null;
        for (Retrasada r : retrasadas){
            if (!r.getActiva()){
                continue;
            }
            if (r.getFormados().size() <= minCola){
                min = r;
                minCola = r.getFormados().size();
            }
        }
        return min;

    }

    @Override
    public void forma(Cliente cliente, 
                      AreaRapida areaRapida, 
                      LinkedList<Retrasada> retrasadas){
        Retrasada r = menorDisponible(retrasadas);
        if (r == null){
            try{
                Thread.sleep(10);
            }catch (InterruptedException e){}
            forma(cliente, areaRapida, retrasadas);
            return;
        }
        r.anadirCliente(cliente);
    }
    
}
