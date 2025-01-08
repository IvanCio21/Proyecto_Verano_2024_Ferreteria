package Vista;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;
//Observable:Genera eventos o notificaciones
//se registra en el observable

public class Model extends Observable {
    //NECESITO SETTEARLOS CON CONTENIDO
    private JTable tableArticulos = new JTable();
    private JTable tableCategorias = new JTable();
    private JTable tableSubCategorias = new JTable();

    public JTable getTableArticulos() {
        return tableArticulos;
    }

    public void setTableArticulos(JTable tableArticulos) {
        this.tableArticulos = tableArticulos;
    }

    public JTable getTableCategorias() {
        return tableCategorias;
    }

    public void setTableCategorias(JTable tableCategorias) {
        this.tableCategorias = tableCategorias;
    }

    public JTable getTableSubCategorias() {
        return tableSubCategorias;
    }

    public void setTableSubCategorias(JTable tableSubCategorias) {
        this.tableSubCategorias = tableSubCategorias;
    }

    //PATRON OBSERVADOR//
     public synchronized void addObserver(Observer o) {
        super.addObserver(o);
        this.commit();
     }

     public void commit(){
        this.setChanged();
        this.notifyObservers();
     }

}
