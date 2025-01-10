package Vista;

import Logic.Category;
import Logic.Service;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Control {

    private static Model model;
    private GUI gui;
    private static Service service;

    private static JTable tableArticulos;
    private static JTable tableCategorias;
    private static JTable tableSubCategorias;

    public Control(Model model, GUI gui) {
        service = new Service();
        this.model = model;
        this.gui = gui;
        gui.setModel(model);
        gui.setController(this);
        tableArticulos = new JTable();
        tableCategorias = new JTable();
        tableSubCategorias = new JTable();
        service.cargarxml();
        this.gui.setVisible(true);
        show();
        tableCategorias();
    }

    public static void show(){ //Mostrar las actualizaciones del observable
        model.setTableArticulos(tableArticulos);
        model.setTableCategorias(tableCategorias);
        model.setTableSubCategorias(tableSubCategorias);
        model.commit();//encargador de actualizar
    }

    public void hide(){this.gui.setVisible(false);}


    void exit(){
        service.GurdarXml();
    }


    //TABLAS//

    private static void setTableArticulos(){
        //tableArticulos.setModel(new DefaultTableModel(new String[]{"ID" , "Nombre", "Descripcion"}));
    }



    public void  tableCategorias(){

        String[] columnNames = {"Id","Nombre", "Descripción"};
        Object[][] data = new Object[service.getAllCategorias().size()][6];

        for (int i = 0; i < service.getAllCategorias().size(); i++) {
            Category categoria = service.getAllCategorias().get(i);
            data[i][0] = categoria.getId();
            data[i][1] = categoria.getName();
            data[i][2] = categoria.getDescription();
        }

        tableCategorias.setModel(new DefaultTableModel(data, columnNames));
    }

    public  void agregarCategoria(String id, String nombre, String de) throws Exception {
        Category cat = new Category(id, nombre, de);
        service.addCategoria(cat);
        tableCategorias();
        show();
    }
}
