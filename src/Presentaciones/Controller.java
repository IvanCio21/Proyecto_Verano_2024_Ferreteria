package Presentaciones;

public class Controller {
    protected viewCategoria view;
    protected  Model model;

    public Controller() {
        this.view = new viewCategoria(this );
        //this.model = new Model();
        // Carga los archivos

    }

    public void Init(){

        view.Load();
    }


}
