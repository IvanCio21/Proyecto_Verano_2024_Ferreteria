package cr.ac.una.MVC.View;

import cr.ac.una.MVC.Controller.Controller;
import cr.ac.una.MVC.Model.Model;
import cr.ac.una.Protocol.User;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class Login implements Observer {
    private JPanel PanelLoginBase;
    private JPanel loginPanel;
    private JTextField FieldUsuario;
    private JPasswordField FieldContrasenia;
    private JButton buttonFinish;
    private JButton ButtonIngreso;


    Model model;
    Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;

        ButtonIngreso.addActionListener(e -> {
            String usuario = FieldUsuario.getText();
            String contrasenia = new String(FieldContrasenia.getPassword());

            User user = new User(contrasenia,"",usuario);

            try {
                controller.login(user);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        buttonFinish.addActionListener(e -> System.exit(0));
    }

    public JPanel getPanelLoginBase() {
        return PanelLoginBase;
    }

    public void update(Observable o, Object arg) {
        System.out.println("El modelo ha sido actualizado: " + arg);

        if ((int) arg == Model.USER) {
            JOptionPane.showMessageDialog(null, "Usuario actualizado en el modelo");
        }
    }
}
