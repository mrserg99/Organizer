package busneslogick;

import adapter.XMLAdapter;
import dto.User;
import dto.Users;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Commands {
    private final Scanner scanner;
    private final Users users;
    private final File dataFile;
    private final XMLAdapter adapter;

    public Commands(Scanner scanner, Users users, File dataFile) {
        this.scanner = scanner;
        this.users = users;
        this.dataFile = dataFile;
        this.adapter = XMLAdapter.getAdapter();
    }

    public void help() {
        System.out.println(
                "help - Список команд,\n" +
                        "add - Добавить клиента,\n" +
                        "update - Редактировать клиента,\n" +
                        "remove - Удалить клиента,\n" +
                        "showUsers - Показать список клиентов,\n" +
                        "find - Найти клиента,\n" +
                        "exit - Закончить работу с программой\n"
        );
    }

    public void add() {
        User user = new User();
        int id = 0;

        while (true) {
            System.out.println("Введите уникальный Табельный номер");
            id = scanner.nextInt();
            if (checkThatIdNotUsed(id)){
                break;
            }
            System.out.println("Запись с таким Табельный номер уже существует, попробуйте другое значение");
        }
        user.setId(id);

        fillUserFields(user);

        users.getUserList().add(user);
        save();

        System.out.println("Элемент добавлен");
    }

    private void save() {
        adapter.marshal(users, dataFile);
    }

    public void update() {
        User user = askIdInConsoleAndGetUser();

        fillUserFields(user);

        save();

        System.out.println("Элемент обновлён");
    }

    public void remove() {
        User user = askIdInConsoleAndGetUser();

        users.getUserList().remove(user);

        save();

        System.out.println("Элемент удалён");
    }

    public void showUsers() {
        if (users.getUserList().size() == 0 || users.getUserList() == null){
            System.out.println("Файл пустой");
            return;
        }
        adapter.unmarshal(dataFile).getUserList().forEach(user -> {
            System.out.println(user.toString());
        });
    }

    public void find() {
        User user = askIdInConsoleAndGetUser();

        System.out.println(user.toString());
    }

    private boolean checkThatIdNotUsed(int id) {
        return users.getUserList().stream().noneMatch(user -> user.getId() == id);
    }

    private User askIdInConsoleAndGetUser() {
        int id = 0;

        while (true) {
            System.out.println("Введите уникальный Табельный номер");
            id = scanner.nextInt();
            if (checkWhatIdUsed(id)){
                break;
            }
            System.out.println("Записи с таким Табельны номером не существует, попробуйте другое значение");
        }

        return getUserOnId(id);
    }

    private boolean checkWhatIdUsed(int id) {
        return users.getUserList().stream().anyMatch(user -> user.getId() == id);
    }

    private User getUserOnId(int id) {
        Optional<User> optionalUser = users.getUserList().stream()
                .filter(user -> user.getId() == id).findFirst();

        if (optionalUser.isPresent()){
            return optionalUser.get();
        }else {
            throw new RuntimeException("Записи с таким Табельны номером не существует, попробуйте другое значение");
        }
    }

    private void fillUserFields(User user) {
        System.out.println("Введите \"ФИО\"");
        scanner.nextLine();
        String FIO = scanner.nextLine();
        user.setFIO(FIO);

        System.out.println("Введите \"Должность\"");
        String position = scanner.nextLine();
        user.setPosition(position);

        System.out.println("Введите \"Название организации\"");
        String organizationName = scanner.nextLine();
        user.setOrganizationName(organizationName);

        // по хорошему тут надо напиать нормальное регулярное выражение для провекри почты, но мне показалось это лишним
        String email = "";
        while (true) {
            System.out.println("Введите \"Почтовый адрес\"");
            email = scanner.nextLine();
            if (email.contains("@")) {
                break;
            }
            System.out.println("Некорректный \"Почтовый адрес\"");
        }
        user.setEmail(email);

        System.out.println("Введите \"Список телефонов\" через пробел");
        String phoneNumbers = scanner.nextLine();
        List<String> sss = Arrays.stream(phoneNumbers.split(" ")).filter(s -> !s.equals("")).collect(Collectors.toList());
        user.setPhoneNumbers(sss);
    }
}
