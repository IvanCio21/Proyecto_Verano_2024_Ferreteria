package cr.ac.una.MVC.Controller;

import cr.ac.una.Data.*;
import cr.ac.una.MVC.*;
import cr.ac.una.Logic.*;
import cr.ac.una.MVC.Model.*;
import cr.ac.una.Protocol.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Controller {

    private static Model model;
    private GUI gui;
    private static Service service;
    private Login loginView;
    JFrame frameLogin;

    public Controller(Model model, GUI gui, Login loginView) {
        this.model = model;
        this.gui = gui;
        this.loginView = loginView;

        service = new Service();
        service.loadXml();
        model.setCategories(service.allCategories());


        loginView.setController(this);
        mostrarLogin();


    }


    public void mostrarLogin() {
        frameLogin = new JFrame("Login - Sistema de Inventarios");
        frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLogin.setContentPane(loginView.getPanelLoginBase());
        frameLogin.pack();
        frameLogin.setVisible(true);
    }

    public void login(User user) throws Exception {
        try {
            User loggedInUser = Proxy.instance().login(user);

            if (loggedInUser == null) {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            model.setCurrentUser(loggedInUser);
            model.commit(Model.USER);
            frameLogin.dispose();
//            loginView.getPanelLoginBase().setVisible(false);
            JOptionPane.showMessageDialog(null, "¡Login exitoso!");
            iniciarSistema();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar sesión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void iniciarSistema() {
        JFrame frame = new JFrame("Sistema de Inventarios");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(gui.getContentPane());
        frame.pack();
        frame.setVisible(true);
        model.setCategories(service.allCategories());

        if (model.getCategories().isEmpty()) {
            prueba();
        }
        gui.setController(this);
        TableCategorias();
        TableSubCategories();
        TableItems();
        TablePresentacion();
        TablePedidos();
        TableArticulosFinal();
        llenarCombos(model.getCategories());
    }

    //Categoria

    public void prueba() {
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


        service.saveXml();
    }


    public void exit() {
        service.saveXml();
    }

    /// CATEGORIA
    public boolean agregarCategoria(String id, String nombre, String descripcion) {
        Category newCategory = new Category(id, nombre, descripcion);
        try {
            for (Category category : model.getCategories()) {
                if (category.getId().trim().equals(id)) {
                    JOptionPane.showMessageDialog(null, "ID de categoria repetido", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                if (category.getName().trim().equals(nombre)) {
                    JOptionPane.showMessageDialog(null, "Nombre de categoria repetido", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            service.addCategory(newCategory);
            model.setCategories(service.allCategories());
            TableCategorias();
            llenarCombos(model.getCategories());
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public void TableCategorias() {

        DefaultTableModel TableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Descripcion"}, model.getCategories().size()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (int i = 0; i < model.getCategories().size(); i++) {
            Category category = model.getCategories().get(i);

            TableModel.setValueAt(category.getId(), i, 0);
            TableModel.setValueAt(category.getName(), i, 1);
            TableModel.setValueAt(category.getDescription(), i, 2);
        }
        this.gui.setTableCategoria(TableModel);
    }

    public boolean deleteCategory(String id) {
        try {
            service.CategoryDelete(id);
            model.setCategories(service.allCategories());
            service.saveXml();
            TableCategorias();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Categoria no puede ser eliminada porque contiene Subcategorias",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    public boolean editCategory(String id, String name, String descripcion) {
        List<Category> categories = model.getCategories();
        try {
            for (Category category : categories) {
                if (!category.getId().equals(id) && category.getName().equalsIgnoreCase(name)) {
                    JOptionPane.showMessageDialog(null, "El nombre de categoría ya existe. No se puede editar con un nombre duplicado.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }

            service.CategoryEdit(id, name, descripcion);
            model.setCategories(service.allCategories());
            TableCategorias();
            return true;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void searchCategoryTable(String dat) throws Exception {

        DefaultTableModel TableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Descripcion"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
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

        try {
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

    public boolean GuardarSubCategoria(String idCategoria, String idSub, String nombre, String descripcion) {
        try {
            Category cat = service.categoryGetId(idCategoria);

            SubCategory newSubCategory = new SubCategory(idSub, nombre, descripcion);
            for (SubCategory subCat : cat.getSubCategoryList()) {
                if (subCat.getSubCategoryID().trim().equals(newSubCategory.getSubCategoryID().trim())) {
                    JOptionPane.showMessageDialog(null, "ID de SubCateogira repetido", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                if (subCat.getSubCategoryName().trim().equals(nombre)) {
                    JOptionPane.showMessageDialog(null, "Nombre de SubCateogira repetido", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;

                }
            }
            service.addSubCategory(idCategoria, newSubCategory);
            TableSubCategories();
            service.saveXml();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
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
            if (service.consultarNombre(idCat, nombre)) {
                JOptionPane.showMessageDialog(null, "Ya existe una subcategoría con ese nombre. No se puede editar con un nombre duplicado.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            service.setEditSubCategory(idCat, idSub, nombre, descripcion);
            TableSubCategories();
            return true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la operación: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void searchSubCategoryTable(String dat) throws Exception {

        DefaultTableModel TableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Descripcion"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que las celdas no sean editable
            }
        };

        SubCategory subCategory = service.subCategoryGetId(gui.getCategoryId(), dat);

        if (subCategory == null) {

        } else {
            TableModel.addRow(new Object[]{subCategory.getSubCategoryID(), subCategory.getSubCategoryName(), subCategory.getSubCategoryDescription()});

        }

        this.gui.setTableSubCategorias(TableModel);

    }

    public void searchSubCategory(String dat) throws Exception {
        try {
            searchSubCategoryTable(dat);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "SubCategoria no encontrada ");
        }

    }

    //Articulos

    public void searchArticuloTable(String dat) throws Exception {
        DefaultTableModel TableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Marca", "Descripción"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Items articulo = service.articuloGetId(dat);
        if (articulo != null) {

            TableModel.addRow(new Object[]{articulo.getId(), articulo.getName(), articulo.getBrand(), articulo.getDescription()});
        }


        this.gui.setTableArticulos(TableModel);
    }

    public void searchArticulo(String dat) throws Exception {
        try {
            searchArticuloTable(dat);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Articulo no encontrada ");
        }
    }

    public boolean saveItems(String idC, String sub, String cod, String marca, String nombre, String descripcion, String Prese, String e, String precio) {
        try {
            if (service.BuscarNameArticulos(idC, sub, nombre)) {
                JOptionPane.showMessageDialog(null, "Ya existe un artículo con el mismo nombre. No se puede agregar.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            double num = Double.parseDouble(e);
            Items item = new Items(cod, marca, nombre, descripcion);
            Presentation presentation = new Presentation(Prese, num, Double.parseDouble(precio));
            service.guardarArticulo(idC, sub, item, presentation);
            TableItems();
            service.saveXml();
            return true;

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "El valor de la cantidad debe ser un número válido: " + e, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar el artículo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void TableItems() {

        try {

            List<Items> itemsList = service.allItems(gui.getCategoryId(), gui.getIDSubCategoria());
            DefaultTableModel TableModel = new DefaultTableModel(new String[]{"ID", "Marca", "Nombre", "Descripcion"}, itemsList.size()) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            for (int j = 0; j < itemsList.size(); j++) {
                Items item = itemsList.get(j);
                TableModel.setValueAt(item.getId(), j, 0);
                TableModel.setValueAt(item.getBrand(), j, 1);
                TableModel.setValueAt(item.getName(), j, 2);
                TableModel.setValueAt(item.getDescription(), j, 3);
            }
            this.gui.setArticulosTable(TableModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public boolean eliminarArticulo() {
        try {
            boolean eliminado = service.deleteItem(gui.getCategoryId(), gui.getIDSubCategoria(), gui.getArticuloId());

            if (eliminado) {
                TablePresentacion();
                TableItems();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {

            throw new RuntimeException("Error al eliminar el artículo: " + e.getMessage(), e);
        }
    }

    public boolean editarItems(String idC, String sub, String cod, String nombre, String marca, String descripcion, String presentacion, String cantidad, String precio) {
        try {
            /*
            if (service.BuscarNameArticulos(idC, sub, nombre)) {
                JOptionPane.showMessageDialog(null,"Ya existe un artículo con el mismo nombre. No se puede editar con un nombre duplicado.","Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

             */

            service.editarArticulo(idC, sub, cod, nombre, marca, descripcion, presentacion, cantidad, precio);
            //editPresentation(presentacion, cantidad);
            TableItems();
            TablePresentacion();
            service.saveXml();
            JOptionPane.showMessageDialog(null, "Articulo editado con exito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar el artículo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }


    //Presentaciones
    public boolean deletePresentacion(String me, String e, String precio) {
        double ee = Double.parseDouble(e);
        service.eliminarPresentation(gui.getCategoryId(), gui.getIDSubCategoria(), gui.getArticuloId()
                , new Presentation(me, ee, Double.parseDouble(precio)));
        return true;

    }


    public void TablePresentacion() {

        try {
            List<Presentation> presentations = service.allPresentation(gui.getCategoryId(), gui.getIDSubCategoria(), gui.getArticuloId());
            DefaultTableModel TableModel = new DefaultTableModel(new String[]{"Unidad", "Cantidad", "Precio Compra", "Precio Unidad"}, presentations.size()) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            for (int j = 0; j < presentations.size(); j++) {
                Presentation presentation = presentations.get(j);
                TableModel.setValueAt(presentation.getMeasure(), j, 0);
                TableModel.setValueAt(presentation.getQuantity(), j, 1);
                TableModel.setValueAt(presentation.getPrice(), j, 2);
                TableModel.setValueAt(presentation.precioVenta(presentation.getPrice()), j, 3);
            }
            this.gui.setPresentacionesTable(TableModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean agregarPresentaciones(String un, String pre, String precio) {
        try {
            double numeroComoDouble = Double.parseDouble(pre);

            // Llamar al servicio para agregar la presentación
            service.agregarPresentation(gui.getCategoryId(), gui.getIDSubCategoria(), gui.getArticuloId(),
                    new Presentation(un, numeroComoDouble, Double.parseDouble(precio)));


            JOptionPane.showMessageDialog(null, "Presentación agregada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return true;

        } catch (RuntimeException e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void TablePedidos() {
        try {
            List<Items> items = service.allItems(gui.getCategoryId(), gui.getIDSubCategoria());
            DefaultTableModel TableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Marca", "Descripcion", "Unidad", "Cantidad Disponible", "Precio Unitario", "Cantidad", "Total"}, items.size()) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 7;
                }

            };
            this.gui.setTableArticulosVender(TableModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void TableArticulosFinal() {
        try {
            DefaultTableModel TableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Marca", "Descripcion", "Unidad", "Cantidad Disponible", "Precio Unitario", "Cantidad", "Total"}, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }

            };
            this.gui.setArticulosVenderFinal(TableModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void TableSearchArticuloVenta() throws Exception {
        DefaultTableModel TableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Marca", "Descripcion", "Unidad", "Cantidad Disponible", "Precio Unitario", "Cantidad", "Total"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 7;//el usuario lo edita
            }
        };

        List<Items> items = service.allItems(gui.getCategoriaIdCb(), gui.getSubCategoriaIdCb());
        for (Items item : items) {
            if (item != null) {
                List<Presentation> presentaciones = item.getPresentation();
                for (Presentation p : presentaciones) {
                    TableModel.addRow(new Object[]{
                            item.getId(),
                            item.getName(),
                            item.getBrand(),
                            item.getDescription(),
                            p.getMeasure(),
                            p.getQuantity(),
                            p.precioVenta(p.getPrice()),
                            0,
                            0
                    });
                }
            }
        }
        this.gui.setTableArticulosVender(TableModel);
    }

    public void agregarArticuloTable() {
        JTable tableBusqueda = gui.getTableArticulosVender();
        JTable tablePedidos = gui.getTableArticulosFinal();

        int selectedRow = tableBusqueda.getSelectedRow();

        if (selectedRow != -1) {
            DefaultTableModel TableModelBusqueda = (DefaultTableModel) tableBusqueda.getModel();
            DefaultTableModel TableModelPedidos = (DefaultTableModel) tablePedidos.getModel();

            double cantidadUsuario;
            try {
                cantidadUsuario = Double.parseDouble(TableModelBusqueda.getValueAt(selectedRow, 7).toString());

                if (cantidadUsuario <= 0) {
                    JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Object[] rowData = new Object[TableModelBusqueda.getColumnCount()];
                for (int i = 0; i < TableModelBusqueda.getColumnCount(); i++) {
                    rowData[i] = TableModelBusqueda.getValueAt(selectedRow, i);
                }
                TableModelPedidos.addRow(rowData);
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            } catch (HeadlessException e) {
                throw new RuntimeException(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un artículo antes de agregarlo.");
        }
    }

    public void searchArticuloVenta() throws Exception {
        try {
            TableSearchArticuloVenta();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Articulo no encontrado ");
        }
    }
    public void llenarCombos(List<Category> categories){
        gui.comboCategoria().removeAllItems();
        for(Category cat : categories){
            gui.comboCategoria().addItem(cat.getId());
        }
    }

    public void llenarSubCategoria(String categ) {
        try {
            gui.comboSubCategoria().removeAllItems();
            List<SubCategory> subCategories = new ArrayList<>();
            for(Category cat : model.getCategories())
            {
                if(cat.getId().equals(categ)){
                    subCategories = cat.getSubCategoryList();
                }
            }
            if (subCategories.isEmpty()) {
                gui.comboSubCategoria().addItem("No hay subcategorías disponibles");
            } else {
                // Agregar las subcategorías al ComboBox
                for (SubCategory subCat : subCategories) {
                    gui.comboSubCategoria().addItem(subCat.getSubCategoryID());
                }
            }
        } catch (Exception e) {
            gui.comboSubCategoria().removeAllItems();
            gui.comboSubCategoria().addItem("Error al cargar subcategorías");
            e.printStackTrace();
        }

    }

}


