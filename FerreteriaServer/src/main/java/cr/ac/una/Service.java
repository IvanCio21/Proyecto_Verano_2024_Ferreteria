package cr.ac.una;

import cr.ac.una.Data.UsersXML;
import cr.ac.una.Protocol.IService;
import cr.ac.una.Protocol.Message;
import cr.ac.una.Protocol.User;

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

    public User login(User p) throws Exception {
        for (User u : data) {
            if (p.getUserName().equals(u.getUserName())) {
                if (p.getPassword().equals(u.getPassword())) {
                    u.resetearIntentosFallidos();
                    return u;
                } else {
                    u.incrementarIntentosFallidos();
                    if (u.getIntentosFallidos() >= 3) {
                        u.setStatus("Bloqueado");
                    }
                    return null;
                }
            }
        }
        return null;
    }



    public void logout(User p) throws Exception{

    }

}
