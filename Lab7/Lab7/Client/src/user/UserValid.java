package user;

import serialPack.user.User;

import java.util.Scanner;

public class UserValid {
    private User user;
    Scanner scanner = new Scanner(System.in);

    public UserValid (User user) {
        this.user = user;
    }

    public String readUsername() {
        while (true) {
            System.out.println("Имя пользователя:");
            String username = scanner.nextLine();
            if (!validateUsername(username)) continue;
            return username;
        }
    }

    public String readPassword() {
        while (true) {
            System.out.println("Пароль:");
            String password = scanner.nextLine();
            if (!validatePassword(password)) continue;
            return password;
        }
    }

    private boolean validateUsername(String username) {
        if ("".equals(username.split("\\s+")) || "".equals(username.trim())) {
            System.err.println("Имя пользователя не может быть пустой строкой!");
            return false;
        }
        if (!username.matches("\\b[a-zA-Z][a-zA-Z0-9\\-._]{3,16}\\b")) {
            System.err.println("Имя пользователя может содержать строчные и заглавные латинские буквы,цифры, символ . и _ " +
                    "Длина должна быть больше 3 и меньше 16!");
            return false;
        }
        return true;
    }

    private boolean validatePassword(String password) {
        if ("".equals(password.split("\\s+")) || "".equals(password.trim())) {
            System.err.println("Пароль не может быть пустой строкой!");
            return false;
        }
        if (!password.matches("\\b[a-zA-Z][a-zA-Z0-9\\-._]{3,10}\\b")) {
            System.err.println("Пароль может содержать строчные и заглавные латинские буквы,цифры, символ . и _ " +
                    "Длина должна быть больше 3 и меньше 10!");
            return false;
        }
        return true;
    }
}
