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
        TableSubCategories();
        TableItems();
        TablePresentacion();
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
           for(Category category: model.getCategories()){
               if(category.getId().trim().equals(id)){
                   JOptionPane.showMessageDialog(null, "Categoria existente", "Error", JOptionPane.ERROR_MESSAGE);
                   return false;
               }
           }
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
            JOptionPane.showMessageDialog(null, "Categoria no puede ser eliminada porque contiene Subcategorias",
                    "Error", JOptionPane.ERROR_MESSAGE);
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

    public void TableSubCategories() {

        try{
            List<SubCategory> subCategories = service.categoryGetId(gui.getCategoryId()).getSubCategoryList();

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
            TableSubCategories();
            service.saveXml();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public boolean agregarPresentaciones(String un, String pre) {
        try {
            double numeroComoDouble = Double.parseDouble(pre); // Convertir cantidad a double

            // Llamar al servicio para agregar la presentación
            service.agregarPresentation(gui.getCategoryId(), gui.getIDSubCategoria(), gui.getArticuloId(),
                    new Presentation(un, numeroComoDouble));

            // Mostrar mensaje de éxito en la interfaz
            JOptionPane.showMessageDialog(null, "Presentación agregada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return true;

        } catch (RuntimeException e) {
            // Mostrar mensaje de error en la interfaz gráfica
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }


    public boolean eliminarSubcategoria(String categoriaId, String subCategoriaId) {
        try {
            service.EliminateSubcategory(categoriaId, subCategoriaId);
            model.setCategories(service.allCategories());
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
            TableSubCategories();
            JOptionPane.showMessageDialog(null, "Sub Categoria editada");
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

        SubCategory subCategory = service.subCategoryGetId(gui.getCategoryId(),dat);

        if (subCategory == null) {

        } else {
            TableModel.addRow(new Object[]{subCategory.getSubCategoryID(), subCategory.getSubCategoryName(), subCategory.getSubCategoryDescription()});

        }

        this.gui.setTableSubCategorias(TableModel);

    }
    public void searchSubCategory(String dat) throws Exception {
        try{
            searchSubCategoryTable(dat);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "SubCategoria no encontrada ");
        }

    }

    //Articulos

    public boolean saveItems(String idC, String sub, String cod, String marca, String nombre, String descripcion, String Prese, String e) {
        try {
            // Convertir la cantidad de la presentación a double
            double num = Double.parseDouble(e);

            // Crear el objeto Item y Presentation
            Items item = new Items(cod, marca, nombre, descripcion);
            Presentation presentation = new Presentation(Prese, num);

            // Intentar guardar el artículo
            service.guardarArticulo(idC, sub, item, presentation);
            TableItems(); // Actualizar la tabla de artículos
            service.saveXml(); // Guardar cambios en el XML
            return true;

        } catch (NumberFormatException ex) {
            // Mostrar error si la cantidad no es válida
            JOptionPane.showMessageDialog(null, "El valor de la cantidad debe ser un número válido: " + e, "Error", JOptionPane.ERROR_MESSAGE);
            return false;

        } catch (Exception ex) {
            // Mostrar cualquier otra excepción
            JOptionPane.showMessageDialog(null, "Error al guardar el artículo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
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

    public void TablePresentacion(){

        try{
            List<Presentation> presentations = service.allPresentation(gui.getCategoryId(), gui.getIDSubCategoria(), gui.getArticuloId());
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


    public boolean eliminarArticulo() {
        try {
            boolean eliminado = service.deleteItem(gui.getCategoryId(), gui.getIDSubCategoria(), gui.getArticuloId());

            if (eliminado) {
                TablePresentacion(); // Refrescar la tabla de presentaciones
                TableItems(); // Refrescar la tabla de artículos
                return true;
            } else {
                return false; // No se encontró el artículo
            }
        } catch (Exception e) {
            // Lanza la excepción para que la vista la maneje
            throw new RuntimeException("Error al eliminar el artículo: " + e.getMessage(), e);
        }
    }



}
