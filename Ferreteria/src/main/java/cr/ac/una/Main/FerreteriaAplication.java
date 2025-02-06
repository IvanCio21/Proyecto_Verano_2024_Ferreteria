package cr.ac.una.Main;


import cr.ac.una.MVC.Controller.Controller;
import cr.ac.una.MVC.View.Login;
import cr.ac.una.MVC.Model.Model;
import cr.ac.una.MVC.View.GUI;

import javax.swing.*;

public class FerreteriaAplication {
    public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                try {
                  new Controller(new Model(), new GUI(),new Login());
               } catch (Exception var1) {
                    Exception e = var1;
                    throw new RuntimeException(e);
                }
         });
    }
}