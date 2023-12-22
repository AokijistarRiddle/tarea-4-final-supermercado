package marketinator3000.modelo.cajas;

import marketinator3000.modelo.clientes.Cliente;

import java.util.LinkedList;

public class Formador {
    /** 
     * Declaracion de atributos de la clase
     */
    private LinkedList<Retrasada> retrasadas;
    private AreaRapida areaRapida;
    private Estrategia estrategia;

    public Formador(LinkedList<Retrasada> retrasadas, AreaRapida areaRapida){
        this.retrasadas = retrasadas;
        this.areaRapida = areaRapida;
    }
    
    public void forma(Cliente cliente){
        if (cliente.totalProductos() <= 20){
            estrategia = new EstrategiaRapida();
            estrategia.forma(cliente, areaRapida, retrasadas);
            return;
        }
        estrategia = new EstrategiaRetrasada();
        estrategia.forma(cliente, areaRapida, retrasadas);
    }

}