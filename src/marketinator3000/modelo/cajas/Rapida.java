package marketinator3000.modelo.cajas;

import java.time.Instant;
import java.util.Random;

import marketinator3000.modelo.almacen.Almacen;
import marketinator3000.modelo.almacen.Ticket;
import marketinator3000.modelo.clientes.Cliente;
import marketinator3000.modelo.clientes.ClienteBuilder;

public class Rapida extends Caja{

    private Cliente cliente;
    private AreaRapida areaRapida;

    /**
     * Metodo que declara el tipo de caja
     */
    public Rapida(String id, Almacen almacen, Gerente gerente, ClienteBuilder clienteBuilder, double ratio, long vida, AreaRapida areaRapida){
        super(id, almacen, gerente, clienteBuilder, ratio, vida);
        super.tipo = "rapida";
        this.areaRapida = areaRapida;
    }
    /**
     * Metodo que regresa el cliente a atender
     * @param cliente El cliente a antender
     */
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    /**
     * Metodo que atiende a los clientes formados
     */
    @Override
    public void atender(){
        /**
         * Declaracion de las variables que usara el metodo
         */
        Instant inicio = Instant.now();
        Instant fin = inicio.plusMillis(super.vida);
        Random random = new Random();
        super.horaDescanso = Long.valueOf(random.nextInt((int)(vida) - (int)(vida) / 8));
        super.activa = true;
        /**
         * Proceso que asigna la funcion de hora de descanso de la caja y que cuando se da esta hora, pone a dormir a la caja
         */
        Instant descanso = inicio.plusMillis(super.horaDescanso);
        boolean haDescansado = false;
        while (Instant.now().isBefore(fin) || cliente != null){
            if (Instant.now().isAfter(descanso) && !haDescansado){
                System.out.println("Ya empezó la hora de descanso de la caja: " + super.id);
                super.activa = false;
                if (cliente == null){
                    try{
                        System.out.println("Descansando caja: " + super.id);
                        Thread.sleep(super.tiempoDescanso);
                    }catch (InterruptedException e){}
                    super.activa = true;
                    haDescansado = true;
                    System.out.println("Ha terminado la hora de descanso de la caja: " + super.id);
                }
            }
            /**
             * Proceso que duerme la caja si no tiene clientes
             */
            if (cliente == null){
                try{
                    Thread.sleep(10);
                }catch (InterruptedException e){}
                continue;
            }
            super.atendidos += 1;
            for (int i = 0; i < cliente.getCarrito().size(); i++){
                int tiempo = 100 + random.nextInt(101);
                if (random.nextDouble() <= super.ratio){
                    super.cancelar();
                    String eliminado = cliente.eliminaAleatorio();
                    clienteBuilder.update(eliminado);
                    continue;
                }
                try{
                    Thread.sleep(Long.valueOf(tiempo));
                } catch(InterruptedException e){}
            }
            super.productosvendidos += cliente.getCarrito().size();
            /**
             * Procesamiento del ticket generado
             */
            Ticket generado = super.creaTicket(cliente.getCarrito());
            almacen.procesaTicket(generado);
            cliente = null;
            areaRapida.update(super.id);
        }
        this.activa = false;
    }
    /**
     * Metodo que inicializa la caja, en un hilo separado
     * */
    @Override
    public void run(){
        atender();
    }
    
}