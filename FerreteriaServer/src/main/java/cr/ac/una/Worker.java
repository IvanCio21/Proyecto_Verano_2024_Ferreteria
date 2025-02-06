package cr.ac.una;

import cr.ac.una.Protocol.IService;
import cr.ac.una.Protocol.Message;
import cr.ac.una.Protocol.Protocol;
import cr.ac.una.Protocol.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Worker {
    Server srv;
    ObjectInputStream in;
    ObjectOutputStream out;
    IService service;
    User user;

    public Worker(Server srv, ObjectInputStream in, ObjectOutputStream out, User user, IService service) {
        this.srv=srv;
        this.in=in;
        this.out=out;
        this.user=user;
        this.service=service;
    }

    boolean continuar;

    public void start(){
        try {
            System.out.println("Worker atendiendo peticiones...");
            Thread t = new Thread(new Runnable(){
                public void run(){
                    listen();
                }
            });
            continuar = true;
            t.start();
        } catch (Exception ex) {
        }
    }

    public void stop(){
        continuar=false;
        System.out.println("Conexion cerrada...");
    }

    public void listen(){
        int method;
        while (continuar) {
            try {
                method = in.readInt();
                System.out.println("Operacion: "+method);
                switch(method){
                    //case Protocol.LOGIN: done on accept
                    case Protocol.LOGOUT:
                        try {
                            srv.remove(user);
                            //service.logout(user); //nothing to do
                        } catch (Exception ex) {}
                        stop();
                        break;
                    case Protocol.POST:
                        Message message=null;
                        try {
                            message = (Message)in.readObject();
                            message.setSender(user);
                            service.post(message); // if wants to save messages, ex. recivier no logged on
                            srv.deliver(message);
                            System.out.println(user.getUserName()+": "+message.getMessage());
                        } catch (ClassNotFoundException ex) {}
                        break;
                }
                out.flush();
            } catch (IOException  ex) {
                System.out.println(ex);
                continuar = false;
            }
        }
    }

    public void deliver(Message message){ //Enviar el mensaje
        try {
            out.writeInt(Protocol.DELIVER);
            out.writeObject(message);
            out.flush();
        } catch (IOException ex) {
        }
    }
}
