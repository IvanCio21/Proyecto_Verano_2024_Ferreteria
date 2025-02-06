package cr.ac.una.Protocol;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private String userName;
    private String password;
    private String status;

    private int intentosFallidos = 0;

    public User(String password, String status, String userName) {
        this.password = password;
        this.status = status;
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.userName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final User other = (User) obj;
        return Objects.equals(this.userName, other.userName) && Objects.equals(this.password, other.password);

    }

    public int getIntentosFallidos() {
        return intentosFallidos;
    }

    public void setIntentosFallidos(int intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
    }

    public void incrementarIntentosFallidos() {
        this.intentosFallidos++;
    }

    public void resetearIntentosFallidos() {
        this.intentosFallidos = 0;
    }

}
