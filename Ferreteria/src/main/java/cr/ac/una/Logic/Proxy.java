package cr.ac.una.Logic;

import cr.ac.una.MVC.Controller.Controller;

import cr.ac.una.Protocol.IService;
import cr.ac.una.Protocol.Message;
import cr.ac.una.Protocol.Protocol;
import cr.ac.una.Protocol.User;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
//import cr.ac.una.Protocol.IService;

public class Proxy implements IService {
    private static IService theInstance;
    public static IService instance(){
        if (theInstance==null){
            theInstance=new Proxy();
        }
        return theInstance;
    }

    ObjectInputStream in;
    ObjectOutputStream out;
    Controller controller;

    public Proxy() {
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    Socket skt;
    private void connect() throws Exception{
        skt = new Socket(Protocol.SERVER,Protocol.PORT);
        out = new ObjectOutputStream(skt.getOutputStream() );
        out.flush();
        in = new ObjectInputStream(skt.getInputStream());
    }

    private void disconnect() throws Exception{
        skt.shutdownOutput();
        skt.close();
    }

    public User login(User u) throws Exception{
        connect();
        try {
            out.writeInt(Protocol.LOGIN);
            out.writeObject(u);
            out.flush();
            int response = in.readInt();
            if (response==Protocol.ERROR_NO_ERROR){
                User u1=(User) in.readObject();
                this.start();
                return u1;
            }
            else {
                disconnect();
                throw new Exception("No remote user");
            }
        } catch (IOException | ClassNotFoundException ex) {
            throw new Exception("Se produjo un error, pero no se mostrará en consola.");
        }
    }

    public void logout(User u) throws Exception{
        out.writeInt(Protocol.LOGOUT);
        out.writeObject(u);
        out.flush();
        this.stop();
        this.disconnect();
    }

    public void post(Message message){
        try {
            out.writeInt(Protocol.POST);
            out.writeObject(message);
            out.flush();
        } catch (IOException ex) {

        }
    }

    // LISTENING FUNCTIONS
    boolean continuar = true;
    public void start(){
        System.out.println("Client worker atendiendo peticiones...");
        Thread t = new Thread(new Runnable(){
            public void run(){
                listen();
            }
        });
        continuar = true;
        t.start();
    }
    public void stop(){
        continuar=false;
    }

    public void listen(){
        int method;
        while (continuar) {
            try {
                method = in.readInt();
                System.out.println("DELIVERY");
                System.out.println("Operacion: "+method);
                switch(method){
                    case Protocol.DELIVER:
                        try {
                            Message message=(Message)in.readObject();
                            deliver(message);
                        } catch (ClassNotFoundException ex) {}
                        break;
                }
                out.flush();
            } catch (IOException  ex) {
                continuar = false;
            }
        }
    }

    private void deliver( final Message message ){
    }

}
