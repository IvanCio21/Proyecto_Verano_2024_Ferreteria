package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//Este sera el observado: Escucha los cambios
//actualiza cuando algo del observable cambia

public class GUI extends JFrame {
    //Control y model de la view
    private CategoriaController control;
    private Model model;
    //elementos

    private JScrollPane articulosTable;
    private JLabel codigoLabel;
    private JLabel nombreLabel;
    private JLabel descripcionLabel;
    private JTabbedPane PestaniasPanel;
    private JPanel panelPrincipal;
    private JPanel PanelCategoria;
    private JTextField codigo;
    private JTextField nombre;
    private JPanel search;
    private JButton searchButton;
    private JButton limpiarCategoriaButton;
    private JButton guardarButton;
    private JButton eliminarButton;
    private JTextField descripcionsubCategoriaField;
    private JTextField buscarSubCategoria;
    private JPanel Categoria;
    private JTable presentacionesTable;
    private JTextField categoriaArticuloTf;
    private JTextField subCategoriaArticuloTf;
    private JTextField codigoArticuloTf;
    private JTextField nombreArticuloTf;
    private JTextField descripcionArticuloTf;
    private JTextField buscarIdArticuloTf;
    private JButton buscarArticuloBtn;
    private JButton guardarArticulosBtn;
    private JButton limpiarArticulosBtn;
    private JButton eliminarArticulosBtn;
    private JPanel AcercaDe;
    private JPanel Articulo;
    private JPanel SubCategoria;
    private JScrollPane ListadoTable;
    private JTable jTableArticulos;
    private JTable subCategoriasTable;
    private JButton limpiarSubcategoriaBtn;
    private JButton eliminarsubcategoriaBtn;
    private JButton GuardarSubcategoriaBtn;
    private JButton buscarSubcategoriaBtn;
    private JTextField buscarCategoria;
    private JTextField descripcionCategoria;
    private JTable listaCategoria;
    private JScrollPane panelListado;
    private JTextField codigosubCategoriaField;
    private JTextField nombresubCategoriaField;
    private JTextField categoriaSubcategoriaField;
    private JTextField subCategoriaSubCategoriaField;
    private JTextField marcaArticuloTf;

    private DefaultTableModel categoriaTableModel;


    /*
    //Categoria

    public JTextField getCategoriaCodigo(){
    return codigo;
    }

    public JTextField getCategoriaNombre(){
    return nombre;
    }

    public JTextField getCategoriaDescripcion(){
    return descripcionCategoria;
    }

    public JButton getGuardarButton(){
    return guardarButton;
    }
    public JButton getEliminarButton(){
    return eliminarButton;
    }
    public JButton getLimpiarButton(){
    return limpiarCategoriaButton;
    }

    public JTextField getBuscarCategoria(){
    return buscarCategoria;
    }

    public JTable getListaCategoria() {
        return listaCategoria;
    }

    // Obtener el modelo de la tabla
    public DefaultTableModel getModeloTabla() {
        return (DefaultTableModel) listaCategoria.getModel();
    }

    public JTabbedPane getPestaniasPanel() {
    return PestaniasPanel;
    }
    */


    public GUI() throws Exception{
        initComponets();
    }

    //Controller
    public void setController(CategoriaController control) {
        this.control = control;
    }

    public CategoriaController getController() {
        return control;
    }

    //End controller

    //Model
    public void setModel(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    //End model//

    //TABLES VIEWS
    public void updateTables() {
        listaCategoria.setModel(model.getTableCategorias().getModel());
        listaCategoria.setColumnModel(model.getTableCategorias().getColumnModel());
        jTableArticulos.setModel(model.getTableArticulos().getModel());
        jTableArticulos.setColumnModel(model.getTableArticulos().getColumnModel());
        subCategoriasTable.setModel(model.getTableArticulos().getModel());
        subCategoriasTable.setColumnModel(model.getTableArticulos().getColumnModel());
    }

    public JTable getTableCategorias(){
        return listaCategoria;
    }

    public JTable getTableArticulos(){
        return jTableArticulos;
    }

    public JTable getSubCategoriasTable(){
        return subCategoriasTable;
    }

    //END TABLES VIEWS//


    //COMPONENTES//

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_  formWindowClosing
        control.exit();
    }
    private void initComponets() throws Exception {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MENU PRINCIPAL");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        this.setContentPane(panelPrincipal); // Seteo contenido del form al JFrame que se acaba de crear
        this.pack();




        //BOTONES ARTICULOS//
        limpiarArticulosBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearItems();
            }
        });

        eliminarArticulosBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        guardarArticulosBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(validateFormItems()) {
                        control.agregarItems(codigoArticuloTf.getText(), marcaArticuloTf.getText(), nombreArticuloTf.getText(), descripcionArticuloTf.getText());
                    }
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }

            }
        });

        buscarArticuloBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //BOTONES CATEGORIA//

        limpiarCategoriaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearCategory();
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        this.guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(validateFormCategories()) {
                        control.agregarCategoria(codigo.getText(), nombre.getText(), descripcionCategoria.getText());
                    }
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }
            }
        });


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //BOTONES SUBCATEGORIA//
        limpiarSubcategoriaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearSubCategory();
            }
        });

        eliminarsubcategoriaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        GuardarSubcategoriaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(validateFormSubCategories()) {
                        control.agregarsubCategoria(codigosubCategoriaField.getText(), nombresubCategoriaField.getText(), descripcionsubCategoriaField.getText());
                    }
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }
            }
        });

        buscarSubcategoriaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    private boolean validateFormCategories() {

        javax.swing.border.Border errorBorder = BorderFactory.createLineBorder(Color.RED, 2);
        boolean valid = true;
        if (codigo.getText().isEmpty()) {
            valid = false;
            codigoLabel.setBorder(errorBorder);
            codigoLabel.setToolTipText("ID requerido");
        } else {
            codigoLabel.setBorder(null);
            codigoLabel.setToolTipText(null);
        }

        if (nombre.getText().isEmpty()) {
            valid = false;
            nombreLabel.setBorder(errorBorder);
            nombreLabel.setToolTipText("Nombre requerido");
        } else {
            nombreLabel.setBorder(null);
            nombreLabel.setToolTipText(null);
        }

        if (descripcionCategoria.getText().isEmpty()) {
            valid = false;
            descripcionLabel.setBorder(errorBorder);
            descripcionLabel.setToolTipText("Descripcion requerido");
        }else {
            descripcionLabel.setBorder(null);
            descripcionLabel.setToolTipText(null);
        }

        return valid;
    }

    private boolean validateFormSubCategories() {

        javax.swing.border.Border errorBorder = BorderFactory.createLineBorder(Color.RED, 2);
        boolean valid = true;
        if (codigosubCategoriaField.getText().isEmpty()) {
            valid = false;
            codigosubCategoriaField.setBorder(errorBorder);
            codigosubCategoriaField.setToolTipText("ID requerido");
        } else {
            codigosubCategoriaField.setBorder(null);
            codigosubCategoriaField.setToolTipText(null);
        }

        if (nombresubCategoriaField.getText().isEmpty()) {
            valid = false;
            nombresubCategoriaField.setBorder(errorBorder);
            nombresubCategoriaField.setToolTipText("Nombre requerido");
        } else {
            nombresubCategoriaField.setBorder(null);
            nombresubCategoriaField.setToolTipText(null);
        }

        if (descripcionsubCategoriaField.getText().isEmpty()) {
            valid = false;
            descripcionsubCategoriaField.setBorder(errorBorder);
            descripcionsubCategoriaField.setToolTipText("Descripcion requerido");
        }else {
            descripcionsubCategoriaField.setBorder(null);
            descripcionsubCategoriaField.setToolTipText(null);
        }
        return valid;
    }

    private boolean validateFormItems() {

        javax.swing.border.Border errorBorder = BorderFactory.createLineBorder(Color.RED, 2);
        boolean valid = true;
        if (codigoArticuloTf.getText().isEmpty()) {
            valid = false;
            codigoArticuloTf.setBorder(errorBorder);
            codigoArticuloTf.setToolTipText("ID requerido");
        } else {
            codigoArticuloTf.setBorder(null);
            codigoArticuloTf.setToolTipText(null);
        }

        if (nombreArticuloTf.getText().isEmpty()) {
            valid = false;
            nombreArticuloTf.setBorder(errorBorder);
            nombreArticuloTf.setToolTipText("Nombre requerido");
        } else {
            nombreArticuloTf.setBorder(null);
            nombreArticuloTf.setToolTipText(null);
        }

        if (descripcionArticuloTf.getText().isEmpty()) {
            valid = false;
            descripcionArticuloTf.setBorder(errorBorder);
            descripcionArticuloTf.setToolTipText("Descripcion requerido");
        }else {
            descripcionArticuloTf.setBorder(null);
            descripcionArticuloTf.setToolTipText(null);
        }

        if (marcaArticuloTf.getText().isEmpty()) {
            valid = false;
            marcaArticuloTf.setBorder(errorBorder);
            marcaArticuloTf.setToolTipText("ID requerido");
        } else {
            marcaArticuloTf.setBorder(null);
            marcaArticuloTf.setToolTipText(null);
        }
        return valid;
    }

    public void clearCategory(){
        nombre.setText("");
        codigo.setText("");
        descripcionCategoria.setText("");
        buscarCategoria.setText("");
    }

    public void clearSubCategory(){
        categoriaSubcategoriaField.setText("");
        subCategoriaSubCategoriaField.setText("");
        codigosubCategoriaField.setText("");
        nombresubCategoriaField.setText("");
        descripcionsubCategoriaField.setText("");
        buscarSubCategoria.setText("");
    }

    public void clearItems(){
        categoriaArticuloTf.setText("");
        subCategoriaArticuloTf.setText("");
        codigoArticuloTf.setText("");
        nombreArticuloTf.setText("");
        descripcionArticuloTf.setText("");

    }



//
//    public void redimensionarAnchoColumna(JTable tabla) {
//        TableColumnModel columnModel = tabla.getColumnModel();
//
//        for(int column = 0; column < tabla.getColumnCount(); ++column) {
//            int width = 70;
//
//            for(int row = 0; row < tabla.getRowCount(); ++row) {
//                TableCellRenderer renderer = tabla.getCellRenderer(row, column);
//                Component comp = tabla.prepareRenderer(renderer, row, column);
//                width = Math.max(comp.getPreferredSize().width + 1, width);
//            }
//
//            if (width > 300) {
//                width = 300;
//            }
//
//            columnModel.getColumn(column).setPreferredWidth(width);
//        }
//
//    }


}
