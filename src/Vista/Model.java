package Vista;

import Logic.Category;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
//Observable:Genera eventos o notificaciones
//se registra en el observable

public class Model extends Observable  {

private List<Category> categories;
private JTable tableSubCategories = new JTable();
private JTable tableCategories = new JTable();



public Model(){categories = new ArrayList<>();}

public List<Category> getCategories(){
    return categories;
}

    public void setCategories(List<Category> categorias) {
        this.categories = categorias;
    }

    public void addCategories(Category categoria)
    {
        this.categories.add(categoria);
    }
    public JTable getTableSubCategories() {return tableSubCategories;}
    public void setTableSubCategories(JTable tableSubCategories) {this.tableSubCategories = tableSubCategories;}
    public JTable getTableCategories() {return tableCategories;}
    public void setTableCategories(JTable tableCategories) {this.tableCategories = tableCategories;}

    //    //NECESITO SETTEARLOS CON CONTENIDO
//    private JTable tableArticulos = new JTable();
//    private JTable tableCategorias = new JTable();
//    private JTable tableSubCategorias = new JTable();
//
//    public JTable getTableArticulos() {
//        return tableArticulos;
//    }
//
//    public void setTableArticulos(JTable tableArticulos) {
//        this.tableArticulos = tableArticulos;
//    }
//
//    public JTable getTableCategorias() {
//        return tableCategorias;
//    }
//
//public void setTableCategorias(JTable tableCategorias) {
//        this.tableCategorias = tableCategorias;
//    }
//
//
//    public void setTableSubCategorias(JTable tableSubCategorias) {
//        this.tableSubCategorias = tableSubCategorias;
//    }
//
//    //PATRON OBSERVADOR//
     public synchronized void addObserver(Observer o) {
        super.addObserver(o);
        this.commit();
    }
//
   public void commit() {
       this.setChanged();
       this.notifyObservers();
   }


}
