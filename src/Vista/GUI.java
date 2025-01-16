package Vista;

import Logic.Category;
import Logic.Service;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
//Este sera el observado: Escucha los cambios
//actualiza cuando algo del observable cambia

public class GUI extends JFrame implements Observer {
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
    private JLabel descrpcionLabel;
    private JLabel cantidadLa;
    private JTextField cantidadItems;
    private JButton agregarPresentacionButton;
    private JTextField unidadArt;
    private JButton editarButton;


    public GUI(){
        initComponets();
    }
    @Override
    public void update(Observable o, Object arg) {
        if (this.model.getTableCategories() != null) {
            this.listaCategoria.setModel(this.model.getTableCategories().getModel());
            this.listaCategoria.setColumnModel(this.model.getTableCategories().getColumnModel());
        }
        if (this.model.getTableSubCategories() != null) {
            this.subCategoriasTable.setModel(this.model.getTableSubCategories().getModel());
            this.subCategoriasTable.setColumnModel(this.model.getTableSubCategories().getColumnModel());
        }
      //  this.listadoSubCategorias.setModel(this.model.getTableSubCategories().getModel());
      //  this.listadoSubCategorias.setColumnModel(this.model.getTableSubCategories().getColumnModel());
    }

    public void setController(Controller control) {
        this.controller = control;
    }

    public Controller getController() {
        return controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }
    public Model getModel() {
        return model;
    }

    public void setTableCategoria(DefaultTableModel tableCategorias) {
        this.listaCategoria.setModel(tableCategorias);
    }

    public void setTableSubCategorias(DefaultTableModel tableSubCategorias) {
        this.subCategoriasTable.setModel(tableSubCategorias);
    }


    public DefaultTableModel getTableCategorias(){
        return tableCategorias;
    }

    public JTable getTableArticulos(){
        return jTableArticulos;
    }

    public JTable getSubCategoriasTable(){
        return subCategoriasTable;
    }

    public void setArticulosTable(DefaultTableModel ta){
         jTableArticulos.setModel(ta);
    }


    //END TABLES VIEWS//

    //COMPONENTES//
    public void initComponets(){
        this.setContentPane(panelPrincipal); // Seteo contenido del form al JFrame que se acaba de crear
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        //codigo.setEditable(true);

      //  subCategoriasTable = new JTable();
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
                codigo.setEditable(true);
                clearText();
            }
        });

        this.eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if (listaCategoria.getSelectedRow() != -1){
                        controller.deleteCategory(listaCategoria.getSelectedRow());
                    }else{
                        JOptionPane.showMessageDialog(null, "Selecione una categoria    ");
                    }

                } catch(Exception ex){
                    throw new RuntimeException(ex);
                }

            }
        });

        listaCategoria.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = listaCategoria.getSelectedRow();

                if (filaSeleccionada != -1) {
                    codigo.setEditable(false);
                    String codigoCategoria = listaCategoria.getValueAt(filaSeleccionada, 0).toString();
                    String nombreCategoria = listaCategoria.getValueAt(filaSeleccionada, 1).toString();
                    String descripcion = listaCategoria.getValueAt(filaSeleccionada, 2).toString();

                    codigo.setText(codigoCategoria);
                    nombre.setText(nombreCategoria);
                    descripcionCategoria.setText(descripcion);
                }
            }
        });

        this.editCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                  if(controller.editCategory(codigo.getText(),nombre.getText(),descripcionCategoria.getText())){
                      clearText();
                  };
                }catch(Exception ex){
                    throw new RuntimeException(ex);
                }
            }
        });

        this.searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.searchCategory(buscarCategoria.getText());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // SUB-CATEGORY

        this.listaCategoria.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = listaCategoria.getSelectedRow();
                if (filaSeleccionada != -1) {
                    String codigoCategoria = listaCategoria.getValueAt(filaSeleccionada, 0).toString();
                    String nombreCategoria = listaCategoria.getValueAt(filaSeleccionada, 1).toString();

                    categorySubCategory.setText(codigoCategoria+ "-" + nombreCategoria);
                    categorySubCategory.setEditable(false);
                    categoriaArticuloTf.setText(codigoCategoria+ "-" + nombreCategoria);
                    categoriaArticuloTf.setEditable(false);

                    controller.TableSubCategories(codigo.getText()); /// Cambiar esto
                }
            }
        });


        this.limpiarSubcategoriaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTextSubCategoria();
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

        assert this.eliminarsubcategoriaBtn != null;
        this.eliminarsubcategoriaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(controller.eliminarSubCatgoeria(codigo.getText(),IDSubCategoria.getText())){
                    clearTextSubCategoria();
                    JOptionPane.showMessageDialog(null, "Subcategoria eliminada con exito");
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
                    subCategoriaArticuloTf.setText(codigoSubCategoria+ "-" + nombreSubCategoria);
                    subCategoriaArticuloTf.setEditable(false);

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
            }
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

        //ARTICULO

        this.limpiarArticulosBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTextArticulo();
            }
        });

        this.guardarArticulosBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validateArticulo()){
                    try{
                        if(controller.saveItems(codigo.getText(),IDSubCategoria.getText(), codigoArticuloTf.getText(),
                                nombreArticuloTf.getText(),descripcionArticuloTf.getText(),unidadArt.getText(),cantidadItems.getText())){
                               clearTextArticulo();
                               JOptionPane.showMessageDialog(null, "Articulo guardada con exito");
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }


                }
            }
        });



        
    }

    public String getCategoryId(){ return codigo.getText(); }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_  formWindowClosing
        controller.exit();
    }
    private boolean validateForm() {

        javax.swing.border.Border errorBorder = BorderFactory.createLineBorder(Color.RED, 2);
        boolean valid = true;
        if (codigo.getText().isEmpty()) {
            valid = false;
            codigo.setBorder(errorBorder);
            codigoLabel.setToolTipText("ID requerido");
        } else {
            codigo.setBorder(null);
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

        javax.swing.border.Border errorBorder = BorderFactory.createLineBorder(Color.RED, 1);
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


    private boolean validateArticulo() {

        javax.swing.border.Border errorBorder = BorderFactory.createLineBorder(Color.RED, 2);
        boolean valid = true;
        if (codigoArticuloTf.getText().isEmpty()) {
            valid = false;
            codigoArticuloTf.setBorder(errorBorder);
           // codigoArticuloTf.setToolTipText("ID requerido");
        } else {
            codigoArticuloTf.setBorder(null);
            codigoArticuloTf.setToolTipText(null);
        }

        if (nombreArticuloTf.getText().isEmpty()) {
            valid = false;
            nombreArticuloTf.setBorder(errorBorder);
           // nombreLabel.setToolTipText("Nombre requerido");
        } else {
            nombreArticuloTf.setBorder(null);
         //   nombreLabel.setToolTipText(null);
        }

        if (descripcionArticuloTf.getText().isEmpty()) {
            valid = false;
            descripcionArticuloTf.setBorder(errorBorder);
          //  descripcionArticuloTf.setToolTipText("Descripcion requerido");
        }else {
            descripcionArticuloTf.setBorder(null);
            //descripcionLabel.setToolTipText(null);
        }

        return valid;
    }


    void  clearText(){
        nombre.setText("");
        codigo.setText("");
        descripcionCategoria.setText("");
        buscarCategoria.setText("");
        listaCategoria.clearSelection();
    }
    void  clearTextSubCategoria(){
        IDSubCategoria.setText("");
        NameSubCategoria.setText("");
        Descripcion_SubCategoria.setText("");
        buscarSubCategoria.setText("");
        subCategoriasTable.clearSelection();
    }

    void clearTextArticulo(){
        codigoArticuloTf.setText("");
        nombreArticuloTf.setText("");
        descripcionArticuloTf.setText("");
        buscarIdArticuloTf.setText("");
        jTableArticulos.clearSelection();
        unidadArt.setText("");
        cantidadItems.setText("");

    }
}
