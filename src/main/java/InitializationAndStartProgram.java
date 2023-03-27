import adapter.XMLAdapter;
import busneslogick.Commands;
import dto.Users;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class InitializationAndStartProgram {
    public static Users init(File dataFile) throws IOException {
        if (dataFile.isFile()){
            XMLAdapter adapter = XMLAdapter.getAdapter();
            return adapter.unmarshal(dataFile);
        } else if (dataFile.createNewFile()) {
            System.out.println("Создан файл для хранения данных");
            Users users = new Users();
            users.setUsers(new ArrayList<>());
            return users;
        }else {
            System.out.println("Не удалось создать файл для хранения данных");
            System.exit(1);
        }
        return null;
    }

    public static void startWork(File dataFile, Users users){
        boolean isWork = true;

        if (!dataFile.isFile()){
            throw new RuntimeException("Не могу найти файл");
        }

        Scanner scanner = new Scanner(System.in);
        Commands commands = new Commands(scanner, users, dataFile);

        while (isWork){
            System.out.println("Чтобы вызвать справку по командам вбейте help");
            String input = scanner.nextLine();

            switch (input){
                case "help":
                    commands.help();
                    break;
                case "add":
                    commands.add();
                    break;
                case "update":
                    commands.update();
                    break;
                case "remove":
                    commands.remove();
                    break;
                case "showUsers":
                    commands.showUsers();
                    break;
                case "find":
                    commands.find();
                    break;
                case "exit":
                    isWork = false;
                    break;
                default:
                    System.out.println("Не могу найти такой комманды");
            }
        }
    }
}
