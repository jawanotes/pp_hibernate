package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        User man;
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        man = new User("Ivan", "Ivanov", (byte) 11);
        userService.saveUser(man.getName(), man.getLastName(), man.getAge());
        System.out.printf("User с именем – %s добавлен в базу данных\n", man.getName());

        man = new User("Petr", "Petrov", (byte) 22);
        userService.saveUser(man.getName(), man.getLastName(), man.getAge());
        System.out.printf("User с именем – %s добавлен в базу данных\n", man.getName());

        man = new User("Sidor", "Sidorov", (byte) 33);
        userService.saveUser(man.getName(), man.getLastName(), man.getAge());
        System.out.printf("User с именем – %s добавлен в базу данных\n", man.getName());

        man = new User("Незнайка", "Незнанский", (byte) 44);
        userService.saveUser(man.getName(), man.getLastName(), man.getAge());
        System.out.printf("User с именем – %s добавлен в базу данных\n", man.getName());

        List<User> userList = userService.getAllUsers();
        StringBuilder sb = new StringBuilder();
        for (User user : userList) {
            sb.append(user.getId()).append(" ")
                    .append(user.getName()).append(" ")
                    .append(user.getLastName()).append(" ")
                    .append(user.getAge()).append("\n");
        }
        System.out.println(sb);

        userService.dropUsersTable();
    }
}
