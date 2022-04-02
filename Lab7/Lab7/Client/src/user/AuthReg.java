package user;

import serialPack.Response;
import serialPack.user.User;

import java.util.Locale;
import java.util.Scanner;

public class AuthReg {
    private User user;
    private UserValid userValid;
    Scanner scanner = new Scanner(System.in);

    public AuthReg(User user) {
        this.user = user;
        this.userValid = new UserValid(this.user);
    }

    public void userAuthReg(){
        System.out.println("Введите reg, если хотите зарегистрироваться\n" +
                "Введите auth, если хотите войти!");
        while (true){
            String userInput = scanner.nextLine();
            if (userInput.trim().toLowerCase(Locale.ROOT).equals("1")){
                userRegistration();
                break;
            }
            if (userInput.trim().toLowerCase(Locale.ROOT).equals("2")){
                authorize();
                break;
            }
            System.err.println("Введите 1 или 2!");
        }
    }

    public void userRegistration() {
        user.setRegistration(true);
        user.setAuthorization(false);
        System.out.println("Регистрация пользователя!\n");
        user.setUsername(userValid.readUsername());
        user.setPassword(userValid.readPassword());
    }

    public void authorize() {
        user.setRegistration(false);
        user.setAuthorization(false);
        System.out.println("Авторизация");
        user.setUsername(userValid.readUsername());
        user.setPassword(userValid.readPassword());
    }

    public boolean userExist(Response response){
        if (response.getBody().equals("Пользователя с таким именем нет!")) return true;
        return false;
    }
}
