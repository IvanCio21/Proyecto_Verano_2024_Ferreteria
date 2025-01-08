package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Control {

    private static Model model;
    private GUI gui;

    private static JTable tableArticulos;
    private static JTable tableCategorias;
    private static JTable tableSubCategorias;

    public Control(Model model, GUI gui) {
        this.model = model;
        this.gui = gui;
        gui.setModel(model);
        gui.setController(this);
        tableArticulos = new JTable();
        tableCategorias = new JTable();
        tableSubCategorias = new JTable();


    }

    public static void show(){ //Mostrar las actualizaciones del observable
        model.setTableArticulos(tableArticulos);
        model.setTableCategorias(tableCategorias);
        model.setTableSubCategorias(tableSubCategorias);
        model.commit();//encargador de actualizar
    }

    //TABLAS//

    private static void setTableArticulos(){
        //tableArticulos.setModel(new DefaultTableModel(new String[]{"ID" , "Nombre", "Descripcion"}));
    }

}
