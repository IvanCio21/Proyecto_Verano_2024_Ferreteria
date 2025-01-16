package Main;


import MVC.Controller.Controller;
import MVC.GUI;
import MVC.Model.Model;

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