package Main;


import Vista.Controller.Controller;
import Vista.GUI;
import Vista.Model.Model;

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