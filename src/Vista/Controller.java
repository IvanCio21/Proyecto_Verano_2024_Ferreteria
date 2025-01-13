package Vista;

import Logic.Category;
import Logic.Service;
import Logic.SubCategory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.EventObject;
import java.util.List;

public class Controller {

    private static Model model;
    private GUI gui;
    private static Service service;
    private static JTable categoriesTable;
    private static final JTable SubcategoryTable = new JTable();

    public Controller(Model model, GUI gui) {
        service = new Service();
        this.model = model;  // Aquí creas el modelo internamente
        this.gui = gui;    // Aquí creas la vista internamente
        model.setCategories(service.allCategories());
        gui.setController(this);
        gui.setModel(this.model);
        iniciar();
        prueba();
        TableCategorias();
        TableSubCategories();

    }


    public void iniciar() {
        JFrame frame = new JFrame("Sistema de Inventarios");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Para cerrar la ventana correctamente
        frame.setContentPane(gui.getContentPane());
        frame.pack(); // Ajusta el tamaño según el contenido
        frame.setVisible(true); // Muestra la ventana
    }

    public void  prueba (){
        Category herramientas = new Category("001", "Herramientas", "Todo tipo de herramientas manuales y eléctricas.");
        Category materialesConstruccion = new Category("002", "Materiales de Construcción", "Cemento, ladrillos, yeso, etc.");
        Category pintura = new Category("003", "Pinturas", "Pinturas, barnices y materiales para decoración.");
        model.getCategories().add(herramientas);
        model.getCategories().add(materialesConstruccion);
        model.getCategories().add(pintura);

        SubCategory aes = new SubCategory("23334"," 3edff","edf");
        herramientas.addSubCategory(aes);

    }

    public void exit(){ service.saveXml();}
    public boolean agregarCategoria(String id, String nombre, String descripcion) {
        Category newCategory =  new Category(id,nombre,descripcion);
        try{
            service.addCategory(newCategory);
            TableCategorias();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
    public  void  TableCategorias()  {

        DefaultTableModel TableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Descripcion"}, model.getCategories().size()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (int i = 0; i < model.getCategories().size(); i++) {
            Category category = model.getCategories().get(i);

            TableModel.setValueAt(category.getId(), i, 0);
            TableModel.setValueAt(category.getName(),i,1);
            TableModel.setValueAt(category.getDescription(),i,2);
        }
        this.gui.setTableCategoria(TableModel);
    }
    public void deleteCategory(int row){
        this.model.getCategories().remove(row);
        TableCategorias();
    }
    public boolean editCategory(String id,String name, String descripcion) {
        List<Category> categories = model.getCategories();
        try{
            for(int i = 0; i < categories.size(); i++){
                if(categories.get(i).getId().equals(id)){
                    service.CategoryEdit(id,name,descripcion);
                    TableCategorias();
                    return true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }
    public   void searchCategoryTable(String dat) throws Exception {
        DefaultTableModel TableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Descripcion"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {return false; // Hacer que las celdas no sean editable

                 }
        };

        Category cat = service.categoryGetId(dat);
        if (cat == null) {
            TableCategorias();
        } else {
            TableModel.setValueAt(cat.getId(), 0, 0);
            TableModel.setValueAt(cat.getName(),1,1);
            TableModel.setValueAt(cat.getDescription(),2,2);

        }

        this.gui.setTableCategoria(TableModel);

    }
    public void  searchCategory(String dat) throws Exception {
        searchCategoryTable(dat);
    }

    public void TableSubCategories(){
        DefaultTableModel TableModel = new DefaultTableModel(new String[]{ "ID", "Nombre", "Descripcion"}, service.allSubCategories().size()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (int i = 0; i < service.allSubCategories().size(); i++) {
            SubCategory subCategory = service.allSubCategories().get(i);
            TableModel.setValueAt(subCategory.getSubCategoryID(), i, 0);
            TableModel.setValueAt(subCategory.getSubCategoryName(),i,1);
            TableModel.setValueAt(subCategory.getSubCategoryDescription(),i,2);
        }

        this.gui.setTableSubCategorias(TableModel);

    }


    public boolean GuardarSubCategoria(String idCategoria,String idSub, String nombre, String descripcion) {
        for(Category cat: service.allCategories()){
            if(cat.getId().equals(idCategoria)){
                cat.addSubCategory(new SubCategory(idSub,nombre,descripcion));
                TableSubCategories();
                return true;
            }
        }
        return false;
    }


}
