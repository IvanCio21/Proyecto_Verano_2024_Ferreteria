package XML;

import dataStructure.Items;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class ItemDom {

        private Items items;
        private static String xmlFilePath = "Articulos.xml";

        public ItemDom() {
            items = new Items();
            CreateFile("Articulos", xmlFilePath, "Articulo", items.dataName(), items.getData());
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

    public boolean AddItem(Items item) throws TransformerConfigurationException, TransformerException
    {
        boolean result = false;
        boolean idexist = false;

        //----------------Check if item id already exists--------------

        try {
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            if(!xmlFile.exists())
                return result;
            Document doc = builder.parse(xmlFile);
            NodeList itemsNodes = doc.getElementsByTagName("Articulo");

            for(int i = 0; i < itemsNodes.getLength(); i++) {
                Node itemNode = itemsNodes.item(i);
                if(itemNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element itemElement = (Element) itemNode;
                    //int id = Integer.parseInt(userElement.getAttribute("id"));

                    if(item.getId().equals(itemElement.getAttribute("id")))
                    {
                        idexist = true;
                        break;
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException pce) {}



        //---------------If user id does not exits---------------------
        try {
            if(!idexist)
            {
                File xmlFile = new File(xmlFilePath);
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                if(!xmlFile.exists())
                    return result;
                Document document = builder.parse(xmlFile);
                //NodeList userNodes = document.getElementsByTagName("users");

                Node root = document.getElementsByTagName("articulo").item(0);

                //Element root = document.getElementById("users");
                // user element
                Element newuser = document.createElement("user");

                root.appendChild(newuser);

                // set an attribute to staff element
                Attr attr = document.createAttribute("id");
                attr.setValue(user.getID());
                newuser.setAttributeNode(attr);


                // firstname element
                Element firstName = document.createElement("firstname");
                firstName.appendChild(document.createTextNode(user.getFirstname()));
                newuser.appendChild(firstName);

                // lastname element
                Element lastname = document.createElement("lastname");
                lastname.appendChild(document.createTextNode(user.getLastname()));
                newuser.appendChild(lastname);

                // email element
                Element username = document.createElement("username");
                username.appendChild(document.createTextNode(user.getUsername()));
                newuser.appendChild(username);

                // department elements
                Element password = document.createElement("password");
                password.appendChild(document.createTextNode(user.getPassword()));
                newuser.appendChild(password);

                // create the xml file
                //transform the DOM Object to an XML File
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(document);
                StreamResult streamResult = new StreamResult(new File(xmlFilePath));

                // If you use
                // StreamResult result = new StreamResult(System.out);
                // the output will be pushed to the standard output ...
                // You can use that for debugging

                transformer.transform(domSource, streamResult);

                result = true;
            }

        } catch (ParserConfigurationException | IOException | SAXException pce) {
        }

        return result;
    }
    }
