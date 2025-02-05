package cr.ac.una.Data;

import cr.ac.una.Logic.*;
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

    public void guardarCategorias(Data data) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
            //Raiz
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("inventario");
            doc.appendChild(rootElement);

            String dato;

            for (int i = 0; i < data.getCategorias().size(); i++) { // categorias

                //Raiz
                Element categoryElement = doc.createElement("categoria");
                Category category = (Category) data.getCategorias().get(i);
                categoryElement.setAttribute("id", category.getId());
                rootElement.appendChild(categoryElement);

                //Nombre//
                dato = category.getName();
                Element name = doc.createElement("nombre");
                name.appendChild(doc.createTextNode(dato));
                categoryElement.appendChild(name);

                //Descripcion//
                dato = category.getDescription();
                Element description = doc.createElement("descripcion");
                description.appendChild(doc.createTextNode(dato));
                categoryElement.appendChild(description);

                //SUBCATEGORIA//
                List<SubCategory> subCategoryList = category.getSubCategoryList();
                if (subCategoryList.size() > 0) {
                    Element subcategories = doc.createElement("subCategorias");
                    for (SubCategory subCategory : subCategoryList) {

                        //Atributo subCategoria//
                        Element subCategoryElement = doc.createElement("subcategoria");
                        subCategoryElement.setAttribute("id", subCategory.getSubCategoryID());
                        subcategories.appendChild(subCategoryElement);

                        //Nombre subcategoria//
                        Element subCategoryName = doc.createElement("nombre");
                        subCategoryName.appendChild(doc.createTextNode(subCategory.getSubCategoryName()));
                        subCategoryElement.appendChild(subCategoryName);

                        //Descripcion SubCategoria//
                        Element subCategoryDescription = doc.createElement("descripcion");
                        subCategoryDescription.appendChild(doc.createTextNode(subCategory.getSubCategoryDescription()));
                        subCategoryElement.appendChild(subCategoryDescription);

                        List<Items> itemsList = subCategory.getItems();
                        if (itemsList.size() > 0) {
                            Element itemsE = doc.createElement("articulos");
                            for (Items items : subCategory.getItems()) {
                                //Atributo Articulo
                                Element itemsElement = doc.createElement("articulo");
                                itemsElement.setAttribute("id", items.getId());
                                itemsE.appendChild(itemsElement);

                                //Nombre articulo//
                                Element itemName = doc.createElement("nombre");
                                itemName.appendChild(doc.createTextNode(items.getName()));
                                itemsElement.appendChild(itemName);

                                //Marca//
                                Element itemBrand = doc.createElement("marca");
                                itemBrand.appendChild(doc.createTextNode(items.getBrand()));
                                itemsElement.appendChild(itemBrand);

                                //Descripcion//
                                Element itemDescription = doc.createElement("descripcion");
                                itemDescription.appendChild(doc.createTextNode(items.getDescription()));
                                itemsElement.appendChild(itemDescription);

                                List<Presentation> presentationList = items.getPresentation();
                                if (presentationList.size() > 0) {
                                    Element presentationsE = doc.createElement("presentaciones");
                                    for (Presentation presentation : items.getPresentation()) {
                                        Element presentationElement = doc.createElement("presentacion");
                                        presentationsE.appendChild(presentationElement);

                                        //Unidad de medida//
                                        Element measure = doc.createElement("unidad");
                                        measure.appendChild(doc.createTextNode(presentation.getMeasure()));
                                        presentationElement.appendChild(measure);

                                        //Capacidad//

                                        Element quantity = doc.createElement("cantidad");
                                        quantity.appendChild(doc.createTextNode(String.valueOf(presentation.getQuantity())));
                                        presentationElement.appendChild(quantity);

                                        Element price = doc.createElement("precio");
                                        price.appendChild(doc.createTextNode(String.valueOf(presentation.getPrice())));
                                        presentationElement.appendChild(price);
                                    }
                                    itemsElement.appendChild(presentationsE);
                                }
                            }
                            subCategoryElement.appendChild(itemsE);
                        }
                    }
                    categoryElement.appendChild(subcategories);
                }
            }
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            // initialize StreamResult with File object to save to file
            StreamResult result = new StreamResult(new File("Inventario.xml"));
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveXml(Document doc, String address) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(new File(address));
            transformer.transform(domSource, streamResult);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }


    public List cargarCategorias() {
        categorias = new ArrayList<>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        try {
            dbFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbFactory.newDocumentBuilder();
            Document doc = db.parse(new File("Inventario.xml"));
            doc.getDocumentElement().normalize();


            //CATEGORIA
            NodeList categoryNodeList = doc.getElementsByTagName("categoria");
            for (int i = 0; i < categoryNodeList.getLength(); i++) {
                //Nodo actual
                Node CategoryNode = categoryNodeList.item(i);
                if (CategoryNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element categoryElement = (Element) CategoryNode;

                    //SUBCATEGORIA
                    NodeList subCategoryNodeList = categoryElement.getElementsByTagName("subcategoria");
                    List<SubCategory> subCategoryList = new ArrayList<>();
                    for (int j = 0; j < subCategoryNodeList.getLength(); j++) {
                        Node subCategoryNode = subCategoryNodeList.item(j);
                        if (subCategoryNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element subCategoryElement = (Element) subCategoryNode;

                            //ARTICULO
                            NodeList itemNodeList = subCategoryElement.getElementsByTagName("articulo");
                            List<Items> itemsList = new ArrayList<>();
                            for (int k = 0; k < itemNodeList.getLength(); k++) {
                                Node itemNode = itemNodeList.item(k);
                                if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element itemElement = (Element) itemNode;

                                    //PRESENTACION
                                    NodeList presentationNodeList = itemElement.getElementsByTagName("presentacion");
                                    List<Presentation> presentationList = new ArrayList<>();
                                    for (int l = 0; l < presentationNodeList.getLength(); l++) {
                                        Node presentationNode = presentationNodeList.item(l);
                                        if (presentationNode.getNodeType() == Node.ELEMENT_NODE) {
                                            Element presentationElement = (Element) presentationNode;
                                            Presentation presentation = new Presentation(presentationElement.getElementsByTagName("unidad").item(0).getTextContent(),
                                                    Double.parseDouble(presentationElement.getElementsByTagName("cantidad").item(0).getTextContent()),
                                                    Double.parseDouble(presentationElement.getElementsByTagName("precio").item(0).getTextContent()));
                                            presentationList.add(presentation);
                                        }
                                    }

                                    Items items = new Items(itemElement.getAttribute("id"),
                                            itemElement.getElementsByTagName("marca").item(0).getTextContent(),
                                            itemElement.getElementsByTagName("nombre").item(0).getTextContent(),
                                            itemElement.getElementsByTagName("descripcion").item(0).getTextContent(),
                                            presentationList);
                                    itemsList.add(items);
                                }
                            }
                            SubCategory subcategory = new SubCategory(subCategoryElement.getAttribute("id"), subCategoryElement.getElementsByTagName("nombre").item(0).getTextContent(),
                                    subCategoryElement.getElementsByTagName("descripcion").item(0).getTextContent(), itemsList);
                            subCategoryList.add(subcategory);
                        }
                    }
                    Category category = new Category(categoryElement.getAttribute("id"), categoryElement.getElementsByTagName("nombre").item(0).getTextContent(),
                            categoryElement.getElementsByTagName("descripcion").item(0).getTextContent(), subCategoryList);
                    categorias.add(category);
                }
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
