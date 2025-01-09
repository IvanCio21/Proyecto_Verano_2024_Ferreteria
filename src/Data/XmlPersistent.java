package Data;

import Logic.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlPersistent {

    public void guardarCategorias(List<Category> categorias){
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("inventario");
            doc.appendChild(rootElement);

            for(Category cat : categorias){
                Element catElement = doc.createElement("categoria");
                rootElement.appendChild(catElement);

                Element id = doc.createElement("ID");
                id.appendChild(doc.createTextNode(cat.getId()));
                catElement.appendChild(id);

                Element nombre = doc.createElement("nombre");
                nombre.appendChild(doc.createTextNode(cat.getName()));
                catElement.appendChild(nombre);

                Element descripcion = doc.createElement("descripcion");
                descripcion.appendChild(doc.createTextNode(cat.getDescription()));
                catElement.appendChild(descripcion);

                for(SubCategory subCategory : cat.getSubCategoryList()){
                    Element subCatElement = doc.createElement("subcategoria");
                    catElement.appendChild(subCatElement);

                    Element subCategoryId = doc.createElement("ID");
                    subCategoryId.appendChild(doc.createTextNode(subCategory.getId()));
                    subCatElement.appendChild(subCategoryId);

                    Element subCategoryNombre = doc.createElement("nombre");
                    subCategoryNombre.appendChild(doc.createTextNode(subCategory.getName()));
                    subCatElement.appendChild(subCategoryNombre);

                    Element subCategoryDescripcion = doc.createElement("descripcion");
                    subCategoryDescripcion.appendChild(doc.createTextNode(subCategory.getDescription()));
                    subCatElement.appendChild(subCategoryDescripcion);

                    for(Items articulos : subCategory.getArticulos()){
                        Element articuloElement = doc.createElement("articulo");
                        subCatElement.appendChild(articuloElement);

                        Element articuloId = doc.createElement("ID");
                        articuloId.appendChild(doc.createTextNode(articulos.getId()));
                        articuloElement.appendChild(articuloId);

                        Element articuloMarca = doc.createElement("marca");
                        articuloMarca.appendChild(doc.createTextNode(articulos.getMarca()));
                        articuloElement.appendChild(articuloMarca);

                        Element articuloNombre = doc.createElement("nombre");
                        articuloNombre.appendChild(doc.createTextNode(articulos.getName()));
                        articuloElement.appendChild(articuloNombre);

                        Element articuloDescripcion = doc.createElement("descripcion");
                        articuloDescripcion.appendChild(doc.createTextNode(articulos.getDescription()));
                        articuloElement.appendChild(articuloDescripcion);

                        for(Presentation presentacion : articulos.getPresentation()){
                            Element presentationElement = doc.createElement("presentacion");
                            articuloElement.appendChild(presentationElement);

                            Element presentationId = doc.createElement("ID");
                            presentationElement.appendChild(presentationId);

                            Element presentationUnidad = doc.createElement("Unidad");
                            presentationElement.appendChild(presentationUnidad);

                            Element presentationCantidad = doc.createElement("Cantidad");
                            presentationElement.appendChild(presentationCantidad);
                        }
                    }
                }
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            StreamResult result = new StreamResult(new File("Articulos.xml"));
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Category> cargarCategorias(){
        List<Category> categorias = new ArrayList<>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        try {
            dbFactory.setAttribute(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbFactory.newDocumentBuilder();

            Document doc = db.parse(new File("Articulos.xml"));

            doc.getDocumentElement().normalize();

            NodeList categoriaNList = doc.getElementsByTagName("categoria");

            for (int i = 0; i < categoriaNList.getLength(); i++){

                Node NodeCategoria = categoriaNList.item(i);

                if (NodeCategoria.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) NodeCategoria;

                    String idCategoria = eElement.getElementsByTagName("ID").item(0).getTextContent();
                    String nameCategoria = eElement.getElementsByTagName("nombre").item(0).getTextContent();
                    String descripcionCategoria = eElement.getElementsByTagName("descripcion").item(0).getTextContent();
                    Category category = new Category(idCategoria, nameCategoria, descripcionCategoria);
                    //SUBCATEGORIA//
                            NodeList subcategoriaNodes = eElement.getElementsByTagName("subcategoria");
                            ArrayList<SubCategory> subcategoriaList = new ArrayList<>();

                            for (int j = 0; j < subcategoriaNodes.getLength(); j++) {
                                Node node = subcategoriaNodes.item(i);
                                if (node.getNodeType() == Node.ELEMENT_NODE) {
                                    Element subcategoriaElement = (Element) node;

                                    String idSubCategoria = subcategoriaElement.getElementsByTagName("codigo").item(0).getTextContent();
                                    String namesubCategoria = subcategoriaElement.getElementsByTagName("nombre").item(0).getTextContent();
                                    String descripcionsubCategoria = subcategoriaElement.getElementsByTagName("descripcion").item(0).getTextContent();
                                    SubCategory subCategoria = new SubCategory(idSubCategoria, namesubCategoria, descripcionsubCategoria);
                                    subcategoriaList.add(subCategoria);
                                }
                            }
                            category.setSubCategoryList(subcategoriaList);
                            categorias.add(category);
                        }
                    }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (SAXException ex) {
            throw new RuntimeException(ex);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        return categorias;

        }


    }
