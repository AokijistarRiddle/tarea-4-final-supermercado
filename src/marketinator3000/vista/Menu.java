package marketinator3000.vista;

/**
 * Interfaz de menu que define los metodos comunes para la implementacion de menus.
 */
public interface Menu {

    /**
     * Metodo para mostrar el menu.
     */
    public void mostrar();

    /**
     * Metodo para agregar una opcion al menu.
     *
     * @param opcion La opcion del menu a agregar.
     */
    public void addEntry(Menu opcion);

    /**
     * Metodo para mostrar el submenu.
     */
    public void mostrarSubMenu();

    /**
     * Metodo para obtener el menu anterior.
     *
     * @return El menu anterior.
     */
    public Menu getAnterior();

    /**
     * Metodo para establecer el menu anterior.
     *
     * @param menu El menu anterior a establecer.
     */
    public void setAnterior(Menu menu);

    /**
     * Metodo para obtener un menu específico por su identificador.
     *
     * @param id El identificador del menu.
     * @return El menu con el identificador proporcionado.
     */
    public Menu getMenu(int id);

    /**
     * Metodo para obtener el identificador del menu.
     *
     * @return El identificador del menu.
     */
    public int getId();

    /**
     * Metodo para obtener la accion asociada al menu.
     *
     * @return La accion asociada al menu.
     */
    public String getAccion();

}
