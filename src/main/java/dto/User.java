package dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement(name = "user")
@XmlType(propOrder = {"id", "FIO", "position", "organizationName", "email", "phoneNumbers"})
public class User {
    private int id;
    private String FIO;
    private String position;
    private String organizationName;
    private String email;
    private List<String> phoneNumbers;

    @XmlElement(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name = "FIO")
    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    @XmlElement(name = "position")
    public void setPosition(String position) {
        this.position = position;
    }

    @XmlElement(name = "organizationName")
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    @XmlElement(name = "email")
    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement(name = "phoneNumbers")
    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public int getId() {
        return id;
    }

    public String getFIO() {
        return FIO;
    }

    public String getPosition() {
        return position;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    @Override
    public String toString() {
        return  "id = " + id +
                ", ФИО = '" + FIO + '\'' +
                ", Должность = '" + position + '\'' +
                ", Организация = '" + organizationName + '\'' +
                ", Почтовый адрес = '" + email + '\'' +
                ", Список телефонов = " + phoneNumbers;
    }
}

