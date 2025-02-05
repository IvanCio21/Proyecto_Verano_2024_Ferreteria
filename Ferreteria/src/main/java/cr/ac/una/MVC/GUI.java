package cr.ac.una.MVC;

import cr.ac.una.MVC.Controller.*;
import cr.ac.una.MVC.Model.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
//Este sera el observado: Escucha los cambios
//actualiza cuando algo del observable cambia

public class GUI extends JFrame {
    //Control y model de la view
    private Controller controller;
    private Model model;
    //elementos de el TAG DE CATEGORIA
    private JScrollPane panelListado;


    private JScrollPane articulosTable;

    private JTable listaCategoria;
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
    private JTextField Descripcion_SubCategoria;
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
    private JPanel PanelArticulo;
    private JScrollPane ListadoSubCategorias;
    private DefaultTableModel tableCategorias;
    private JTable jTableArticulos;


    private JButton editCategory;

    //SUB- CATEGORY
    private JPanel PanelSubCategoria;
    private JTextField categorySubCategory;
    private JTextField IDSubCategoria;
    private JTextField NameSubCategoria;
    private JLabel codigoLabel_Sub;
    private JLabel nombreLabel_Sub;
    private JLabel descrpcionLabel_SubCategoria;
    private JTable subCategoriasTable;
    private JButton limpiarSubcategoriaBtn;
    private JButton eliminarsubcategoriaBtn;
    private JButton GuardarSubcategoriaBtn;
    private JButton buscarSubcategoriaBtn;
    private JTextField buscarCategoria;
    private JTextField descripcionCategoria;
    private JButton editarButtonSubCat;
    private JPanel codigoArticuloLa;
    private JLabel nombreArticuloLa;
    private JLabel descrpcionArticuloLabel;
    private JLabel cantidadArticuloLa;
    private JTextField cantidadItems;
    private JButton agregarPresentacionButton;
    private JTextField unidadArt;
    private JButton editarButton;
    private JButton NextButtonSub;
    private JTextArea textAcercaDe;
    private JLabel marcaLabel;
    private JTextField marcaArticuloTf;
    private JButton regresarCategoriaButton;
    private JButton eliminarPresentacionButton;
    private JButton CategoriaSubButton;
    private JLabel buscarCategori;
    private JComboBox unidadCombo;
    private JComboBox marcaCombo;
    private JButton regresarSubCategoria;
    private JPanel Pedidos;
    private JTextField buscarArticulo;
    private JButton buscarButton;
    private JScrollPane articulosVender;
    private JTextField subtotal;
    private JTextField descuento;
    private JTextField Total;
    private JLabel codigoArticuloLabel;
    private JTextField nombreCategoriaArticuloTf;
    private JTextField nombresubCategoriaArticuloTf;
    private JTextField precioArticuloTf;
    private JLabel precioLb;
    private JLabel buscarArticuloLb;
    private JTable tableArticulosVender;
    private JButton volverCategoriaBtn;
    private JComboBox subCategoriaPedido;
    private JComboBox categoriaPedido;
    private JButton agregarButtonArticuloBtn;
    private JTable tableArticulosFinal;
    private JPanel panelPrincipalLogin;

    public GUI(){
        initComponets();
    }

    public void setController(Controller control) {

        this.controller = control;
    }
    public void setArticulosTable(DefaultTableModel ta){
        jTableArticulos.setModel(ta);
    }

    public Controller getController() {
        return controller;
    }


    public void setTableCategoria(DefaultTableModel tableCategorias) {
        this.listaCategoria.setModel(tableCategorias);
    }

    public void setTableSubCategorias(DefaultTableModel tableSubCategorias) {
        this.subCategoriasTable.setModel(tableSubCategorias);
    }

    public  void setPresentacionesTable(DefaultTableModel presentacionesTable) {
        this.presentacionesTable.setModel(presentacionesTable);
    }

    public JTable getTableArticulosVender() {
        return tableArticulosVender;
    }

    public void setTableArticulosVender(DefaultTableModel tableArticulosVender) {
        this.tableArticulosVender.setModel(tableArticulosVender);
    }

    public JTable getTableArticulosFinal(){
        return tableArticulosFinal;
    }

    public void setArticulosVenderFinal(DefaultTableModel tableArticulosFinal) {
        this.tableArticulosFinal.setModel(tableArticulosFinal);
    }

    public String getIDSubCategoria() {
        return IDSubCategoria.getText();
    }

    public String getCategoriaIdCb(){
        return (String) categoriaPedido.getSelectedItem();
    }

    public String getSubCategoriaIdCb(){
        return (String) subCategoriaPedido.getSelectedItem();
    }
    //END TABLES VIEWS//

    //COMPONENTES//
    public void initComponets(){

        final String[] codigoCategoria = new String[1];
        this.setContentPane(panelPrincipal);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        PestaniasPanel.setEnabledAt(1,false);
        PestaniasPanel.setEnabledAt(2,false);
        textAcercaDe.setEditable(false);
        Acercade();

        NEXTTTButton.setEnabled(false);
        NextButtonSub.setEnabled(false);
        agregarPresentacionButton.setEnabled(false);
        eliminarPresentacionButton.setEnabled(false);

        editCategory.setEnabled(false);
        eliminarButton.setEnabled(false);
        guardarButton.setEnabled(true);

        editarButtonSubCat.setEnabled(false);
        GuardarSubcategoriaBtn.setEnabled(true);
        eliminarButton.setEnabled(false);
        limpiarCategoriaButton.setEnabled(false);
        searchButton.setEnabled(false);
        buscarSubcategoriaBtn.setEnabled(false);
        limpiarSubcategoriaBtn.setEnabled(false);
        eliminarsubcategoriaBtn.setEnabled(false);

        eliminarArticulosBtn.setEnabled(false);
        buscarArticuloBtn.setEnabled(false);
        limpiarArticulosBtn.setEnabled(false);
        editarButton.setEnabled(false);
        //buscarButton.setEnabled(false);

        nombresubCategoriaArticuloTf.setEditable(false);
        agregarButtonArticuloBtn.setEnabled(false);


        //Categporia
        this.guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(validateForm()){
                        if(  GUI.this.controller.agregarCategoria(codigo.getText(),nombre.getText(),descripcionCategoria.getText())){
                            clearText();
                            JOptionPane.showMessageDialog(null, "Categoria guardada con exito");
                        }
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        this.limpiarCategoriaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearText();
                controller.TableCategorias();
                JOptionPane.showMessageDialog(null,"La ventana de categoria se a limpiado correctamente");
            }
        });

        this.eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listaCategoria.getSelectedRow() != -1) {
                    String categoriaId = (String) listaCategoria.getValueAt(listaCategoria.getSelectedRow(), 0);

                    try {
                        if (controller.deleteCategory(categoriaId)) {
                            clearText();
                            controller.TableCategorias();
                            listaCategoria.clearSelection();
                            JOptionPane.showMessageDialog(null, "Categoría eliminada ");
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo eliminar la categoría.");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error al eliminar la categoría: " + ex.getMessage());
                    }
                }
            }
        });

        this.listaCategoria.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int filaSeleccionada = listaCategoria.getSelectedRow();
                if (filaSeleccionada != -1) {
                    String codCategoria = listaCategoria.getValueAt(filaSeleccionada, 0).toString();
                    String nombreCategoria = listaCategoria.getValueAt(filaSeleccionada, 1).toString();
                    String descripcion = listaCategoria.getValueAt(filaSeleccionada, 2).toString();

                    codigo.setText(codCategoria);
                    nombre.setText(nombreCategoria);
                    descripcionCategoria.setText(descripcion);

                    codigo.setEditable(false);
                    NEXTTTButton.setEnabled(true);
                    editCategory.setEnabled(true);
                    eliminarButton.setEnabled(true);
                    guardarButton.setEnabled(false);
                } else {
                    editCategory.setEnabled(false);
                    guardarButton.setEnabled(true);
                }
            }
        });
        this.editCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (listaCategoria.getSelectedRow() != -1) {
                        if (controller.editCategory(codigo.getText(), nombre.getText(), descripcionCategoria.getText())) {
                            clearText();
                        } else {
                            JOptionPane.showMessageDialog(null, "Selecione una categoria    ");
                        }
                    }
                }catch(Exception ex){
                    throw new RuntimeException(ex);
                }
            }

        });
        this.searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(buscarCategoria.getText().isEmpty()){
                        buscarCategori.setBorder( BorderFactory.createLineBorder(Color.RED, 2));

                    }else{
                        buscarCategori.setBorder(null);
                        controller.searchCategory(buscarCategoria.getText());
                    }

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        this.NEXTTTButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = listaCategoria.getSelectedRow();
                if (filaSeleccionada != -1) {

                    codigoCategoria[0] = listaCategoria.getValueAt(filaSeleccionada, 0).toString();
                    PestaniasPanel.setEnabledAt(1, true);
                    PestaniasPanel.setSelectedIndex(1);
                    PestaniasPanel.setEnabledAt(0, false);
                    String nombreCategoria = listaCategoria.getValueAt(filaSeleccionada, 1).toString();

                    categorySubCategory.setText(codigoCategoria[0] + "-" + nombreCategoria);
                    categorySubCategory.setEditable(false);
                    categoriaArticuloTf.setText(codigoCategoria[0]);
                    nombreCategoriaArticuloTf.setText(nombreCategoria);
                    categoriaArticuloTf.setEditable(false);
                    nombreCategoriaArticuloTf.setEditable(false);
                    controller.TableSubCategories();
                }
            }
        });

        this.NextButtonSub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = listaCategoria.getSelectedRow();
                int filaSeleccionadaSub = subCategoriasTable.getSelectedRow();
                if (filaSeleccionada != -1 && filaSeleccionadaSub != -1) {
                    String codigoCategoria = listaCategoria.getValueAt(filaSeleccionada, 0).toString();
                    PestaniasPanel.setEnabledAt(2, true);
                    PestaniasPanel.setSelectedIndex(2);
                    PestaniasPanel.setEnabledAt(0, false);
                    PestaniasPanel.setEnabledAt(1, false);
                    PestaniasPanel.setEnabledAt(3,true);
                    controller.TableSubCategories();
                    controller.TableItems();
                }
            }
        });

        this.regresarSubCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    PestaniasPanel.setEnabledAt(0, false);
                    PestaniasPanel.setEnabledAt(1, true);
                    PestaniasPanel.setSelectedIndex(1);
                    PestaniasPanel.setEnabledAt(2, false);
            }
        });

        // SUB-CATEGORY
        this.limpiarSubcategoriaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTextSubCategoria();
                controller.TableSubCategories();
                JOptionPane.showMessageDialog(null, "La ventana de subcategoria se a limpiado correctamente");
            }
        });
        this.GuardarSubcategoriaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                  if(validateSubCategoria()){

                      if(controller.GuardarSubCategoria(codigo.getText(),IDSubCategoria.getText(),NameSubCategoria.getText(),Descripcion_SubCategoria.getText())){
                          clearTextSubCategoria();
                          JOptionPane.showMessageDialog(null, "Subcategoria guardada con exito");
                      }

                  }
            }
        });
        this.eliminarsubcategoriaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String categoriaId = (String) listaCategoria.getValueAt(listaCategoria.getSelectedRow(), 0);
                String subCategoriaId = getIDSubCategoria();
                try {
                    if (controller.eliminarSubcategoria(categoriaId, subCategoriaId)) {
                        clearTextSubCategoria();
                        controller.TableSubCategories();// Limpiar campos si es necesario
                        JOptionPane.showMessageDialog(null, "Subcategoría eliminada con éxito");
                        eliminarsubcategoriaBtn.setEnabled(false);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar la subcategoría: " + ex.getMessage());
                }
            }
        });
        this.subCategoriasTable.getSelectionModel().addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting()){
                if (subCategoriasTable.getSelectedRow() != -1){
                    editarButtonSubCat.setEnabled(true);
                    GuardarSubcategoriaBtn.setEnabled(false);
                    eliminarsubcategoriaBtn.setEnabled(true);
                } else {
                    editarButtonSubCat.setEnabled(false);
                    GuardarSubcategoriaBtn.setEnabled(true);
                }
            }
        });

        this.subCategoriasTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = subCategoriasTable.getSelectedRow();
                if (filaSeleccionada != -1) {
                    String codigoSubCategoria = subCategoriasTable.getValueAt(filaSeleccionada, 0).toString();
                    String nombreSubCategoria = subCategoriasTable.getValueAt(filaSeleccionada, 1).toString();
                    String descripcionSub = subCategoriasTable.getValueAt(filaSeleccionada, 2).toString();
                    IDSubCategoria.setEditable(false);
                    IDSubCategoria.setText(codigoSubCategoria);
                    NameSubCategoria.setText(nombreSubCategoria);
                    Descripcion_SubCategoria.setText(descripcionSub);
                    subCategoriaArticuloTf.setText(codigoSubCategoria);
                    subCategoriaArticuloTf.setEditable(false);
                    nombresubCategoriaArticuloTf.setText(nombreSubCategoria);
                    nombreCategoriaArticuloTf.setEditable(false);
                    NextButtonSub.setEnabled(true);
                    editarButtonSubCat.setEnabled(true);
                    GuardarSubcategoriaBtn.setEnabled(false);
                }
            }
        });
        buscarSubcategoriaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            if(!buscarSubCategoria.getText().isEmpty()){
                try {
                    controller.searchSubCategory(buscarSubCategoria.getText());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }else{
            }
                JOptionPane.showMessageDialog(null, "Por favor, ingrese ID o nombre.");
            }
        });
        this.editarButtonSubCat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(controller.editSubCategory(codigo.getText(),IDSubCategoria.getText(),NameSubCategoria.getText(),Descripcion_SubCategoria.getText())){
                    clearTextSubCategoria();
                }
            }
        });
        this.CategoriaSubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cambiar a la pestaña de Categoría
                PestaniasPanel.setSelectedIndex(0);
                PestaniasPanel.setEnabledAt(1, false);
                clearTextSubCategoria();
            }
        });

        //ARTICULO

        this.limpiarArticulosBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTextArticulo();
                controller.TableItems();
                JOptionPane.showMessageDialog(null, "La ventana de Articulos se a limpiado correctamente");
            }
        });
        this.guardarArticulosBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validateArticulo()){
                    try{
                        if(controller.saveItems(codigo.getText(),IDSubCategoria.getText(), codigoArticuloTf.getText(),(String)marcaCombo.getSelectedItem(),
                                nombreArticuloTf.getText(),descripcionArticuloTf.getText(),(String)unidadCombo.getSelectedItem(),cantidadItems.getText(), precioArticuloTf.getText())){
                               clearTextArticulo();
                               JOptionPane.showMessageDialog(null, "Articulo guardada con exito");
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }


                }
            }
        });
        this.editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int seleccion = presentacionesTable.getSelectedRow();
                if (seleccion != -1) {
                    editarButton.setEnabled(true);
                controller.editarItems(categoriaArticuloTf.getText(), subCategoriaArticuloTf.getText(), codigoArticuloTf.getText(), nombreArticuloTf.getText(),
                        (String)marcaCombo.getSelectedItem(),descripcionArticuloTf.getText(), cantidadItems.getText(), (String) unidadCombo.getSelectedItem(), precioArticuloTf.getText());
                   controller.TableItems();
                   controller.TablePresentacion();
                   clearTextArticulo();
                }
            }
        });
        buscarArticuloBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!buscarIdArticuloTf.getText().isEmpty()){
                    try {
                        controller.searchArticulo(buscarIdArticuloTf.getText());
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        this.jTableArticulos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int seleccion = jTableArticulos.getSelectedRow();
                if (seleccion != -1) {
                    agregarPresentacionButton.setEnabled(true);

                    String codigoArticulo = jTableArticulos.getValueAt(seleccion, 0).toString(); // Código del artículo
                    String marca = jTableArticulos.getValueAt(seleccion, 1).toString(); // Descripción
                    String nombreArticulo = jTableArticulos.getValueAt(seleccion, 2).toString();
                    String descripcion = jTableArticulos.getValueAt(seleccion, 3).toString(); // Marca
                    codigoArticuloTf.setText(codigoArticulo);
                    descripcionArticuloTf.setText(descripcion);
                    nombreArticuloTf.setText(nombreArticulo);
                    marcaCombo.setSelectedItem(marca);
                    controller.TablePresentacion();
                    codigoArticuloTf.setEditable(false);
                    eliminarArticulosBtn.setEnabled(true);
                    guardarArticulosBtn.setEnabled(false);
                }
            }
        });
        this.eliminarArticulosBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int seleccion = jTableArticulos.getSelectedRow();

                if (seleccion != -1) {

                    try {
                        boolean eliminado = controller.eliminarArticulo();

                        if (eliminado) {
                            JOptionPane.showMessageDialog(null, "Artículo eliminado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            clearTextArticulo();
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontró el artículo para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (RuntimeException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un artículo para eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });


        //PRESENTACIONES
        this.agregarPresentacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( cantidadItems.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Ingrese los datos de articulo");
                }else{
                    if(controller.agregarPresentaciones((String)unidadCombo.getSelectedItem(),cantidadItems.getText(), precioArticuloTf.getText())){
                        JOptionPane.showMessageDialog(null, "Presentacion agregado con exito");
                        clearTextArticulo();
                        controller.TablePresentacion();
                    }
                }
            }
        });
        this.eliminarPresentacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int seleccion = presentacionesTable.getSelectedRow();
                if (seleccion != -1) {
                     if(controller.deletePresentacion((String)unidadCombo.getSelectedItem(),cantidadItems.getText(), precioArticuloTf.getText())){
                         JOptionPane.showMessageDialog(null, "Presentacion eliminado con exito");
                         controller.TablePresentacion();
                         cantidadItems.setText("");
                         eliminarPresentacionButton.setEnabled(false);
                     }
                 }
            }
        });

        this.presentacionesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = presentacionesTable.getSelectedRow();
                if(filaSeleccionada != -1){
                    eliminarPresentacionButton.setEnabled(true);
                    String unidad = presentacionesTable.getValueAt(filaSeleccionada, 0).toString();
                    String cantidad = presentacionesTable.getValueAt(filaSeleccionada, 1).toString();;
                    String precio = presentacionesTable.getValueAt(filaSeleccionada, 2).toString();
                    unidadCombo.setSelectedItem(unidad);
                    cantidadItems.setText(cantidad);
                    precioArticuloTf.setText(precio);
                    agregarPresentacionButton.setEnabled(false);
                    editarButton.setEnabled(true);
                }
            }
        });
        categoriaPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String categoriaSeleccionada = (String) categoriaPedido.getSelectedItem();

                try {
                    if (categoriaSeleccionada != null) {
                        String[] partes = categoriaSeleccionada.split("-");
                        String categoriaId = partes[0];
                        controller.llenarSubCategoria(categoriaId);
                    }

                }catch(Exception ex){
                    new Exception();
                }
            }
        });

        //BONOTES REGRESAR O NEXT ARTCICULO
        this.regresarCategoriaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PestaniasPanel.setEnabledAt(0, true);
                PestaniasPanel.setSelectedIndex(0);
                PestaniasPanel.setEnabledAt(1, false);
                PestaniasPanel.setEnabledAt(2, false);

            }
        });

        //Pedidos//
        this.buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                        controller.searchArticuloVenta();
                        //tableArticulosVender()
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        this.getTableArticulosVender().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                int columnaSeleccionada = tableArticulosVender.getSelectedColumn();
                int filaSeleccionada = tableArticulosVender.getSelectedRow();

                if (filaSeleccionada != -1) {
                    agregarButtonArticuloBtn.setEnabled(true);
                    //if (columnaSeleccionada == 7 || columnaSeleccionada == 8) {
                        try {
                            double cantidadUsuario = Double.parseDouble(tableArticulosVender.getValueAt(filaSeleccionada, 7).toString());
                            double precioUnitario = Double.parseDouble(tableArticulosVender.getValueAt(filaSeleccionada, 6).toString());
                            double cantidadDisponible = Double.parseDouble(tableArticulosVender.getValueAt(filaSeleccionada, 5).toString());

                            if (cantidadUsuario > cantidadDisponible) {
                                JOptionPane.showMessageDialog(null, "No hay suficiente stock disponible.", "Error", JOptionPane.ERROR_MESSAGE);
                                tableArticulosVender.setValueAt(0, filaSeleccionada, 7);
                                tableArticulosVender.setValueAt(0, filaSeleccionada, 8);
                            } else {
                                double total = cantidadUsuario * precioUnitario;
                                tableArticulosVender.setValueAt(total, filaSeleccionada, 8);
                            }
                        } catch (NumberFormatException ex) {
                            tableArticulosVender.setValueAt(0, filaSeleccionada, 0);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    //}
                }
            }
        });

        agregarButtonArticuloBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            controller.agregarArticuloTable();
            }
        });


        volverCategoriaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PestaniasPanel.setEnabledAt(0, true);
                PestaniasPanel.setSelectedIndex(0);
                PestaniasPanel.setEnabledAt(1, false);
                PestaniasPanel.setEnabledAt(2, false);
            }
        });


        this.nombre.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validar();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                validar();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                validar();
            }
        });
        this.codigo.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validar();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                validar();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                validar();
            }
        });
        this.descripcionCategoria.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validar();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                validar();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                validar();
            }
        });
        this.buscarCategoria.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validar();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                validar();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                validar();
            }
        });
        this.IDSubCategoria.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarBottenSubCategoria();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                validarBottenSubCategoria();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                validarBottenSubCategoria();
            }
        });
        this.NameSubCategoria.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarBottenSubCategoria();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                validarBottenSubCategoria();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                validarBottenSubCategoria();
            }
        });
        this.Descripcion_SubCategoria.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarBottenSubCategoria();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                validarBottenSubCategoria();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                validarBottenSubCategoria();
            }
        });
        this.buscarSubCategoria.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarBottenSubCategoria();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                validarBottenSubCategoria();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                validarBottenSubCategoria();
            }
        });
        this.nombreArticuloTf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarArticulo();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                validarArticulo();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                validarArticulo();
            }
        });
        this.codigoArticuloTf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarArticulo();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                validarArticulo();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                validarArticulo();
            }
        });
        this.buscarIdArticuloTf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarArticulo();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                validarArticulo();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                validarArticulo();
            }
        });
        this.descripcionArticuloTf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarArticulo();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                validarArticulo();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                validarArticulo();
            }
        });
        this.cantidadItems.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarArticulo();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                validarArticulo();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                validarArticulo();
            }
        });

        this.precioArticuloTf.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                validarArticulo();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validarArticulo();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarArticulo();
            }
        });

        /*
        this.buscarArticulo.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarBuscar();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                validarBuscar();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                validarBuscar();
            }
        });

         */



    }

    public  void validarBuscar(){
        if(buscarArticulo.getText().isEmpty()){
            buscarButton.setEnabled(false);
        }else{
            buscarButton.setEnabled(true);
        }
    }
    public void validar(){
        if(!(codigo.getText().isEmpty() && descripcionCategoria.getText().isEmpty() &&
                nombre.getText().isEmpty() && buscarCategoria.getText().isEmpty())){
            limpiarCategoriaButton.setEnabled(true);
        }else{
            limpiarCategoriaButton.setEnabled(false);
        }
        if(!(buscarCategoria.getText().isEmpty())){
            searchButton.setEnabled(true);
        }else{
            searchButton.setEnabled(false);
        }
    }

    public String getCategoryId(){ return codigo.getText(); }
    public String getArticuloId(){return codigoArticuloTf.getText(); }

    private void formWindowClosing(WindowEvent evt) {//GEN-FIRST:event_  formWindowClosing
        controller.exit();
    }
    private boolean validateForm() {

        Border errorBorder = BorderFactory.createLineBorder(Color.RED, 2);
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

    private boolean validateSubCategoria() {

        Border errorBorder = BorderFactory.createLineBorder(Color.RED, 2);
        boolean valid = true;
        if (IDSubCategoria.getText().isEmpty()) {
            valid = false;
            codigoLabel_Sub.setBorder(errorBorder);
            codigoLabel_Sub.setToolTipText("ID requerido");
        } else {
            codigoLabel_Sub.setBorder(null);
            codigoLabel_Sub.setToolTipText(null);
        }

        if (NameSubCategoria.getText().isEmpty()) {
            valid = false;
            nombreLabel_Sub.setBorder(errorBorder);
            nombreLabel_Sub.setToolTipText("Nombre requerido");
        } else {
            nombreLabel_Sub.setBorder(null);
            nombreLabel_Sub.setToolTipText(null);
        }

        if (Descripcion_SubCategoria.getText().isEmpty()) {
            valid = false;
            descrpcionLabel_SubCategoria.setBorder(errorBorder);
            descrpcionLabel_SubCategoria.setToolTipText("Descripcion requerido");
        }else {
            descrpcionLabel_SubCategoria.setBorder(null);
            descrpcionLabel_SubCategoria.setToolTipText(null);
        }

        return valid;
    }


    private void validarArticulo() {
        if(!(codigoArticuloTf.getText().isEmpty() &&
        nombreArticuloTf.getText().isEmpty()
        && descripcionArticuloTf.getText().isEmpty()
        && cantidadArticuloLa.getText().isEmpty() && buscarIdArticuloTf.getText().isEmpty() && precioArticuloTf.getText().isEmpty())){
           limpiarArticulosBtn.setEnabled(true);
        }else{
            limpiarArticulosBtn.setEnabled(false);
        }

        if(buscarIdArticuloTf.getText().isEmpty()){
            buscarArticuloBtn.setEnabled(false);
        }else{
            buscarArticuloBtn.setEnabled(true);
        }
    }

    private boolean validateArticulo() {

        Border errorBorder = BorderFactory.createLineBorder(Color.RED, 2);
        boolean valid = true;
        if (codigoArticuloTf.getText().isEmpty()) {
            valid = false;
            codigoArticuloLabel.setBorder(errorBorder);
        } else {
            codigoArticuloLabel.setBorder(null);
            codigoArticuloLabel.setToolTipText(null);
        }

        if (nombreArticuloTf.getText().isEmpty()) {
            valid = false;
            nombreArticuloLa.setBorder(errorBorder);
        } else {
            nombreArticuloLa.setBorder(null);
        }

        if (descripcionArticuloTf.getText().isEmpty()) {
            valid = false;
            descrpcionArticuloLabel.setBorder(errorBorder);
        }else {
            descrpcionArticuloLabel.setBorder(null);
        }
        if(cantidadItems.getText().isEmpty()){
            valid = false;
            cantidadArticuloLa.setBorder(errorBorder);
        }else{
            cantidadArticuloLa.setBorder(null);
        }
        if(precioArticuloTf.getText().isEmpty()){
            valid = false;
            precioLb.setBorder(errorBorder);
        }else{
            precioLb.setBorder(null);
        }


        return valid;
    }
    private void validarPedido(){
        if(!buscarArticulo.getText().isEmpty()){
            buscarButton.setEnabled(true);
        }else{
            buscarButton.setEnabled(false);
        }
    }

    /*
    private boolean validarPedidoForm(){
        Border errorBorder = BorderFactory.createLineBorder(Color.RED, 2);
        boolean valid = true;
        if (buscarArticulo.getText().isEmpty()) {
            valid = false;
            buscarArticuloLb.setBorder(errorBorder);
        } else {
            buscarArticuloLb.setBorder(null);
            buscarArticuloLb.setToolTipText(null);
        }
        return valid;
    }

     */

    void  clearText(){
        nombre.setText("");
        nombreLabel.setBorder(null);
        codigo.setText("");
        codigoLabel.setBorder(null);
        descripcionCategoria.setText("");
        descripcionLabel.setBorder(null);
        buscarCategoria.setText("");
        listaCategoria.clearSelection();
        codigo.setEditable(true);
        NEXTTTButton.setEnabled(false);
        eliminarButton.setEnabled(false);
        buscarCategori.setBorder(null);

    }
    void  clearTextSubCategoria(){
        IDSubCategoria.setText("");
        codigoLabel_Sub.setBorder(null);
        NameSubCategoria.setText("");
        nombreLabel_Sub.setBorder(null);
        Descripcion_SubCategoria.setText("");
        descrpcionLabel_SubCategoria.setBorder(null);
        buscarSubCategoria.setText("");
        subCategoriasTable.clearSelection();
        IDSubCategoria.setEditable(true);
       eliminarsubcategoriaBtn.setEnabled(false);
    }
    void clearTextArticulo(){
        codigoArticuloTf.setText("");
        nombreArticuloTf.setText("");
        descripcionArticuloTf.setText("");
        buscarIdArticuloTf.setText("");
        jTableArticulos.clearSelection();
        cantidadItems.setText("");
        precioArticuloTf.setText("");
        guardarArticulosBtn.setEnabled(true);
        codigoArticuloTf.setEditable(true);
        presentacionesTable.clearSelection();
        agregarPresentacionButton.setEnabled(false);
        limpiarArticulosBtn.setEnabled(false);
        editarButton.setEnabled(false);
        eliminarArticulosBtn.setEnabled(false);
        controller.TablePresentacion();
        eliminarPresentacionButton.setEnabled(false);

    }
    private void validarBottenSubCategoria(){
        if(!(IDSubCategoria.getText().isEmpty() && NameSubCategoria.getText().isEmpty()
        && Descripcion_SubCategoria.getText().isEmpty() && buscarSubCategoria.getText().isEmpty())){
            limpiarSubcategoriaBtn.setEnabled(true);
        }else{
        limpiarSubcategoriaBtn.setEnabled(false);
     }
        if(buscarSubCategoria.getText().isEmpty()){
            buscarSubcategoriaBtn.setEnabled(false);
        }else{
            buscarSubcategoriaBtn.setEnabled(true);
        }
    }
    private void Acercade() {
        String texto = "Esta Aplicación Fue Creada Por Grupo F.\n\n"
                + "Integrantes:\n"
                + "- Cristi Lazuna Valdivia\n"
                + "- Ivan Espinoza Mora\n"
                + "- Sharon Cerrato Amador\n"
                + "- Fernando Santamaría Leiva\n"
                + "- Jurguen Herrera Alfaro\n\n"
                + "Especificaciones:\n\n"
                + "Categoría:\n"
                + "- Todo es editable menos el código.\n"
                + "- Para pasar a la siguiente pestaña es necesario haber seleccionado una categoría de la lista y darle al botón 'Next'.\n\n"
                + "SubCategoría:\n"
                + "- Todo es editable menos el código.\n"
                + "- Para pasar de pestaña es necesario haber seleccionado una subcategoría de la lista y darle al botón 'Next'.\n\n"
                + "Artículo:\n"
                + "- Se puede editar todo menos el nombre de la presentación (Unidad) y el código del artículo.\n"
                + "- Para agregar un artículo es necesario incluir una presentación.\n"
                + "- Para editar el artículo es necesario que escojan el artículo y la presentación con un clic en su respectiva tabla.";

        textAcercaDe.setText(texto);
    }
    public void setTableArticulos(DefaultTableModel tableModel) {
        jTableArticulos.setModel(tableModel);
    }

    public JComboBox comboCategoria(){
        return  categoriaPedido;
    }
    public  JComboBox comboSubCategoria(){
        return  subCategoriaPedido;
    }


}
