package marketinator3000.modelo.cajas;

import marketinator3000.modelo.almacen.Almacen;
import marketinator3000.modelo.almacen.Ticket;
import marketinator3000.modelo.clientes.ClienteBuilder;
import marketinator3000.modelo.clientes.Cliente;

import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Retrasada extends Caja{

    private Queue<Cliente> formados;
    private int maxFormados;
    /**
     * Metodo que declara el tipo de caja e inicializa una caja normal 
     * @param id Identificador de la caja
     * @param almacen El almacen del modelo
     * @param gerente El gerente (instancia unica)
     * @param clientebuilder El constructor de los clientes
     * @param ratio La propocion de devolucion de los productos
     * @param vida El tiempo de vida de las cajas
     */
    public Retrasada(String id, Almacen almacen, Gerente gerente, ClienteBuilder clienteBuilder, double ratio, long vida){
        super(id, almacen, gerente, clienteBuilder, ratio, vida);
        super.tipo = "retrasada";
        this.formados = new LinkedList<Cliente>();
        this.maxFormados = 0;
    }

    public int getMaxFormados(){
        return maxFormados;
    }

    public void anadirCliente(Cliente cliente){
        this.formados.add(cliente);
        if (formados.size() > maxFormados){
            this.maxFormados = formados.size();
        }
    }

    /**
     * Metodo que atiende a los clientes, se compone en 3 partes, si no hay clientes formados la caja duerme por 10 milisegundos
     * Obtiene el carrito  de un cliente y lo quita de la fila, mientras atiende al cliente por producto duerme 10 segundos
     * Despues con la informacion del cliente y su carrito, llama a generar un ticket
     */
    @Override
    public void atender(){
        Instant inicio = Instant.now();
        Instant fin = inicio.plusMillis(super.vida);
        Random random = new Random();
        super.horaDescanso = Long.valueOf(random.nextInt((int)(vida) - (int)(vida) / 8));
        super.activa = true;
        Instant descanso = inicio.plusMillis(super.horaDescanso);
        boolean haDescansado = false;
        while (Instant.now().isBefore(fin) || !formados.isEmpty()){
            if (Instant.now().isAfter(descanso) && !haDescansado){
                System.out.println("Ya empezó la hora de descanso de la caja: " + super.id);
                super.activa = false;
                if (formados.isEmpty()){
                    System.out.println("Descansando caja: " + super.id);
                    try{
                        Thread.sleep(super.tiempoDescanso);
                    }catch (InterruptedException e){}
                    super.activa = true;
                    haDescansado = true;
                    System.out.println("Ha terminado la hora de descanso de la caja: " + super.id);
                }
            }
            if (formados.isEmpty()){
                try{
                    Thread.sleep(10);
                } catch (InterruptedException e){}
                continue;
            }
            Cliente actual = formados.remove();
            super.atendidos += 1;
            for (int i = 0; i < actual.getCarrito().size(); i++){
                int tiempo = 100 + random.nextInt(101);
                if (random.nextDouble() <= super.ratio){
                    super.cancelar();
                    String eliminado = actual.eliminaAleatorio();
                    clienteBuilder.update(eliminado);
                    continue;
                }
                try{
                    Thread.sleep(Long.valueOf(tiempo));
                } catch(InterruptedException e){}
            }
            super.productosvendidos += actual.getCarrito().size();
            /**
             * Procesamiento del ticket generado
             */
            Ticket generado = super.creaTicket(actual.getCarrito());
            almacen.procesaTicket(generado);
        }
        this.activa = false;
    }
    public Queue<Cliente> getFormados(){
        return formados;
    }
    /**
     * Metodo que inicializa la caja, con la funcion run() de la paqueteria de Threading
     */
    @Override
    public void run(){
        atender();
    }
    
}
