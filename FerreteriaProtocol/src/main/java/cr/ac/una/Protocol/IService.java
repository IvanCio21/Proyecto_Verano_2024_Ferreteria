package cr.ac.una.Protocol;

public interface IService {
     User login(User u) throws Exception;
    void logout(User u) throws Exception;
    void post(Message m);
}
