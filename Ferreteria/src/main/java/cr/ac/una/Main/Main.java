package cr.ac.una.Main;


import cr.ac.una.MVC.Controller.Controller;
import cr.ac.una.MVC.Model.Model;
import cr.ac.una.MVC.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                try {
                  new Controller(new Model(), new GUI());
               } catch (Exception var1) {
                    Exception e = var1;
                    throw new RuntimeException(e);
                }
         });
    }
}