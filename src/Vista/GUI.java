package Vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
//Este sera el observado: Escucha los cambios
//actualiza cuando algo del observable cambia

public class GUI extends JFrame implements Observer {
    //Control y model de la view
    private Control control;
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
    private JTable tableCategorias;
    private JTable jTableArticulos;
    private JTable subCategoriasTable;
    private JButton limpiarSubcategoriaBtn;
    private JButton eliminarsubcategoriaBtn;
    private JButton GuardarSubcategoriaBtn;
    private JButton buscarSubcategoriaBtn;


    public GUI(){
        initComponets();
    }

    public void setController(Control control) {
        this.control = control;
    }

    public Control getController() {
        return control;
    }

    public void setModel(Model model) {
        this.model = model;
    }
    public Model getModel() {
        return model;
    }


    //TABLES VIEWS
    @Override
    public void update(Observable o, Object arg) {
        tableCategorias.setModel(model.getTableArticulos().getModel());
        tableCategorias.setColumnModel(model.getTableArticulos().getColumnModel());
        jTableArticulos.setModel(model.getTableArticulos().getModel());
        jTableArticulos.setColumnModel(model.getTableArticulos().getColumnModel());
        subCategoriasTable.setModel(model.getTableArticulos().getModel());
        subCategoriasTable.setColumnModel(model.getTableArticulos().getColumnModel());
    }

    public JTable getTableCategorias(){
        return tableCategorias;
    }

    public JTable getTableArticulos(){
        return jTableArticulos;
    }

    public JTable getSubCategoriasTable(){
        return subCategoriasTable;
    }

    //END TABLES VIEWS//

    //COMPONENTES//
    public void initComponets(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LA MEJOR FERRETERIA");

        /*addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });*/

        this.setContentPane(panelPrincipal); // Seteo contenido del form al JFrame que se acaba de crear
        this.pack();

        //BOTONES ARTICULOS//
        limpiarArticulosBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

            }
        });

        buscarArticuloBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //BOTONES CATEGFORIA//

        limpiarCategoriaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
}
