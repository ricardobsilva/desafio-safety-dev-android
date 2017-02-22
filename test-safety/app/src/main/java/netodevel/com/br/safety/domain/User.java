package netodevel.com.br.safety.domain;

import java.io.Serializable;

/**
 * @author NetoDevel
 */
public class User implements Serializable {

    public boolean logged;
    public String name;

    public User(boolean logged, String name) {
        this.logged = logged;
        this.name = name;
    }

    public User(){
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
