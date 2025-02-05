package cr.ac.una;

import cr.ac.una.Protocol.IService;
import cr.ac.una.Protocol.Message;
import cr.ac.una.Protocol.Protocol;
import cr.ac.una.Protocol.User;
import cr.ac.una.Service;
import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    ServerSocket srv;
    List<Worker> workers;

//    Data users = new Data(); xml

    public Server() {
        try {
            srv = new ServerSocket(Protocol.PORT);
            workers =  Collections.synchronizedList(new ArrayList<Worker>());
            System.out.println("Servidor iniciado...");
        } catch (IOException ex) {
        }
    }

    public void run(){
        IService service = new Service();

        boolean continuar = true;
        ObjectInputStream in=null;
        ObjectOutputStream out=null;
        Socket skt=null;
        while (continuar) {
            try {
                //skt = srv.accept();
                skt = srv.accept();
                in = new ObjectInputStream(skt.getInputStream());
                out = new ObjectOutputStream(skt.getOutputStream() );
                System.out.println("Conexion Establecida...");
                User user=this.login(in,out,service);
                Worker worker = new Worker(this,in,out,user, service);
                workers.add(worker);
                worker.start();
            }
            catch (IOException | ClassNotFoundException ex) {}
            catch (Exception ex) {
                try {
                    out.writeInt(Protocol.ERROR_LOGIN);
                    out.flush();
                    skt.close();
                } catch (IOException ex1) {}
                System.out.println("Conexion cerrada...");
            }
        }
    }

    private User login(ObjectInputStream in,ObjectOutputStream out,IService service) throws IOException, ClassNotFoundException, Exception{
        int method = in.readInt();
        if (method!=Protocol.LOGIN) throw new Exception("Should login first");
        User user=(User)in.readObject();
        user = service.login(user);



        if (user == null || !user.getStatus().equals("Activo")) {
            out.writeInt(Protocol.ERROR_LOGIN);
            out.flush();
            throw new Exception(user == null ? "Usuario o contraseña incorrectos" : "Usuario bloqueado");
        }

        out.writeInt(Protocol.ERROR_NO_ERROR);
        out.writeObject(user);
        out.flush();
        return user;
    }

    public void deliver(Message message){
        synchronized(workers) {
            for (Worker wk : workers) {
                wk.deliver(message);
            }
        }
    }

    public void remove(User u){
        for(Worker wk:workers) if(wk.user.equals(u)){workers.remove(wk);break;}
        System.out.println("Quedan: " + workers.size());
    }
}
