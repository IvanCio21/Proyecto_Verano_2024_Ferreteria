package Main;


import Vista.CategoriaController;
import Vista.CategoriaController;
import Vista.GUI;
import Vista.Model;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {

            SwingUtilities.invokeLater(() -> {
                try {
                   new CategoriaController(new Model(), new GUI());
                } catch (Exception var1) {
                    Exception e = var1;
                    throw new RuntimeException(e);
                }
            });

    }
}