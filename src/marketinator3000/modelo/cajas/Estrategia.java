package marketinator3000.modelo.cajas;

import marketinator3000.modelo.clientes.Cliente;

import java.util.LinkedList;

public interface Estrategia {
   
   public void forma(Cliente cliente, 
                     AreaRapida areaRapida, 
                     LinkedList<Retrasada> retrasadas);

}






/**
 * Estoy que te parto y tu vato barato, vente a irapuato
 */