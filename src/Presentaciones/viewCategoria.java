package Presentaciones;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class viewCategoria extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField codigo;
    private JTextField nombre;
    private JTextField textField3;
    private JPanel search;
    private JButton eliminarButton;
    private JButton limpiarButton;
    private JButton guardarButton;
    private JTextField textField4;
    private JButton searchButton;
    private JLabel codigoText;
    private JLabel nombretext;
    private JLabel descripcionText;
    private JTable table1;
    private JButton NEXTTTButton;

    protected Controller controller;
    public viewCategoria(Controller controller) {
        setContentPane(contentPane);
        setModal(true);
        this.controller = controller;
        configureTable();

    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void PassTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PassTextFieldKeyTyped
        CheckTextFields();
    }//GEN-LAST:event_PassTextFieldKeyTyped

    private void UserTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UserTextFieldKeyTyped
        CheckTextFields();
    }

    public void Load(){
        java.awt.EventQueue.invokeLater(() -> {
            this.setSize(800, 600); // Establece el tamaño de la ventana (ancho x alto en píxeles)
            this.setLocationRelativeTo(null); // Centra la ventana en la pantalla
            this.setVisible(true);
        });
    }



// Comprueba que los campos esten llenos de informaion
    private void CheckTextFields()
    {
        if (nombretext.getText().trim().isEmpty() || codigoText.getText().trim().isEmpty() || descripcionText.getText().trim().isEmpty())
            guardarButton.setEnabled(false);
        else
            guardarButton.setEnabled(true);
           eliminarButton.setEnabled(true);

    }

    private void configureTable() {
        DefaultTableModel tableModel = new DefaultTableModel(
                new Object[][]{}, // Datos iniciales (vacío)
                new String[]{"Código", "Nombre", "Descripción"} // Nombres de las columnas
        );

        // Asignar el modelo a la tabla
        table1.setModel(tableModel);

        // Opcional: Configurar propiedades de la tabla
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Selección de una sola fila
        table1.setAutoCreateRowSorter(true); // Habilitar ordenamiento
    }
}
