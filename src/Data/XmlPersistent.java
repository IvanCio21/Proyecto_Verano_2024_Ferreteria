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
private List<Category> categorias;

    public void guardarCategorias(List<Category> categorias) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("inventario");
            doc.appendChild(rootElement);

            for (Category cat : categorias) {
                Element catElement = doc.createElement("categoria");
                rootElement.appendChild(catElement);

                Element id = doc.createElement("id");
                id.appendChild(doc.createTextNode(String.valueOf(cat.getId())));
                catElement.appendChild(id);

                Element nombre = doc.createElement("nombre");
                nombre.appendChild(doc.createTextNode(cat.getName()));
                catElement.appendChild(nombre);

                Element descripcion = doc.createElement("descripcion");
                descripcion.appendChild(doc.createTextNode(cat.getDescription()));
                catElement.appendChild(descripcion);

                for (SubCategory subCategory : cat.getSubCategoryList()) {
                    Element subCatElement = doc.createElement("subcategoria");
                    catElement.appendChild(subCatElement);

                    Element subCategoryId = doc.createElement("id");
                    subCategoryId.appendChild(doc.createTextNode(String.valueOf(subCategory.getSubCategoryID())));
                    subCatElement.appendChild(subCategoryId);

                    Element subCategoryNombre = doc.createElement("nombre");
                    subCategoryNombre.appendChild(doc.createTextNode(subCategory.getSubCategoryName()));
                    subCatElement.appendChild(subCategoryNombre);

                    for (Items articulos : subCategory.getArticulos()) {
                        Element articuloElement = doc.createElement("articulo");
                        subCatElement.appendChild(articuloElement);

                        Element articuloId = doc.createElement("id");
                        articuloId.appendChild(doc.createTextNode(String.valueOf(articulos.getId())));
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

                        for (Presentation presentacion : articulos.getPresentation()) {
                            Element presentationElement = doc.createElement("presentacion");
                            articuloElement.appendChild(presentationElement);

                            Element presentationId = doc.createElement("id");
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

    public List<Category> cargarCategorias() {
         categorias = new ArrayList<>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        try {
            dbFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbFactory.newDocumentBuilder();
            Document doc = db.parse(new File("Inventario.xml"));
            doc.getDocumentElement().normalize();

            NodeList categoriaNList = doc.getElementsByTagName("categoria");

            for (int i = 0; i < categoriaNList.getLength(); i++) {
                Element categoriaElement = (Element) categoriaNList.item(i);

                String idCategoria = categoriaElement.getElementsByTagName("id").item(0).getTextContent();
                String nombreCategoria = categoriaElement.getElementsByTagName("nombre").item(0).getTextContent();
                String descripcionCategoria = categoriaElement.getElementsByTagName("descripcion").item(0).getTextContent();

                List<SubCategory> subCategorias = new ArrayList<>();
                NodeList subList = categoriaElement.getElementsByTagName("subcategoria");

                for (int j = 0; j < subList.getLength(); j++) {
                    Element subElement = (Element) subList.item(j);

                    int idSubCategoria = Integer.parseInt(subElement.getAttribute("id"));
                    String nombreSubCategoria = subElement.getElementsByTagName("nombre").item(0).getTextContent();

                    List<Items> articulos = new ArrayList<>();
                    NodeList artList = subElement.getElementsByTagName("articulo");

                    for (int k = 0; k < artList.getLength(); k++) {
                        Element artElement = (Element) artList.item(k);

                        int idArticulo = Integer.parseInt(artElement.getAttribute("id"));
                        String marcaArticulo = artElement.getElementsByTagName("marca").item(0).getTextContent();
                        String nombreArticulo = artElement.getElementsByTagName("nombre").item(0).getTextContent();
                        String descriptionArticulo = artElement.getElementsByTagName("descripcion").item(0).getTextContent();

                        List<Presentation> presentaciones = new ArrayList<>();
                        NodeList presentationList = artElement.getElementsByTagName("presentacion");

                        for (int l = 0; l < presentationList.getLength(); l++) {
                            Element presentationElement = (Element) presentationList.item(l);

                            int idPresentacion = Integer.parseInt(presentationElement.getAttribute("id"));
                            String unidad = presentationElement.getElementsByTagName("unidad").item(0).getTextContent();
                            double cantidad = Double.parseDouble(presentationElement.getElementsByTagName("cantidad").item(0).getTextContent());

                            Presentation presentacion = new Presentation(idPresentacion, unidad, cantidad);
                            presentaciones.add(presentacion);
                        }

                        Items articulo = new Items(idArticulo, marcaArticulo, nombreArticulo, descriptionArticulo, presentaciones);
                        articulos.add(articulo);
                    }

                    SubCategory subCategoria = new SubCategory(idSubCategoria, nombreSubCategoria, articulos);
                    subCategorias.add(subCategoria);
                }

                Category categoria = new Category(idCategoria, nombreCategoria, descripcionCategoria, subCategorias);
                categorias.add(categoria);
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return categorias;
    }

    public List<Category> getCategorias() {
        return categorias;
    }
}
