package marketinator3000.vista;

public class EntradaMenu implements Menu{
    /**
     * Declaracion de los atributos de la clase
     */
    private String accion;
    private int id;
    private Menu anterior;
    private String texto;

    /**
     * Metodo constructor de las Entradas del menu
     * @param id El id de la opcion
     * @param texto El texto que debe mostrarse en pantalla
     * @param accion El texto para identificar el menu, y que accion debe realizar
     */
    public EntradaMenu(int id, String texto, String accion){
        this.id = id;
        this.texto = texto;
        this.accion = accion;
    }

    /**
     * Metodo que imprime en pantalla una opcion del menu
     */
    @Override
    public void mostrar(){
        System.out.println(id + ". " + texto);
    }
    /**
     * Metodos que implementan la clase menu
     */
    @Override
    public void mostrarSubMenu(){
        return;
    }
    /**
     * @param opcion Las opciones del menu
     */
    @Override 
    public void addEntry(Menu opcion){
        return;
    }

    /**
     * Metodo que obtiene el menu anterior 
     * @return El menu anterior
     */
    @Override 
    public Menu getAnterior(){
        return this.anterior;
    }

    /**
     * Metodo que fija cual es el anterior Menu
     * @param anterior El menu anterior
     */
    @Override
    public void setAnterior(Menu anterior){
        this.anterior = anterior; 
    };
    
    /**
     * Metodo que obtiene el menu en base a la id 
     * @param id El identificador del menu
     */
    @Override
    public Menu getMenu(int id){
        return null;
    }
    /**
     * Metodo que obtiene el id 
     */
    @Override
    public int getId(){
        return this.id;
    }
    /**
     * Metodo que obtiene la accion 
     */
    @Override
    public String getAccion(){
        return this.accion;
    }

}