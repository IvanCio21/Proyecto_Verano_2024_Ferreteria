package cr.ac.una;

import cr.ac.una.Data.UsersXML;
import cr.ac.una.Protocol.IService;
import cr.ac.una.Protocol.Message;
import cr.ac.una.Protocol.User;

import javax.swing.*;
import java.util.List;

public class Service implements IService {

private UsersXML usersXML;
private List<User> data;

    public Service() {
        usersXML = new UsersXML();
        data = usersXML.cargarUsuarios();
    }

    public void post(Message m){
        // if wants to save messages, ex. recivier no logged on
    }

    public User login(User usuario) throws Exception {

        User user = null;
        boolean encontrado = false;
        for (User u : data) {
            if (usuario.getUserName().equals(u.getUserName())) {

                encontrado = true;

                // Si el usuario está bloqueado
                if ("Bloqueado".equals(u.getStatus())) {
                    JOptionPane.showMessageDialog(null, "El usuario está bloqueado.", "Error", JOptionPane.ERROR_MESSAGE);
                    return null;
                }

                // Si la contraseña es incorrecta
                if (!usuario.getPassword().equals(u.getPassword())) {
                    u.setIntentosFallidos(u.getIntentosFallidos() + 1); // Incrementamos intentos fallidos

                    // Bloqueamos si alcanza los 3 intentos
                    if (u.getIntentosFallidos() == 3) {
                        u.setStatus("Bloqueado");
                        JOptionPane.showMessageDialog(null, "Límite de intentos fallidos alcanzado. El usuario será bloqueado.", "Advertencia", JOptionPane.ERROR_MESSAGE);
                        return null;
                    }

                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta. Intentos fallidos: " + u.getIntentosFallidos(), "Error", JOptionPane.ERROR_MESSAGE);
                    return null;
                }

                u.setIntentosFallidos(0);
                return u;
            }
        }
        if(!encontrado) {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }




    public void logout(User p) throws Exception{

    }

}
