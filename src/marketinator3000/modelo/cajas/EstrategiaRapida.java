package marketinator3000.modelo.cajas;

import marketinator3000.modelo.clientes.Cliente;

import java.util.LinkedList;

public class EstrategiaRapida implements Estrategia{

    @Override
    public void forma(Cliente cliente, 
                      AreaRapida areaRapida, 
                      LinkedList<Retrasada> retrasadas){
        areaRapida.anadirCliente(cliente);
    }
    
}
