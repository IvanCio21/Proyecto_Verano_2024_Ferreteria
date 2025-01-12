package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
//Este sera el observado: Escucha los cambios
//actualiza cuando algo del observable cambia

public class GUI {
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
    private JButton NEXTTTButton;
    private JTextField codigo;
    private JTextField nombre;
    private JPanel search;
    private JButton searchButton;
    private JButton limpiarCategoriaButton;
    private JButton guardarButton;
    private JButton eliminarButton;
    private JTextField textField3;
    private JTextField textField4;
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

    private DefaultTableModel categoriaTableModel;

public void GUI(){ }

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

//    public GUI(){
//        initComponets();
//    }

//    public void setController(Control control) {
//        this.control = control;
//    }
//
//    public Control getController() {
//        return control;
//    }
//
//    public void setModel(Model model) {
//        this.model = model;
//      //  model.addObserver(this);
//    }
//    public Model getModel() {
//        return model;
//    }
//
//
//    //TABLES VIEWS
//    @Override
//    public void update(Observable o, Object arg) {
//        tableCategorias.setModel(model.getTableCategorias().getModel());
//        tableCategorias.setColumnModel(model.getTableCategorias().getColumnModel());
//        jTableArticulos.setModel(model.getTableArticulos().getModel());
//        jTableArticulos.setColumnModel(model.getTableArticulos().getColumnModel());
//        subCategoriasTable.setModel(model.getTableArticulos().getModel());
//        subCategoriasTable.setColumnModel(model.getTableArticulos().getColumnModel());
//    }
//
//
//
//
//    public JTable getTableCategorias(){
//        return tableCategorias;
//    }
//
//    public JTable getTableArticulos(){
//        return jTableArticulos;
//    }
//
//    public JTable getSubCategoriasTable(){
//        return subCategoriasTable;
//    }
//
//    //END TABLES VIEWS//
//
//    //COMPONENTES//
//    public void initComponets(){
//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//        setTitle("LA MEJOR FERRETERIA");
//
//        addWindowListener(new java.awt.event.WindowAdapter() {
//            public void windowClosing(java.awt.event.WindowEvent evt) {
//                formWindowClosing(evt);
//            }
//        });
//
//
//        this.setContentPane(panelPrincipal); // Seteo contenido del form al JFrame que se acaba de crear
//        this.pack();
//
//        tableCategorias = new JTable();
//
//        //BOTONES ARTICULOS//
//        limpiarArticulosBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//
//        eliminarArticulosBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//
//        guardarArticulosBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//
//            }
//        });
//
//        buscarArticuloBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//
//        //BOTONES CATEGFORIA//
//
//        limpiarCategoriaButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                clearTable();
//            }
//        });
//
//        eliminarButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//
//        this.guardarButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    if(validateForm()){
//                    GUI.this.control.agregarCategoria(codigo.getText(),nombre.getText(),descripcionCategoria.getText());
//                    clearTable();
//                    }
//
//                } catch (Exception ex) {
//                    throw new RuntimeException(ex);
//                }
//
//            }
//        });
//
//        searchButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//
//        //BOTONES SUBCATEGORIA//
//        limpiarSubcategoriaBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//
//        eliminarsubcategoriaBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//
//        GuardarSubcategoriaBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//
//        buscarSubcategoriaBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//
//    }
//
//    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_  formWindowClosing
//        control.exit();
//    }
//
//    private void createUIComponents() {
//        // TODO: place custom component creation code here
//    }
//
//    private boolean validateForm() {
//
//        javax.swing.border.Border errorBorder = BorderFactory.createLineBorder(Color.RED, 2);
//        boolean valid = true;
//        if (codigo.getText().isEmpty()) {
//            valid = false;
//            codigoLabel.setBorder(errorBorder);
//            codigoLabel.setToolTipText("ID requerido");
//        } else {
//            codigoLabel.setBorder(null);
//            codigoLabel.setToolTipText(null);
//        }
//
//        if (nombre.getText().isEmpty()) {
//            valid = false;
//            nombreLabel.setBorder(errorBorder);
//            nombreLabel.setToolTipText("Nombre requerido");
//        } else {
//            nombreLabel.setBorder(null);
//            nombreLabel.setToolTipText(null);
//        }
//
//        if (descripcionCategoria.getText().isEmpty()) {
//            valid = false;
//            descripcionLabel.setBorder(errorBorder);
//            descripcionLabel.setToolTipText("Descripcion requerido");
//        }else {
//            descripcionLabel.setBorder(null);
//            descripcionLabel.setToolTipText(null);
//        }
//
//        return valid;
//    }
//
//    void clearTable(){
//        nombre.setText("");
//        codigo.setText("");
//        descripcionCategoria.setText("");
//        buscarCategoria.setText("");
//    }
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
