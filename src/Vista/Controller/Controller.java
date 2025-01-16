package Vista.Controller;

import Logic.*;
import Vista.GUI;
import Vista.Model.Model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class Controller {

    private static Model model;
    private GUI gui;
    private static Service service;

    public Controller(Model model, GUI gui) {
        service = new Service();
        Controller.model = model; // Aquí creas el modelo internamente
        this.gui = gui; // Aquí creas la vista internamente

        // Cargar datos desde el archivo XML
        service.loadXml();
        model.setCategories(service.allCategories());

        // Solo si el XML está vacío, agregar datos quemados
        if (model.getCategories().isEmpty()) {
            prueba();
        }
        gui.setController(this);
        iniciar();
        TableCategorias();
        TableItems();
        TablePresentacion(gui.getArticuloId());
    }


    public void iniciar() {
        JFrame frame = new JFrame("Sistema de Inventarios");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Para cerrar la ventana correctamente
        frame.setContentPane(gui.getContentPane());
        frame.pack();  // Ajusta el tamaño al contenido
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

        try {
            GuardarSubCategoria("003", "PINT", "Pinturas Acrílicas", "Pinturas para interior y exterior");
            GuardarSubCategoria("003", "PINT_1", "Pinturas De agua", "Pinturas para interior y exterior");
            GuardarSubCategoria("001", "HERM", "Herramientas Manuales", "Martillos, destornilladores, llaves y más");
        } catch (Exception e) {
            throw new RuntimeException("Error inicializando datos quemados", e);
        }

        // Guardar los datos iniciales en el archivo XML
        service.saveXml();
    }


    public void exit(){ service.saveXml();}

    /// CATEGORIA
    public boolean agregarCategoria(String id, String nombre, String descripcion) {
        Category newCategory =  new Category(id,nombre,descripcion);
        try{
            service.addCategory(newCategory);
            model.setCategories(service.allCategories());
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
        this.gui.setTableCategoria(TableModel);
    }
    public void deleteCategory(int row){
        try{
            service.CategoryDelete(row);
            model.setCategories(service.allCategories());
            TableCategorias();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public boolean editCategory(String id,String name, String descripcion) {
        List<Category> categories = model.getCategories();
        try{
            for(int i = 0; i < categories.size(); i++){
                if(categories.get(i).getId().equals(id)){
                    service.CategoryEdit(id,name,descripcion);
                    model.setCategories(service.allCategories());
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
            TableModel.addRow(new Object[]{cat.getId(), cat.getName(), cat.getDescription()});

        }

        this.gui.setTableCategoria(TableModel);

    }
    public void searchCategory(String dat) throws Exception {
        searchCategoryTable(dat);
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
            this.gui.setTableSubCategorias(TableModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean GuardarSubCategoria(String idCategoria,String idSub, String nombre, String descripcion) {
        try{
            Category cat = service.categoryGetId(idCategoria);

            SubCategory newSubCategory = new SubCategory(idSub,nombre,descripcion);
            for (SubCategory subCat : cat.getSubCategoryList()) {
                if (subCat.getSubCategoryID().trim().equals(newSubCategory.getSubCategoryID().trim())) {
                    JOptionPane.showMessageDialog(null, "La Sub Categoria ya existente", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            service.addSubCategory(idCategoria,newSubCategory);
            TableSubCategories(idCategoria);
            service.saveXml();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public boolean agregarPresentaciones(String un, String pre){
        double numeroComoDouble = Double.parseDouble(pre);
        service.agregarPresentation(gui.getCategoryId(),gui.getIDSubCategoria(),gui.getArticuloId()
        ,new Presentation(un,numeroComoDouble));
        return true;
    }

    public boolean eliminarSubcategoria(String categoriaId, String subCategoriaId) {
        try {
            // Llamar al servicio para eliminar la subcategoría usando los IDs
            service.EliminateSubcategory(categoriaId, subCategoriaId);

            // Actualizar el modelo de categorías
            model.setCategories(service.allCategories());

            // Refrescar la vista de categorías
            TableCategorias();
            service.saveXml();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar subcategoría: " + e.getMessage());
            return false;
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
    }

    //Articulos

    public boolean saveItems(String idC, String sub, String cod, String marca,String nombre, String descripcion, String Prese, String e) throws Exception {
        double num = Double.parseDouble(e);
        Items item = new Items(cod,marca,nombre,descripcion);
        Presentation presentation = new Presentation(Prese,num);
        service.guardarArticulo(idC, sub,item, presentation);
        TableItems();
        service.saveXml();
        return true;

    }

    public void TableItems() {

        try{

            List<Items> itemsList = service.allItems(gui.getCategoryId(),gui.getIDSubCategoria());
            DefaultTableModel TableModel = new DefaultTableModel(new String[]{"ID", "Marca", "Nombre", "Descripcion"}, itemsList.size()) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
                for (int j = 0; j < itemsList.size(); j++) {
                    Items item = itemsList.get(j);
                    TableModel.setValueAt(item.getId(), j, 0);
                    TableModel.setValueAt(item.getBrand(),j,1);
                    TableModel.setValueAt(item.getName(),j,2);
                    TableModel.setValueAt(item.getDescription(),j,3);
                }
            this.gui.setArticulosTable(TableModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void TablePresentacion(String dat){

        try{
            List<Presentation> presentations = service.allPresentation(gui.getCategoryId(), gui.getIDSubCategoria(), dat);
            DefaultTableModel TableModel = new DefaultTableModel(new String[]{"Unidad", "Cantidad"}, presentations.size()) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            for (int j = 0; j < presentations.size(); j++) {
                Presentation presentation = presentations.get(j);
                TableModel.setValueAt(presentation.getMeasure(), j, 0);
                TableModel.setValueAt(presentation.getQuantity(),j,1);
            }
            this.gui.setPresentacionesTable(TableModel);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
