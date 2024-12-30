package XML;

import dataStructure.Items;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class ItemDom {

        private Items items;
        private static String xmlFilePath = "Articulos.xml";

        public ItemDom() {
            items = new Items();
            CreateFile("Ferreteria", xmlFilePath, "Articulos", items.dataName(), items.getData());
        }

        private void CreateFile(String objectName, String fileAddress, String elementeType, String[] dataName, String[] data) {
            try {

                File xmlFile = new File(xmlFilePath);

                if (!xmlFile.exists()) {
                    DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
                    Document document = documentBuilder.newDocument();

                    // root element
                    Element root = document.createElement(objectName); //items
                    document.appendChild(root);

                    // item element
                    Element item = document.createElement(elementeType);
                    root.appendChild(item);

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
                    DOMSource domSource = new DOMSource(document);
                    StreamResult streamResult = new StreamResult(new File(xmlFilePath));
                    transformer.transform(domSource, streamResult);

                    System.out.println("Registro guardado");
                }
            } catch (ParserConfigurationException | TransformerException pce) {
                System.out.println(pce.getMessage());

            }
        }
    }
