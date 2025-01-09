package Logic;
import Data.Data;
import Data.XmlPersistent;

import java.util.List;

public class Service {

    private static Service theInstance;
    private Data data = new Data();
    private XmlPersistent xml = new XmlPersistent();

    public Service() {}

    public static Service getInstance() {
        if (theInstance == null) {
            theInstance = new Service();
        }
        return theInstance;
    }



    public Category getIDCategoria(String cedula) throws Exception {
        Category result = (Category) this.data.getCategorias().stream().filter((c) -> {
            return c.getId().equals(cedula);
        }).findFirst().orElse((Category) null);
        if (result != null) {
            return result;
        } else {
            throw new Exception("Empleado no existe");
        }
    }

    public Category getNameCategoria(String cedula) throws Exception {
        Category result = (Category) this.data.getCategorias().stream().filter((c)->c.getName().equals(cedula))
                .findFirst().orElse((Category) null);
        if (result != null) {
            return result;
        }else {
            throw new Exception("Empleado no existe");
        }
    }

    public List<Category> getAllCategorias()  {return this.data.getCategorias(); }

    public Category getCategoryNum(int pos) throws Exception {
        Category result = (Category) this.data.getCategorias().get(pos);
        if (result != null) {
            return result;
        }else {
            throw new Exception("Categoria no existe");
        }

    }



}
