package Vista;

import Logic.Category;
import Logic.Service;

import javax.swing.*;

public class CategoriaController {

    private Model model;
    private GUI gui;
    private Service service;

    public CategoriaController() {
        this.model = new Model();   // Aquí creas el modelo internamente
        this.gui = new GUI();       // Aquí creas la vista internamente
        this.service = new Service(); // Inicializas el servicio
        iniciar();
        guardarBoton();  // Asegúrate de agregar el listener aquí
        limpiarBoton();
    }

    public void iniciar() {
        JFrame frame = new JFrame("Sistema de Inventarios");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Para cerrar la ventana correctamente
        frame.setContentPane(gui.getPestaniasPanel());
        frame.pack(); // Ajusta el tamaño según el contenido
        frame.setVisible(true); // Muestra la ventana
    }

    public void guardarBoton(){
        gui.getGuardarButton().addActionListener(e -> agregarCategoria());
    }

    public void agregarCategoria() {
        String codigo = gui.getCategoriaCodigo().getText();
        String nombre = gui.getCategoriaNombre().getText();
        String descripcion = gui.getCategoriaDescripcion().getText();

        Category newCategory =  new Category(codigo,nombre,descripcion);

        service.addCategory(newCategory);

    }

    public void limpiarBoton(){
        gui.getLimpiarButton().addActionListener(e -> limpiar());
    }

    public void limpiar(){
        gui.getCategoriaCodigo().setText("");
        gui.getCategoriaNombre().setText("");
        gui.getCategoriaDescripcion().setText("");
    }

//    private static Service service;
//
//    private static JTable tableArticulos;
//    private static JTable tableCategorias;
//    private static JTable tableSubCategorias;
//
//    public Control(Model model, GUI gui) {
//        service = new Service();
//        this.model = model;
//        this.gui = gui;
//        gui.setModel(model);
//        gui.setController(this);
//        tableArticulos = new JTable();
//        tableCategorias = new JTable();
//        tableSubCategorias = new JTable();
//        this.gui.setVisible(true);
//        show();
//        tableCategorias();
//    }
//
//    public static void show(){ //Mostrar las actualizaciones del observable
//        model.setTableArticulos(tableArticulos);
//        model.setTableCategorias(tableCategorias);
//        model.setTableSubCategorias(tableSubCategorias);
//        model.commit();//encargador de actualizar
//    }
//
//    public void hide(){this.gui.setVisible(false);}
//
//
//    void exit(){ }
//
//
//    //TABLAS//
//
//    private static void setTableArticulos(){
//        //tableArticulos.setModel(new DefaultTableModel(new String[]{"ID" , "Nombre", "Descripcion"}));
//    }
//
//
//
//    public void  tableCategorias(){
//
//    }
//
//    public  void agregarCategoria(String id, String nombre, String de) throws Exception {
//
//    }
}
