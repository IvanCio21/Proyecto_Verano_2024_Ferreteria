package datas;

import Logic.Category;
import Logic.Items;
import Logic.Presentation;
import Logic.SubCategory;
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

public class ItemDom {

    private Items items;
    private static String xmlFilePath = "";

    public ItemDom(String filepath) {
        this.items = new Items();
        xmlFilePath = filepath;
        saveItems(items.dataName(), items.getData());
    }

    private void saveItems(String[] dataName, String[] data) {
        try {

            File xmlFile = new File(xmlFilePath);

            if (!xmlFile.exists()) {
                DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
                Document document = documentBuilder.newDocument();

                Element root = document.createElement("Ferreteria");
                document.appendChild(root);

                // Sub-root element: Articulo
                Element item = document.createElement("Articulo");
                root.appendChild(item);

                // Sub-element: articulos
                Element items = document.createElement("articulos");
                item.appendChild(items);

                // set an attribute to staff element
                Attr attr = document.createAttribute(dataName[0]);
                attr.setValue(data[0]);
                item.setAttributeNode(attr);

                for (int i = 1; i < data.length; i++) {
                    //crea la etiqueta
                    Element dato = document.createElement(dataName[i]);

                    dato.appendChild(document.createTextNode(data[i]));

                    item.appendChild(dato);//agrega al objeto

                }

                // create the xml file
                //transform the DOM Object to an XML File
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                DOMSource domSource = new DOMSource(document);
                StreamResult streamResult = new StreamResult(new File(xmlFilePath));
                transformer.transform(domSource, streamResult);

                System.out.println("Registro guardado");
            }
        } catch (ParserConfigurationException | TransformerException pce) {
            System.out.println(pce.getMessage());

        }
    }

    public boolean AddItem(Items item, String[] dataName, String[] data) throws TransformerConfigurationException, TransformerException {
        boolean result = false;
        boolean idexist = false;

        //----------------Check if item id already exists--------------

        try {
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            if (!xmlFile.exists())
                return result;
            Document doc = builder.parse(xmlFile);
            NodeList itemsNodes = doc.getElementsByTagName("Articulo");

            for (int i = 0; i < itemsNodes.getLength(); i++) {
                Node itemNode = itemsNodes.item(i);
                if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element itemElement = (Element) itemNode;
                    //int id = Integer.parseInt(userElement.getAttribute("id"));

                    if (item.getId().equals(itemElement.getAttribute("id"))) {
                        idexist = true;
                        break;
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException pce) {
        }


        //---------------If user id does not exits---------------------
        try {
            if (!idexist) {
                File xmlFile = new File(xmlFilePath);

                if (!xmlFile.exists()) {
                    DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
                    Document document = documentBuilder.newDocument();

                    // root element
                    Element root = document.createElement("Articulos"); //items
                    document.appendChild(root);

                    // item element
                    Element itemElement = document.createElement("Articulo");
                    root.appendChild(itemElement);

                    // set an attribute to staff element
                    Attr attr = document.createAttribute(dataName[0]);
                    attr.setValue(data[0]);
                    itemElement.setAttributeNode(attr);

                    for (int i = 1; i < data.length; i++) {
                        //crea la etiqueta
                        Element dato = document.createElement(dataName[i]);

                        dato.appendChild(document.createTextNode(data[i]));

                        itemElement.appendChild(dato);//agrega al objeto

                    }

                    // create the xml file
                    //transform the DOM Object to an XML File
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource domSource = new DOMSource(document);
                    StreamResult streamResult = new StreamResult(new File(xmlFilePath));
                    transformer.transform(domSource, streamResult);

                    System.out.println("Registro guardado");
                    result = true;
                }
            }
        } catch (ParserConfigurationException pce) {
        }

        return result;
    }
}