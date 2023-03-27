package dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
public class Users {
    @XmlElement(name = "user")
    private List<User> users = null;

    public List<User> getUserList() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
