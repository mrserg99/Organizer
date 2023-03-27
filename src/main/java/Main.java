import dto.Users;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        File dataFile = new File("./data.xml");

        Users users = null;
        try {
            users = InitializationAndStartProgram.init(dataFile);
        } catch (IOException e) {
            System.out.println("Не удалось найти путь к файлу data.xml");
            System.exit(1);
        }

        InitializationAndStartProgram.startWork(dataFile, users);
    }
}
