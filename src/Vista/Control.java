package Vista;

import javax.swing.*;

public class Control {

    private Model model;
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

}
