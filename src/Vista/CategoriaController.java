package Vista;

import Logic.Category;
import Logic.Items;
import Logic.Service;
import Logic.SubCategory;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CategoriaController {

    private static Model model;
    private GUI gui;
    //private Service service;

    /*
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
        gui.getGuardarButton().addActionListener(e -> {
            try {
                agregarCategoria();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }


    public void agregarCategoria() throws Exception {
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
    }*/

    private static Service service;
    private static JTable tableArticulos;
    private static JTable tableCategorias;
    private static JTable tableSubCategorias;

    public CategoriaController(Model model, GUI gui) throws Exception {
        service = new Service();
        this.model = model;
        this.gui = gui;
        gui.setModel(model);
        gui.setController(this);
        tableArticulos = new JTable();
        tableCategorias = new JTable();
        tableSubCategorias = new JTable();
        service.loadXml();
        TableCategories();
        show();
        this.gui.setVisible(true);
    }

    //public void hide() {
    //this.gui.setVisible(false);
    //}

    public void exit() { //Funcion Final guardar aqui solamente
        service.saveXml();
    }
    public static void show(){ //Mostrar las actualizaciones del observable
        model.setTableArticulos(tableArticulos);
        model.setTableCategorias(tableCategorias);
        model.setTableSubCategorias(tableSubCategorias);
    }


    //TABLAS//

    private static void TableCategories() throws Exception {

        List<Category> categories = service.allCategories(); // Fetch all categories from the service

        // Prepare the data array
        Object[][] data = new Object[categories.size()][3]; // Assuming 3 columns: ID, Nombre, Descripcion

        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            data[i][0] = category.getId();        // Set ID for the first column
            data[i][1] = category.getName();      // Set Name for the second column
            data[i][2] = category.getDescription(); // Set Description for the third column
        }

        // Set the model with data and column names
        tableCategorias.setModel(new DefaultTableModel(data, new String[]{"ID", "Nombre", "Descripcion"}) {
            public boolean isCellEditable(int row, int column) {
                return false; // Disable editing
            }
        });
    }

    JScrollPane scrollPane = new JScrollPane(tableCategorias);


    //End tablas

    public void agregarCategoria(String id, String nombre, String description) throws Exception {
        Category newCategory =  new Category(id,nombre,description);
        service.addCategory(newCategory);
    }

    public void agregarsubCategoria(String id, String nombre, String description) throws Exception {
        SubCategory newsubCategory =  new SubCategory(id,nombre,description);
        service.addsubCategory(newsubCategory);
    }

    public void agregarItems(String id,String marca, String nombre, String description) throws Exception {
        Items newItem =  new Items(id, marca, nombre, description);
        service.addItems(newItem);
    }

}
