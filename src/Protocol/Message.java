package Protocol;

import java.io.Serializable;
import java.util.List;

public class Message {

        User sender;
        String message;
        List<String> connectedUsers; //lista de usuarios conectados


        public Message() {
        }

        public Message(User sender,String message) {
            this.sender = sender;
            this.message = message;
        }

        public Message(List<String> connectedUsers) {
            this.connectedUsers = connectedUsers;
        }

        public User getSender() {
            return sender;
        }

        public void setSender(User sender) {
            this.sender = sender;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<String> getConnectedUsers() {
            return connectedUsers;
        }

        public void setConnectedUsers(List<String> connectedUsers) {
            this.connectedUsers = connectedUsers;
        }
    }

