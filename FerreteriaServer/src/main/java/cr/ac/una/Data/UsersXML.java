package cr.ac.una.Data;

import cr.ac.una.Protocol.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.*;

import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class UsersXML {
//    public static void main(String[] args) {
//        // Crear usuarios
//        List<User> users;
//        users = new ArrayList<>();
//        users.add(new User("MyP455W04D", "Activo", "JohnDoe"));
//        users.add(new User("qiAqv547Q#$", "Bloqueado", "JaneDoe"));
//        users.add(new User("AnyPassword", "Activo", "JuanPerez"));
//
//        generarXML(users, "usuarios.xml");
//    }

    public static void generarXML(List<User> users, String fileName) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();


            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("usuarios");
            doc.appendChild(rootElement);

            for (User user : users) {
                // Crear el elemento <usuario>
                Element usuarioElement = doc.createElement("usuario");
                rootElement.appendChild(usuarioElement);

                // Crear el elemento <Username>
                Element loginElement = doc.createElement("login");
                loginElement.appendChild(doc.createTextNode(user.getUserName()));
                usuarioElement.appendChild(loginElement);

                // Crear el elemento <password>
                Element passwordElement = doc.createElement("password");
                passwordElement.appendChild(doc.createTextNode(user.getPassword()));
                usuarioElement.appendChild(passwordElement);

                // Crear el elemento <estado>
                Element estadoElement = doc.createElement("estado");
                estadoElement.appendChild(doc.createTextNode(user.getStatus()));
                usuarioElement.appendChild(estadoElement);
            }

            // Guardar el contenido en un archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(fileName));
            transformer.transform(source, result);

            System.out.println("Archivo XML generado exitosamente: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> cargarUsuarios() {
        List<User> usuarios = new ArrayList<>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        try {
            dbFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbFactory.newDocumentBuilder();
            Document doc = db.parse(new File("usuarios.xml"));
            doc.getDocumentElement().normalize();

            // Obtener la lista de nodos <usuario>
            NodeList userNodeList = doc.getElementsByTagName("usuario");
            for (int i = 0; i < userNodeList.getLength(); i++) {
                Node userNode = userNodeList.item(i);
                if (userNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element userElement = (Element) userNode;


                    String userName = userElement.getElementsByTagName("login").item(0).getTextContent();
                    String password = userElement.getElementsByTagName("password").item(0).getTextContent();
                    String status = userElement.getElementsByTagName("estado").item(0).getTextContent();


                    User user = new User(password, status, userName);
                    usuarios.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }

}
