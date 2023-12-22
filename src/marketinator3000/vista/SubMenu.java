package marketinator3000.vista;

import java.util.LinkedList;

public class SubMenu implements Menu{
    /**
     * Declaracion de los atributos de SubMenu
     */
    private String accion;
    private int id;
    private String texto;
    private Menu anterior;
    private LinkedList<Menu> menu;
    /**
     * Metodo constructor de las entradas del submenu
     * @param id El identificador de la opcion del menu
     * @param texto El texto a mostrar en pantalla
     * @param accion El identificador de la accion que realiza el menu
     */
    public SubMenu(int id, String texto, String accion){
        this.id = id;
        this.texto = texto;  
        this.accion = accion;
        this.menu = new LinkedList<Menu>();
    }

        /**
     * Metodo para agregar una opcion al menu.
     *
     * @param opcion La opcion del menu a agregar.
     */
    public void addEntry(Menu opcion) {
        menu.add(opcion);
        opcion.setAnterior(this);
    }

    /**
     * Metodo para mostrar la entrada del menu.
     */
    @Override
    public void mostrar() {
        System.out.println(id + ". " + texto);
    }

    /**
     * Metodo para mostrar el submenu.
     */
    @Override
    public void mostrarSubMenu() {
        for (Menu m : menu) {
            m.mostrar();
        }
    }

    /**
     * Metodo para obtener el menu anterior.
     *
     * @return El menu anterior.
     */
    @Override
    public Menu getAnterior() {
        return this.anterior;
    }

    /**
     * Metodo para establecer el menu anterior.
     *
     * @param anterior El menu anterior a establecer.
     */
    @Override
    public void setAnterior(Menu anterior) {
        this.anterior = anterior;
    }

    /**
     * Metodo para obtener un menu específico por su identificador.
     *
     * @param id El identificador del menu.
     * @return El menu con el identificador proporcionado.
     */
    @Override
    public Menu getMenu(int id) {
        for (Menu m : this.menu) {
            if (m.getId() != id) continue;
            return m;
        }
        return null;
    }

    /**
     * Metodo para obtener el identificador del menu.
     *
     * @return El identificador del menu.
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Metodo para obtener la accion asociada al menu.
     *
     * @return La accion asociada al menu.
     */
    @Override
    public String getAccion() {
        return this.accion;
    }
}
