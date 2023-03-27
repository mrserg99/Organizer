package adapter;

import dto.Users;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLAdapter {
    private static final XMLAdapter adapter = new XMLAdapter();

    private XMLAdapter(){}

    public static XMLAdapter getAdapter() {
        return adapter;
    }

    public void marshal(Users users, File dataFile){
        if (!dataFile.isFile()){
            throw new RuntimeException("Не могу найти файл");
        }

        try {
            JAXBContext jaxbContext = getJAXBContext();
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(users, dataFile);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public Users unmarshal(File data){
        if (!data.isFile()){
            throw new RuntimeException("Не могу найти файл");
        }

        try {
            JAXBContext jaxbContext = getJAXBContext();
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (Users) jaxbUnmarshaller.unmarshal(data);
        } catch (JAXBException e) {
            System.err.println("Некорректный xml файл");
            throw new RuntimeException(e);
        }
    }

    private JAXBContext getJAXBContext() throws JAXBException {
            return JAXBContext.newInstance(Users.class);
    }
}
