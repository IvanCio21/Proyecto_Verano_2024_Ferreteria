package Main;


import Vista.GUI;
import Vista.Model;
import Vista.Control;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

            SwingUtilities.invokeLater(() -> {
                try {
                   new Control(new Model(), new GUI());
                } catch (Exception var1) {
                    Exception e = var1;
                    throw new RuntimeException(e);
                }
            });

    }
}