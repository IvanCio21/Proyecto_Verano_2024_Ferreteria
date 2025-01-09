package Vista;

import Logic.Category;
import Logic.Service;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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

    //TABLAS//

    private static void setTableArticulos(){
        //tableArticulos.setModel(new DefaultTableModel(new String[]{"ID" , "Nombre", "Descripcion"}));
    }



    private static void  tableCategorias(){
      tableCategorias.setModel(new DefaultTableModel(new String[]{
              "ID","Nombre","Descripcion"}, service.getAllCategorias().size()){
          public  boolean isCellEditable(int row, int column) { return false;}
      });

      for(int i = 0; i < service.getAllCategorias().size(); ++i) {
          Category category = service.getAllCategorias().get(i);
          tableCategorias.setValueAt(category.getId(), i, 0);
          tableCategorias.setValueAt(category.getName(), i, 1);
          tableCategorias.setValueAt(category.getDescription(), i, 2);
      }
    }


}
