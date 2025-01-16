package Vista;

import Logic.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class Controller {

    private static Model model;
    private GUI gui;
    private static Service service;
    private static JTable categoriesTable;
    private static final JTable SubcategoryTable = new JTable();
    private  JTable categoryTable;

    public Controller(Model model, GUI gui) {
        service = new Service();
        categoryTable = new JTable();
        Controller.model = model;  // Aquí creas el modelo internamente
        this.gui = gui;    // Aquí creas la vista internamente
        model.setCategories(service.allCategories());
        gui.setController(this);
        gui.setModel(Controller.model);
        iniciar();
        prueba();
        TableCategorias();
        TableSubCategories(gui.getCategoryId());
        TableItems(gui.getCategoryId());
      //  show();


    }


    public void show() {
        model.setTableCategories(categoriesTable);
        model.setTableSubCategories(SubcategoryTable);
        model.commit();
    }


    public void iniciar() {
        JFrame frame = new JFrame("Sistema de Inventarios");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Para cerrar la ventana correctamente
        frame.setContentPane(gui.getContentPane());
        frame.pack(); // Ajusta el tamaño según el contenido
        frame.setVisible(true); // Muestra la ventana
    }

    //Categoria

    public void  prueba (){
        Category herramientas = new Category("001", "Herramientas", "Todo tipo de herramientas manuales y eléctricas.");
        Category materialesConstruccion = new Category("002", "Materiales de Construcción", "Cemento, ladrillos, yeso, etc.");
        Category pintura = new Category("003", "Pinturas", "Pinturas, barnices y materiales para decoración.");
        model.getCategories().add(herramientas);
        model.getCategories().add(materialesConstruccion);
        model.getCategories().add(pintura);

        GuardarSubCategoria("003","PINT","Pinturas Acrílicas", "Pinturas para interior y exterior");
        GuardarSubCategoria( "001","HERM","Herramientas Manuales", "Martillos, destornilladores, llaves y más");

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
    public void TableCategorias()  {

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
      //  categoriesTable.setModel(TableModel);
       // model.setTableCategories(categoriesTable);
        this.gui.setTableCategoria(TableModel);
    }
    public void deleteCategory(int row){
        model.getCategories().remove(row);
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
    public void searchCategoryTable(String dat) throws Exception {

        DefaultTableModel TableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Descripcion"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {return false; // Hacer que las celdas no sean editable
            }
        };

        Category cat = service.categoryGetId(dat);

        if (cat == null) {
            TableCategorias();
        } else {
            /*
            TableModel.setValueAt(cat.getId(), 0, 0);
            TableModel.setValueAt(cat.getName(),1,1);
            TableModel.setValueAt(cat.getDescription(),2,2);
             */
            TableModel.addRow(new Object[]{cat.getId(), cat.getName(), cat.getDescription()});

        }

        this.gui.setTableCategoria(TableModel);

    }
    public void searchCategory(String dat) throws Exception {
        searchCategoryTable(dat);
        show();
    }

    //SubCategoria

    public void TableSubCategories(String dat) {

        try{
            List<SubCategory> subCategories = service.categoryGetId(dat).getSubCategoryList();

            DefaultTableModel TableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Descripcion"}, subCategories.size()) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            for (int i = 0; i < subCategories.size(); i++) {

                SubCategory subCategory = subCategories.get(i);
                TableModel.setValueAt(subCategory.getSubCategoryID(), i, 0);
                TableModel.setValueAt(subCategory.getSubCategoryName(), i, 1);
                TableModel.setValueAt(subCategory.getSubCategoryDescription(), i, 2);
            }
           // SubcategoryTable.setModel(TableModel);
            this.gui.setTableSubCategorias(TableModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


    public boolean GuardarSubCategoria(String idCategoria,String idSub, String nombre, String descripcion) {
        try{
            SubCategory newSubCategory = new SubCategory(idSub,nombre,descripcion);
            service.addSubCategory(idCategoria,newSubCategory);
            TableSubCategories(idCategoria);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public boolean eliminarSubCatgoeria(String idCategory, String idSub) {
        try {
          service.EliminateSubcategory(idCategory,idSub);
          TableSubCategories(idCategory);
          return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean editSubCategory(String idCat, String idSub, String nombre, String descripcion) {
        try {
            service.setEditSubCategory(idCat,idSub,nombre,descripcion);
            TableSubCategories(idCat);
            return true;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void searchSubCategoryTable(String dat) throws Exception {

        DefaultTableModel TableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Descripcion"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {return false; // Hacer que las celdas no sean editable
            }
        };

        SubCategory subCategory = service.subCategoryGetId(dat);

        if (subCategory == null) {

        } else {
            TableModel.addRow(new Object[]{subCategory.getSubCategoryID(), subCategory.getSubCategoryName(), subCategory.getSubCategoryDescription()});

        }

        this.gui.setTableSubCategorias(TableModel);

    }
    public void searchSubCategory(String dat) throws Exception {
        searchSubCategoryTable(dat);
        show();
    }


    //Articulos

    public boolean saveItems(String idC, String sub, String cod, String nombre, String descripcion, String Prese, String e) throws Exception {
        double num = Double.parseDouble(e);
        Items item = new Items(cod,nombre,descripcion);
        Presentation presentation = new Presentation(Prese,num);
        service.guardarArticulo(idC, sub,item, presentation);
        return true;

    }


    public void TableItems(String dat) {

        try{
            List<SubCategory> subCategories = service.categoryGetId(dat).getSubCategoryList();

            DefaultTableModel TableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Descripcion"}, subCategories.size()) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            for (int i = 0; i < subCategories.size(); i++) {
                List<Items> items  = subCategories.get(i).getItems();
                for (int j = 0; j < items.size(); j++) {
                    Items item = items.get(j);
                    TableModel.setValueAt(item.getId(), j, 0);
                    TableModel.setValueAt(item.getName(),j,1);
                    TableModel.setValueAt(item.getDescription(),j,2);
                }
            }
            this.gui.setArticulosTable(TableModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }



}
